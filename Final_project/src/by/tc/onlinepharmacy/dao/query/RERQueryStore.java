package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from table recipe_extension_request.
 */
public class RERQueryStore {

    private RERQueryStore() {
    }

    public final static String IS_UNIQUE_APPLICATION_SELECT = "SELECT * FROM recipe_extension_request WHERE " +
            "recipe_code = ? AND status = 'new'";

    public final static String INSERT_RECIPE_EXTENSION_REQUEST =
            "INSERT INTO recipe_extension_request(recipe_code, client_id, request_date, status, is_read) " +
                    "VALUES (?, ?, NOW(), 'new', 'no')";

    public final static String SELECT_NEW_RECIPE_EXTENSION_REQUEST =
            "SELECT * FROM recipe_extension_request WHERE status = 'new'";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_APPROVED =
            "UPDATE recipe_extension_request SET status = 'approved', doctor_id = ?, request_date = request_date, " +
                    "response_date = NOW() WHERE id = ?";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_DENIED =
            "UPDATE recipe_extension_request SET status = 'denied', doctor_id = ?, request_date = request_date, " +
                    "response_date = NOW() WHERE id = ?";

    public final static String SELECT_DOCTOR_RESPONSE_BY_CLIENT_ID =
            "SELECT id, recipe_code, response_date, status FROM recipe_extension_request WHERE client_id = ? " +
                    "AND is_read = 'no' AND (status = 'approved' OR status = 'denied')";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_IS_READ_YES =
            "UPDATE recipe_extension_request SET is_read = 'yes', request_date = request_date WHERE id = ?";

}