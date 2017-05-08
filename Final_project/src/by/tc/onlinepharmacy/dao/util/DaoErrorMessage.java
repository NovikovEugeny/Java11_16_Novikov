package by.tc.onlinepharmacy.dao.util;

/**
 * The class contains constants that represent an error messages from DAO layer for logger.
 */
public final class DaoErrorMessage {

    private DaoErrorMessage() {
    }

    public final static String CLOSING_RESOURCES_ERROR = "closing resources error";
    public final static String ROLLBACK_ERROR = "rollback error";
    public final static String SET_AUTO_COMMIT_TRUE_ERROR = "setAutoCommit(true)  error";

}