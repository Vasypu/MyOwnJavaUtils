
public class MyStringUtils {
    public static String join(String delimiter, String... params) {
        // не выделялась лишняя память - не создавалось промежуточных строк
        // обрабатывались краевые случаи

        StringBuilder str = new StringBuilder();
        if (delimiter != null && params != null) {
            for (int i = 0; i < params.length; i++) {
                if (i != params.length - 1) {
                    str.append(params[i]);
                    str.append(delimiter);
                } else if (params[i] != null) {
                    str.append(params[i]);
                }
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        MyStringBuilder stringBuilder = new MyStringBuilder();
        stringBuilder.append("sdf1");
        stringBuilder.append("sdf2");
        stringBuilder.append("sdf3");
        stringBuilder.append("sdf4");
        System.out.println(stringBuilder.toString());

        MyStringBuilder stringBuilderTwo = new MyStringBuilder(4);
        stringBuilderTwo.append("sdf1");
        stringBuilderTwo.append("sdf2");
        stringBuilderTwo.append("sdf3");
        System.out.println(stringBuilderTwo.toString());

        MyStringBuilder stringBuilderThree = new MyStringBuilder(4);
        stringBuilderThree.append(null);
        stringBuilderThree.append(null);
        stringBuilderThree.append(null);
        System.out.println(stringBuilderThree.toString());

        System.out.println(join(" ", stringBuilder.getMyStringBuilder()));
    }

    public static class MyStringBuilder {
        String[] strings;
        public int capacity;

        public MyStringBuilder() {
            strings = new String[1];
        }

        public MyStringBuilder(int capacity) {
            this.capacity = capacity;
            strings = new String[capacity];
        }

        public void append(String str) {
            if (str != null) {
                for (int i = 0; i < strings.length; i++) {
                    if (strings[i] == null) {
                        strings[i] = str;
                        break;
                    } else if (i == strings.length - 1 && strings[strings.length - 1] != null) {
                        String[] objects = new String[strings.length + 1];
                        System.arraycopy(strings, 0, objects, 0, strings.length);
                        strings = new String[strings.length + 1];
                        System.arraycopy(objects, 0, strings, 0, objects.length);
                        strings[strings.length - 1] = str;
                        break;
                    }
                }
            } else {
                System.out.println("Значение не может быть null");
            }
        }

        String[] getMyStringBuilder() {
            return strings;
        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < strings.length; i++) {
                if (i != strings.length - 1) {
                    str.append(strings[i]);
                    str.append(" ");
                } else if (strings[i] != null) {
                    str.append(strings[i]);
                }
            }
            return str.toString();
        }
    }
}