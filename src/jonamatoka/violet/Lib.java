package jonamatoka.violet;

public class Lib {

    public static class Paths {

    }

    public static class Mappings {

        public static final String ROOT = "/";

        public static final String REGISTER = ROOT + "register";

        public static final String BRAND_SERVICES = ROOT + "brand";
        public static final String ADD_BRAND_SYSTEM = BRAND_SERVICES + "/add";

        public static final String CATEGORY_SERVICES = ROOT + "category";
        public static final String ADD_CATEGORY_SYSTEM = CATEGORY_SERVICES + "/add";

        public static final String PRODUCT_SERVICES = ROOT + "product";
        public static final String ADD_PRODUCT_SYSTEM = PRODUCT_SERVICES + "/add";

        public static final String STORE_SERVICES = ROOT + "store";
        public static final String ADD_STORE_SYSTEM = STORE_SERVICES + "/add";

        public static final String GET_STORE_SYSTEM = STORE_SERVICES + "/{storeId}";
    }

    public static class Templates {

    }

    public static class Privileges {

        public static final Integer ADMIN = 0;
        public static final Integer OWNER = 3;
        public static final Integer USER = 6;

    }

}