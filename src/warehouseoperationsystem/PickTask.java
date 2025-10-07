package warehouseoperationsystem;

public class PickTask {
    private Item item;
    private Location location;
    private int qtyRequested;
    private int qtyPicked;

    public PickTask(Item item, Location location, int qtyRequested) {
        this.item = item;
        this.location = location;
        this.qtyRequested = qtyRequested;
        this.qtyPicked = 0;
    }
    public Item getItem() { return item; }
    public Location getLocation() { return location; }
    public int getQtyRequested() { return qtyRequested; }
    public int getQtyPicked() { return qtyPicked; }
    public void setQtyPicked(int q) { qtyPicked = q; }

    public String toString() {
        return item.getName() + " from " + location.getName() +
                " req=" + qtyRequested + " picked=" + qtyPicked;
    }
}
