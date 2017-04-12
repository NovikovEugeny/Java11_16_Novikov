import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static boolean testPattern(String pattern, String str) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(str);
    return m.matches();
}

    public static void main(String[] args) {

        String insert1 = "insert into po values(1, ?)";
        String insert2 = "insert into po values(2, ?)";
        String insert3 = "insert into po values(3, ?)";

        String url = "jdbc:mysql://localhost:3306/lr7";
        String userName = "root";
        String password = "";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(insert1);
            ps.setString(1, "hello world - 1");
            ps.executeUpdate();
            System.out.println("insert1");

            System.out.println("ok?");

            Scanner scan = new Scanner(System.in);

            int i = scan.nextInt();

            if (i == 0) {
                connection.rollback();
                System.out.println("rollback");
            }

            connection.commit();
            System.out.println("commit");

        } catch (ClassNotFoundException | SQLException exc) {
            System.out.println("rollback");
            exc.printStackTrace();
        }

    }
}
