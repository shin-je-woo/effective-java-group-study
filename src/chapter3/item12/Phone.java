package chapter3.item12;
    public enum Phone {
        SamSung("galaxy", "23"),
        Apple("iphone", "14");

        private final String name;
        private final String ver;

        public String getName() {
            return name;
        }

        public String getVer() {
            return ver;
        }

        Phone(String name, String ver) {
            this.name = name;
            this.ver = ver;
        }

//        @Override
//        public String toString() {
//            return "Phone{" +
//                    "name='" + name + '\'' +
//                    ", ver='" + ver + '\'' +
//                    '}';
//        }

        public static void main(String[] args) {
            System.out.println("myFruit = " + Phone.SamSung);

    }
}
