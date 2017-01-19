package by.training.xml_analyzer.dao;

/**
 * Created by Евгений on 18.01.2017.
 */
public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();
    private final Dao dao = new DaoImpl();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public Dao getDao() {
        return dao;
    }
}
