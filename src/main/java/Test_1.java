public class Test_1 {
    public static void main(String[] args) {
        MyStringUtils.MyStringBuilder builder_5 = new MyStringUtils.MyStringBuilder();
        builder_5.append("000");
        builder_5.append("111");
        builder_5.append("222");
        builder_5.append("333");
        System.out.println(builder_5);
        builder_5.insertFour(5, "444");
    }
}
