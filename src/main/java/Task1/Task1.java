package Task1;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("-------------------Get All Users-------------------");
        User[] u = UserController.shared.getUsers();
        for(User user: u) {
            System.out.println(user);
        }

        System.out.println("-------------------Update User-------------------");
        User.Address address = new User.Address("123 Maple St", "Suite 100", "Springfield");
        User user = new User(1, "John Doe", "johnD", "john.doe@example.com", "123-456-7890", address);
        System.out.println(UserController.shared.updateUser(user));

        System.out.println("-------------------Remove User-------------------");
        System.out.println(UserController.shared.removeUser(0));

        System.out.println("-------------------Get User By Id-------------------");
        System.out.println(UserController.shared.getUser(0));
        System.out.println(UserController.shared.getUser(1));

        System.out.println("-------------------Get User By Username-------------------");
        System.out.println(UserController.shared.getUser("fdsfds"));
        System.out.println(UserController.shared.getUser("Antonette"));
    }
}