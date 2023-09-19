package Task3;

import Task2.Comment;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.Arrays;
import java.util.List;

public class TodoManager {
    public static TodoManager shared = new TodoManager();
    private final Gson gson;

    private TodoManager() {
        this.gson = new Gson();
    }

    public Todo[] getAllTodoByUserId(int userId) {
        try {
            final String URL = String.format("https://jsonplaceholder.typicode.com/users/%d/todos", userId);

            Connection.Response response = Jsoup.connect(URL)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            return gson.fromJson(response.body(), Todo[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Todo[] {};
        }
    }

    public List<Todo> getAllCompletedTodoByUserId(int userId) {
        Todo[] allTodo = getAllTodoByUserId(userId);
        List<Todo> completedTodo = Arrays.asList(allTodo)
                .stream()
                .filter(todo -> todo.isCompleted())
                .toList();

        return completedTodo;
    }
}
