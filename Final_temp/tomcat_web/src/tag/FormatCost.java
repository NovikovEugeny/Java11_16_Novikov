package tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatCost extends SimpleTagSupport{

    private final static String RU = "ru";
    private final static String EN = "en";

    private double value;
    private String locale;

    public void setValue(double value) {
        this.value = value;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {

        NumberFormat cfRU = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));
        NumberFormat cfUS = NumberFormat.getCurrencyInstance(Locale.US);

        JspWriter out = getJspContext().getOut();

        if (locale.equals(RU)) {
            out.print(cfRU.format(value));
        }
        if (locale.equals(EN)) {
            out.print(cfUS.format(value/1.87));
        }
    }
}