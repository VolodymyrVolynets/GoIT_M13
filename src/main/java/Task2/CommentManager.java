package Task2;

public class CommentManager {
    public void writeToFileAllCommentsToUserPost(int userId) {
        Comment[] comments = CommentController.shared.getAllCommentsToLastPostOfUser(userId);
        if (comments.length == 0)
                return;
        MyFileWriter writer = new MyFileWriter();
        writer.writeFile(comments, String.format("src/main/java/Task2/user-%d-post-%d-comments.json", userId, comments[0].getPostId()));
    }
}
