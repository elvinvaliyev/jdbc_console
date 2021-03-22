import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        //the connection options
        final String jdbcURL = "jdbc:mysql://localhost:3306/jdbc_users";
        final String username = "root";
        final String password = "password";

        //fields of user_table
        int id;
        String name;
        String surname;

        System.out.println("Select what you want to do:\n1.Show all table " +
                "\n2.Delete user \n3.Insert user \n4.Update user");
        int selectTheOption = scanner.nextInt();

        try {
            final Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            switch (selectTheOption) {
                case 1:
                    String sql1 = "Select * from user_table";
                    Statement statement1 = connection.createStatement();
                    ResultSet result = statement1.executeQuery(sql1);

                    System.out.println("The table :");

                    while (result.next()) {
                        int userId = result.getInt(1);
                        String userName = result.getString(2);
                        String userSurname = result.getString(3);
                        System.out.println(userId + " " + userName + " " + userSurname);
                    }

                    connection.close();
                    break;
                case 2:
                    String sql3 = "DELETE FROM user_table WHERE id=?";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);

                    System.out.println("Input the id");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    statement3.setInt(1, id);

                    int rows2 = statement3.executeUpdate();
                    if (rows2 > 0) {
                        System.out.println("A user has been deleted successfully");
                    }

                    connection.close();
                    break;
                case 3:
                    String sql4 = "INSERT INTO user_table (id,name,surname) VALUES(?,?,?)";
                    PreparedStatement statement4 = connection.prepareStatement(sql4);

                    System.out.println("Input the id of user you want to insert");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Input the name of user");
                    name = scanner.nextLine();

                    System.out.println("Input the surname of user");
                    surname = scanner.nextLine();

                    statement4.setInt(1, id);
                    statement4.setString(2, name);
                    statement4.setString(3, surname);

                    int rows3 = statement4.executeUpdate();
                    if (rows3 > 0) {
                        System.out.println("A new user has been inserted successfully");
                    }

                    connection.close();

                    break;
                case 4:
                    String sql5 = "UPDATE user_table SET name=?,surname=? WHERE id=?";
                    PreparedStatement statement5 = connection.prepareStatement(sql5);

                    System.out.println("Input the id of user you want update");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Change the name of user");
                    name = scanner.nextLine();

                    System.out.println("Change the surname of user");
                    surname = scanner.nextLine();

                    statement5.setString(1, name);
                    statement5.setString(2, surname);
                    statement5.setInt(3, id);

                    int rows4 = statement5.executeUpdate();
                    if (rows4 > 0) {
                        System.out.println("A new user has been updated successfully");
                    }

                    connection.close();
                    break;
                default:
                    System.out.println("Nothing to do");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
