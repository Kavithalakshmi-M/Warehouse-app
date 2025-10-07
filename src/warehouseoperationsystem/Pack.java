package warehouseoperationsystem;

import java.util.List;

public class Pack extends Document{
	private static int counter = 1;
    private List<PickTask> tasks;

    public Pack(List<PickTask> tasks) {
        this.id = counter++;
        this.tasks = tasks;
    }

    @Override
    public void showSummary() {
        System.out.println("Pack " + id + " Summary:");
        for (PickTask t : tasks) System.out.println("  - " + t);
    }

    public String toString() {
        return "Pack " + id + " (items=" + tasks.size() + ")";
    }
}
