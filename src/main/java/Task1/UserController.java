package Task1;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class UserController {
    public static UserController shared = new UserController();
    private final String URL;
    private final Gson gson;

    private UserController() {
        URL = "https://jsonplaceholder.typicode.com/users";
        gson = new Gson();
    }

    public User[] getUsers() {
        try {
            Connection.Response response = Jsoup.connect(URL)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            return gson.fromJson(response.body(), User[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User[] {};
        }
    }

    public User updateUser(User updatedUser) {
        try {
            String payload = gson.toJson(updatedUser);

            Connection.Response response = Jsoup.connect(URL + String.format("/%d", updatedUser.getId()))
                    .header("Content-Type", "application/json")
                    .requestBody(payload)
                    .ignoreContentType(true)
                    .method(Connection.Method.PUT)
                    .execute();

            return gson.fromJson(response.body(), User.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    public boolean removeUser(int userId) {
        try {
            Connection.Response response = Jsoup.connect(URL + String.format("/%d", userId))
                    .header("Content-Type", "application/json")
                    .method(Connection.Method.DELETE)
                    .ignoreContentType(true)
                    .execute();
            return response.statusCode() >= 200 && response.statusCode() < 300;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User getUser(int userId) {
        try {
            Connection.Response response = Jsoup.connect(URL + String.format("/%d", userId))
                    .header("Content-Type", "application/json")
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();

            return gson.fromJson(response.body(), User.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    public User getUser(String username) {
        try {
            Connection.Response response = Jsoup.connect(URL + String.format("?username=%s", username))
                    .header("Content-Type", "application/json")
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();

            User[] users = gson.fromJson(response.body(), User[].class);
            if (users.length == 0)
                return new User() {};

            return users[0];
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }
}
