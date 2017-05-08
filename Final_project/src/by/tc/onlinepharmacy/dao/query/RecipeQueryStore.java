package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from table recipe.
 */
public class RecipeQueryStore {

    private RecipeQueryStore() {
    }

    public final static String SELECT_END_RECIPE_DATE = "SELECT end_date FROM recipe WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_CLOSED =
            "UPDATE recipe SET status = 'closed', start_date = start_date WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_OPEN =
            "UPDATE recipe SET status = 'open', start_date = start_date WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_OPEN_AND_EXTEND_DATE =
            "UPDATE recipe SET status = 'open', start_date = start_date, end_date = end_date + " +
                    "INTERVAL 6 MONTH WHERE code = ?";

}