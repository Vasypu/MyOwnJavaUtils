
public class MyStringUtils {
    public static String join(String delimiter, String... params) {
        // не выделялась лишняя память - не создавалось промежуточных строк
        // обрабатывались краевые случаи

        String str = "";
        if (delimiter != null && params != null) {
            for (int i = 0; i < params.length; i++) {
                if (i != params.length - 1) {
                    str += (params[i]);
                    str += (delimiter);
                } else if (params[i] != null) {
                    str += (params[i]);
                }
            }
        }
        return str;
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

        System.out.println(join(" ", stringBuilder.getMyStringBuilder()));

    }

    public static class MyStringBuilder {
        String[] strings;
        public int capacity;

        public MyStringBuilder() {
            strings = new String[4];
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
                        String[] newStrings = new String[strings.length * 2];
                        for (int j = 0; j < strings.length; j++) {
                            newStrings[j] = strings[j];
                        }
                        strings = newStrings;
                        strings[i + 1] = str;
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
            String str = "";
            for (int i = 0; i < strings.length; i++) {
                if (i != strings.length - 1) {
                    str += (strings[i]);
                    str += (" ");
                } else if (strings[i] != null) {
                    str += (strings[i]);
                }
            }
            return str;
        }
    }
}