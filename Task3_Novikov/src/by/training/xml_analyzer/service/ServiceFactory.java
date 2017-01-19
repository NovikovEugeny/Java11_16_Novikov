package by.training.xml_analyzer.service;

/**
 * Created by Евгений on 18.01.2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final Service service = new ServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Service getService() {
        return service;
    }
}
