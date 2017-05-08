package by.tc.onlinepharmacy.resource;


/**
 * The class contains constants that represent jsp page paths.
 */
public final class JspPageName {

    private JspPageName() {
    }

    //common
    public final static String START_PAGE = "/WEB-INF/jsp/common/startPage.jsp";
    public final static String DRUG_LIST_PAGE = "/WEB-INF/jsp/common/drugList.jsp";
    public final static String SIGN_IN_PAGE = "/WEB-INF/jsp/common/signIn.jsp";
    public final static String WRONG_REQUEST_PAGE = "/WEB-INF/jsp/common/404.jsp";
    public final static String FORBIDDEN_PAGE = "/WEB-INF/jsp/common/403.jsp";
    public final static String SERVER_ERROR_PAGE = "/WEB-INF/jsp/common/500.jsp";
    public final static String BAD_REQUEST_PAGE = "/WEB-INF/jsp/common/400.jsp";

    //pharmacist
    public final static String PHARMACIST_HOME_PAGE = "/WEB-INF/jsp/pharmacist/homePage.jsp";
    public final static String PHARMACIST_ADD_DRUG_PAGE = "/WEB-INF/jsp/pharmacist/addDrug.jsp";
    public final static String PHARMACIST_GROUPS_TO_UPDATE = "/WEB-INF/jsp/pharmacist/groupsToUpdate.jsp";
    public final static String PHARMACIST_DRUG_LIST_TO_UPDATE = "/WEB-INF/jsp/pharmacist/drugListToUpdate.jsp";
    public final static String PHARMACIST_SALES_REPORT = "/WEB-INF/jsp/pharmacist/salesReport.jsp";

    //client
    public final static String SIGN_UP_PAGE = "/WEB-INF/jsp/client/signUp.jsp";
    public final static String CLIENT_HOME_PAGE = "/WEB-INF/jsp/client/homePage.jsp";
    public final static String CLIENT_DRUG_LIST_TO_ORDER = "/WEB-INF/jsp/client/drugListToOrder.jsp";
    public final static String CLIENT_CANCEL_ORDER_PAGE = "/WEB-INF/jsp/client/cancelOrder.jsp";
    public final static String CLIENT_EXTEND_RECIPE_PAGE = "/WEB-INF/jsp/client/extendRecipe.jsp";
    public final static String CLIENT_RECIPE_DESCRIPTION = "/WEB-INF/jsp/client/recipeDescription.jsp";
    public final static String CLIENT_ORDER_BY_ERECIPE = "/WEB-INF/jsp/client/orderByERecipe.jsp";
    public final static String CLIENT_PHARM_GROUPS = "/WEB-INF/jsp/client/pharmGroups.jsp";
    public final static String CLIENT_BALANCE = "WEB-INF/jsp/client/balance.jsp";
    public final static String CLIENT_SHOPPING_LIST = "WEB-INF/jsp/client/shoppingList.jsp";

    //doctor
    public final static String DOCTOR_HOME_PAGE = "/WEB-INF/jsp/doctor/homePage.jsp";

}