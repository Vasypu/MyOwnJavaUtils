
public class MyStringUtils {

    public static String join(String delimiter, String... params) {
        // не выделялась лишняя память - не создавалось промежуточных строк
        // обрабатывались краевые случаи
        MyStringBuilder builder = null;
        if (delimiter != null && params != null) {
            builder = new MyStringBuilder(params.length * 2);
            for (int i = 0; i < params.length; i++) {
                if (i != params.length - 1) {
                    builder.append(params[i]);
                    builder.append(delimiter);
                } else if (params[i] != null) {
                    builder.append(params[i]);
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        MyStringBuilder stringBuilder = new MyStringBuilder();
        stringBuilder.append("sdf1");
        stringBuilder.append("sdf2");
        stringBuilder.append("sdf3");
        stringBuilder.append("sdf4");
        System.out.println(stringBuilder);

        MyStringBuilder stringBuilderTwo = new MyStringBuilder(4);
        stringBuilderTwo.append("sdf1");
        stringBuilderTwo.append("sdf2");
        stringBuilderTwo.append("sdf3");
        stringBuilderTwo.append("sdf4");
        stringBuilderTwo.append("sdf5");
        stringBuilderTwo.append("sdf6");
        stringBuilderTwo.append("sdf7");
        stringBuilderTwo.append("sdf8");
        System.out.println(stringBuilderTwo);

        MyStringBuilder stringBuilderThree = new MyStringBuilder(4);
        stringBuilderThree.append(null);
        stringBuilderThree.append(null);
        stringBuilderThree.append(null);
        System.out.println(stringBuilderThree);

        MyStringBuilder stringBuilderFour = new MyStringBuilder(4);
        stringBuilderFour.append("stringBuilderFour1");
        stringBuilderFour.append("stringBuilderFour2");
        stringBuilderFour.append("stringBuilderFour3");
        stringBuilderFour.append("stringBuilderFour4");
        stringBuilderFour.append("stringBuilderFour5");
        stringBuilderFour.append("stringBuilderFour6");
        stringBuilderFour.append("stringBuilderFour7");
        stringBuilderFour.append("stringBuilderFour8");
        System.out.println(stringBuilderFour);

        System.out.println(join("!", stringBuilder.getMyStringBuilder()));
    }

    public static class MyStringBuilder {
        private String[] strings;
        private int fullCapacity = 0;

        public MyStringBuilder() {
            strings = new String[4];
        }

        public MyStringBuilder(int capacity) {
            strings = new String[capacity];
        }

        public void append(String str) {
            if (str != null) {
                if (fullCapacity >= strings.length) {
                    String[] newStrings = new String[strings.length * 2];
                    System.arraycopy(strings, 0, newStrings, 0, strings.length);
                    strings = newStrings;
                }
                strings[fullCapacity++] = str;
            } else {
                System.out.println("Значение не может быть null");
            }
        }

        public String[] getMyStringBuilder() {
            return strings;
        }

        public String toString() {
            String str = "";
            for (int i = 0; i < strings.length; i++) {
                if (i != strings.length - 1) {
                    str += (strings[i]);
                } else if (strings[i] != null) {
                    str += (strings[i]);
                }
            }
            return str;
        }

    }
}