package warehouseoperationsystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WarehouseApp {
	private static Scanner sc = new Scanner(System.in);

    private static Map<Integer, Item> items = new HashMap<>();
    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<String, InventoryRecord> inventory = new HashMap<>();
    private static Map<Integer, PickList> pickLists = new HashMap<>();
    private static Map<Integer, Pack> packs = new HashMap<>();
    private static Map<Integer, Shipment> shipments = new HashMap<>();

	public static void main(String[] args) {

	        boolean getchoice = true;
	        while (getchoice) {
	            System.out.println("\n**** Warehouse Menu ****");
	            System.out.println("1. Add Item");
	            System.out.println("2. Add Location");
	            System.out.println("3. Adjust Inventory");
	            System.out.println("4. Create Pick List");
	            System.out.println("5. Record Pick");
	            System.out.println("6. Create Pack");
	            System.out.println("7. Ship Order");
	            System.out.println("8. Inventory Summary");
	            System.out.println("9. Exit");

	            int choice = readInt("Enter choice: ");
	            try {
	                switch (choice) {
	                    case 1: addItem(); break;
	                    case 2: addLocation(); break;
	                    case 3: adjustInventory(); break;
	                    case 4: createPickList(); break;
	                    case 5: recordPick(); break;
	                    case 6: createPack(); break;
	                    case 7: shipOrder(); break;
	                    case 8: inventorySummary(); break;
	                    case 9: getchoice = false; break;
	                    default: System.out.println("Invalid choice.");
	                }
	            } catch (Exception e) {
	                System.out.println("Error: " + e.getMessage());
	            }
	        }
	    }

	    private static void addItem() {
	        String name = readStr("Item name: ");
	        String sku = readStr("SKU: ");
	        Item it = new Item(name, sku);
	        items.put(it.getId(), it);
	        System.out.println("Added: " + it);
	    }

	    private static void addLocation() {
	        String name = readStr("Location name: ");
	        Location loc = new Location(name);
	        locations.put(loc.getId(), loc);
	        System.out.println("Added: " + loc);
	    }

	    private static void adjustInventory() {
	        if (items.isEmpty() || locations.isEmpty()) {
	            System.out.println("Add items and locations first.");
	            return;
	        }
	        showItems();
	        int itemId = readInt("Item id: ");
	        showLocations();
	        int locId = readInt("Location id: ");
	        int qty = readInt("Quantity: ");

	        String key = itemId + "_" + locId;
	        InventoryRecord rec = new InventoryRecord(items.get(itemId), locations.get(locId), qty);
	        inventory.put(key, rec);
	        System.out.println("Inventory updated: " + rec);
	    }

	    private static void createPickList() {
	        if (inventory.isEmpty()) { System.out.println("No inventory."); return; }
	        List<PickTask> tasks = new ArrayList<>();
	        showItems();
	        int itemId = readInt("Pick item id: ");
	        showLocations();
	        int locId = readInt("From location id: ");
	        int qty = readInt("Qty to pick: ");

	        String key = itemId + "_" + locId;
	        InventoryRecord rec = inventory.get(key);
	        if (rec == null || rec.getOnHand() < qty) {
	            System.out.println("Not enough stock!");
	            return;
	        }
	        tasks.add(new PickTask(items.get(itemId), locations.get(locId), qty));
	        PickList pl = new PickList(tasks);
	        pickLists.put(pl.getId(), pl);
	        System.out.println("PickList created: " + pl);
	    }

	    private static void recordPick() {
	        if (pickLists.isEmpty()) { System.out.println("No pick lists."); return; }
	        int pid = readInt("PickList id: ");
	        PickList pl = pickLists.get(pid);

	        for (PickTask t : pl.getTasks()) {
	            int picked = readInt("Picked qty for " + t.getItem().getName() + ": ");
	            String key = t.getItem().getId() + "_" + t.getLocation().getId();
	            InventoryRecord rec = inventory.get(key);
	            rec.setOnHand(rec.getOnHand() - picked);
	            t.setQtyPicked(picked);
	        }
	        pl.showSummary();
	    }

	    private static void createPack() {
	        if (pickLists.isEmpty()) { System.out.println("No pick lists."); return; }
	        int pid = readInt("PickList id: ");
	        PickList pl = pickLists.get(pid);
	        Pack p = new Pack(pl.getPickedTasks());
	        packs.put(p.getId(), p);
	        p.showSummary();
	    }

	    private static void shipOrder() {
	        if (packs.isEmpty()) { System.out.println("No packs."); return; }
	        int packId = readInt("Pack id: ");
	        Pack p = packs.get(packId);
	        Shipment s = new Shipment(p);
	        shipments.put(s.getId(), s);
	        s.showSummary();
	        s.ship();
	    }

	    private static void inventorySummary() {
	        for (InventoryRecord r : inventory.values()) {
	            System.out.println(r);
	        }
	    }

	    private static int readInt(String msg) {
	        while (true) {
	            try {
	                System.out.print(msg);
	                return Integer.parseInt(sc.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid number. Try again.");
	            }
	        }
	    }

	    private static String readStr(String msg) {
	        System.out.print(msg);
	        return sc.nextLine().trim();
	    }

	    private static void showItems() {
	        for (Item it : items.values()) System.out.println(it);
	    }
	    private static void showLocations() {
	        for (Location l : locations.values()) System.out.println(l);
	    }
}
