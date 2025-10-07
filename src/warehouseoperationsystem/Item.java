package warehouseoperationsystem;

public class Item {
	private static int counter = 1;
    private int id;
    private String name;
    private String sku;

    public Item(String name, String sku) {
        this.id = counter++;
        this.name = name;
        this.sku = sku;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }

    public String toString() {
        return id + ": " + name + " (SKU=" + sku + ")";
    }
}
