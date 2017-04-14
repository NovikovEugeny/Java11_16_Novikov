import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.impl.DrugDaoImpl;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static boolean testPattern(String pattern, String str) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(str);
    return m.matches();
}

    public static void main(String[] args) {

    }
}
