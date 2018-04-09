package jonamatoka.violet;

public class Lib {

    public static class Paths {

    }

    public static class Mappings {

        public static final String ROOT = "/";

        public static final String API_V1 = "/api/v1";

        public static final String API_V1_USER = API_V1 + "/users";
        public static final String API_V1_BRAND = API_V1 + "/brands";
        public static final String API_V1_CATEGORY = API_V1 + "/categories";
        public static final String API_V1_PRODUCT = API_V1 + "/products";
        public static final String API_V1_STORE = API_V1 + "/stores";

    }

    public static class Templates {

    }

    public static class Privileges {

        public static final Integer ADMIN = 0;
        public static final Integer OWNER = 3;
        public static final Integer USER = 6;

    }

}