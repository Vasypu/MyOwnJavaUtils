import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class MyStringUtils {
    public static String join(@Nullable String delimiter, @Nullable String... params) {
        // не выделялась лишняя память - не создавалось промежуточных строк
        // обрабатывались краевые случаи

        if (delimiter != null && params != null) {
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i] + delimiter);
            }
        }

        return "";
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

        join(" ", stringBuilder.toString());
    }

    public static class MyStringBuilder {
        String[] strings;

        public MyStringBuilder() {
            strings = new String[1];
        }

        public MyStringBuilder(int capacity) {
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

        public String toString() {
            String str = "";
            for (int i = 0; i < strings.length; i++) {
                str = strings[i];
            }
            return str;
        }
    }
}