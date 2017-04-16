import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> string1 = new ArrayList<>();
        string1.add("a1");
        string1.add("b1");
        string1.add("c1");
        string1.add("d1");
        string1.add("e1");

        List<String> string2 = new ArrayList<>();
        string2.add("a2");
        string2.add("b2");
        string2.add("c2");
        string2.add("d2");
        string2.add("e2");

        List<String> string3 = new ArrayList<>();
        string3.add("a3");
        string3.add("b3");
        string3.add("c3");
        string3.add("d3");
        string3.add("e3");


        Map<Integer, List<String>> map = new LinkedHashMap<>();
        map.put(1, string1);
        map.put(2, string2);
        map.put(3, string3);

        System.out.println(map);

        //System.out.println(new Date(1492293854789L - 3600000));

        Calendar calendar = new GregorianCalendar();
        //calendar.setTimeInMillis(1492293854789L);
        //System.out.println(calendar);

        System.out.println(calendar.get(Calendar.YEAR));


    }
}
