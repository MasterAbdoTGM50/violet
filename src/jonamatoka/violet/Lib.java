package jonamatoka.violet;

public class Lib {

    public static class Paths {

    }

    public static class Mappings {

        public static final String ROOT = "/";

        public static final String LOGIN = ROOT + "login";
        public static final String LOGOUT = ROOT + "login?logout";

        public static final String REGISTER = ROOT + "register";

        public static final String ADD_PRODUCT_SYSTEM = ROOT + "apts";
        public static final String ADD_STORE_SYSTEM = ROOT + "asts";
        public static final String ADD_CATEGORY_SYSTEM = ROOT + "acts";
        public static final String ADD_BRAND_SYSTEM = "abts";

        public static final String VIEW_SYSTEM_STATISTICS = ROOT + "vsts";
        public static final String VIEW_SYSTEM_STORES = ROOT + "vst";

        public static final String GET_STORE_PAGE = ROOT + "store";

    }

    public static class Templates {

        public static final String INDEX = "index";
        public static final String LOGIN = "login";

        public static final String REGISTER = "register";
        public static final String ADD_PRODUCT_SYSTEM = "apts";

        public static final String ADD_STORE_SYSTEM = "asts";
        public static final String ADD_CATEGORY_SYSTEM = "acts";
        public static final String ADD_BRAND_SYSTEM = "abts";

        public static final String VIEW_SYSTEM_STATISTICS = "vsts";
        public static final String VIEW_SYSTEM_STORES = "vst";

        public static final String GET_STORE_PAGE = "store";

    }

    public static class Privliges {

        public static final Integer ADMIN = 0;
        public static final Integer OWNER = 3;
        public static final Integer USER = 6;

    }

}