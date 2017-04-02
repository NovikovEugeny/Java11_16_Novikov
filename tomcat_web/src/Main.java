import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static boolean testPattern(String pattern, String str) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(str);
    return m.matches();
}

    public static void main(String[] args) {
/*
        try {
           /* ConnectionPool.getInstance().init();

            Connection connection = ConnectionPool.getInstance().takeConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select end_date FROM recipes where code = '44441111'");

            resultSet.next();
            Date currentDate = new Date();
            Date end_date = resultSet.getTimestamp(1);

            System.out.println(currentDate.getTime());
            System.out.println(end_date);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        String s = "Awerfdgd4";

         String mobilePattern = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";

        System.out.println(testPattern(mobilePattern, s));
    }
}
