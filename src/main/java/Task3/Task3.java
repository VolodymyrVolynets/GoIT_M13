package Task3;

public class Task3 {
    public static void main(String[] args) {
        for(Todo t: TodoManager.shared.getAllCompletedTodoByUserId(1)) {
            System.out.println(t);
            System.out.println();
        }
    }
}
