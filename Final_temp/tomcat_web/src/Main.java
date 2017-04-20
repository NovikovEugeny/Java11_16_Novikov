import by.tc.online_pharmacy.dao.exception.DaoException;

public class Main {
    public static void main(String[] args) {

        try {
            if (2 == 3) {
                throw new DaoException("isunique");
            }
            if (2 == 2) {
                throw new DaoException();
            }
        } catch (DaoException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
