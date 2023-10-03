public enum AdminCredentials {

        ADMIN_NAME("admin"),
        ADMIN_PASSWORD("12345");

        private final String value;

        AdminCredentials(String value){
                this.value = value;
        }

        public String getValue() {
                return value;
        }
}