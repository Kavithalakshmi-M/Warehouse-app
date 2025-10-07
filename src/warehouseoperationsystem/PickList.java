package warehouseoperationsystem;

import java.util.ArrayList;
import java.util.List;

public class PickList extends Document {
	 private static int counter = 1;
	    private List<PickTask> tasks;

	    public PickList(List<PickTask> tasks) {
	        this.id = counter++;
	        this.tasks = tasks;
	    }

	    public List<PickTask> getTasks() { return tasks; }

	    public List<PickTask> getPickedTasks() {
	        List<PickTask> out = new ArrayList<>();
	        for (PickTask t : tasks) if (t.getQtyPicked() > 0) out.add(t);
	        return out;
	    }

	    @Override
	    public void showSummary() {
	        System.out.println("PickList " + id + " Summary:");
	        for (PickTask t : tasks) System.out.println("  - " + t);
	    }

	    public String toString() {
	        return "PickList " + id + " with " + tasks.size() + " tasks";
	    }

}
