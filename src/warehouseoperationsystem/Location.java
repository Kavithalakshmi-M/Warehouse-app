package warehouseoperationsystem;

public class Location {
	 private static int counter = 1;
	    private int id;
	    private String name;

	    public Location(String name) {
	        this.id = counter++;
	        this.name = name;
	    }
	    public int getId() { return id; }
	    public String getName() { return name; }

	    public String toString() { return id + ": " + name; }
}
