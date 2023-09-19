package Task2;

import Task1.User;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.*;

public class CommentController {
    public static CommentController shared = new CommentController();
    private final Gson gson;

    private CommentController() {
        gson = new Gson();
    }

    public Comment[] getAllCommentsToLastPostOfUser(int userId) {
        List<Post> posts = Arrays.asList(getAllPostOfUser(userId));

        Optional<Post> lastPost = posts.stream()
                .max(Comparator.comparingInt(Post::getId));

        if (!lastPost.isPresent())
            return new Comment[] {};

        int lastPostId = lastPost.get().getId();

        try {
            final String URL = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", lastPostId);

            Connection.Response response = Jsoup.connect(URL)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            return gson.fromJson(response.body(), Comment[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Comment[] {};
        }
    }

    public Post[] getAllPostOfUser(int userId) {
        try {
            final String URL = String.format("https://jsonplaceholder.typicode.com/users/%d/posts", userId);

            Connection.Response response = Jsoup.connect(URL)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            return gson.fromJson(response.body(), Post[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Post[] {};
        }
    }

}
