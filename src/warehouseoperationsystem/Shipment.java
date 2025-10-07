package warehouseoperationsystem;

public class Shipment extends Document {
	private static int counter = 1;
    private Pack pack;
    private boolean shipped = false;

    public Shipment(Pack pack) {
        this.id = counter++;
        this.pack = pack;
    }

    @Override
    public void showSummary() {
        System.out.println("Shipment " + id + " Summary (from Pack " + pack.getId() + "):");
        pack.showSummary();
    }

    public void ship() {
        shipped = true;
        System.out.println("Shipment " + id + " dispatched successfully!");
    }

    public String toString() {
        return "Shipment " + id + " from Pack " + pack.getId() + " (shipped=" + shipped + ")";
    }

}
