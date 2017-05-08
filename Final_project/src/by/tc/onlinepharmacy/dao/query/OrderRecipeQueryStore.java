package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from table order_recipe.
 */
public final class OrderRecipeQueryStore {

    private OrderRecipeQueryStore(){
    }

    public final static String INSERT_ORDER_RECIPE = "INSERT INTO order_recipe(order_id, recipe_code) VALUES (?, ?)";

    public final static String SELECT_RECIPE_CODE_BY_ORDER_ID =
            "SELECT recipe_code FROM order_recipe WHERE order_id = ?";

}