package warehouseoperationsystem;

public class InventoryRecord {
	private Item item;
    private Location location;
    private int onHand;

    public InventoryRecord(Item item, Location location, int onHand) {
        this.item = item;
        this.location = location;
        this.onHand = onHand;
    }
    public Item getItem() { return item; }
    public Location getLocation() { return location; }
    public int getOnHand() { return onHand; }
    public void setOnHand(int q) { this.onHand = q; }

    public String toString() {
        return item.getName() + "@" + location.getName() + " = " + onHand;
    }

}
