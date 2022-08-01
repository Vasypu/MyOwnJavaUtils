public class MyStringUtils {

    public static String join(String delimiter, String... params) {
        // не выделялась лишняя память - не создавалось промежуточных строк
        // обрабатывались краевые случаи
        if (delimiter == null || params == null) return "";
        MyStringBuilder builder = new MyStringBuilder(params.length * 2);
        for (int i = 0; i < params.length; i++) {
            if (i != params.length - 1) {
                builder.append(params[i]);
                builder.append(delimiter);
            } else if (params[i] != null) {
                builder.append(params[i]);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        MyStringBuilder stringBuilder = new MyStringBuilder();
        stringBuilder.append("sdf14");
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
        System.out.println("пустой stringBuilderThree " + stringBuilderThree);

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

        System.out.println(join(" ! ", stringBuilder.getMyStringBuilder()));
        System.out.println(stringBuilder);

        StringBuilder builder = new StringBuilder(14);
        builder.append("some1");
        builder.append("some2");
        builder.append("some3");
        builder.append("some4");
        System.out.println(builder);
        builder.insert(20, "NewStr");
        System.out.println(builder);

//        stringBuilderTwo.insertThree(5, "SomeSome");
    }

    public static class MyStringBuilder {
        private String[] strings;
        private int capacity = 0;
        private int stringLength = 0;

        public MyStringBuilder() {
            strings = new String[4];
        }

        public MyStringBuilder(int capacity) {
            strings = new String[capacity];
        }

        public void append(String str) {
            if (str == null) {
                System.out.println("Значения не могут быть null");
                return;
            }
            if (capacity >= strings.length) {
                strings = ensureCapacity(strings, strings.length * 2);
            }
            strings[capacity++] = str;
            stringLength += str.length();
        }

        public void insertThree(int offset, String str) {
            String[] newStrings = new String[strings.length + 1];
            capacity++;
            stringLength += str.length();
            int stringsItem = 0;
            int countLength = 0;
            int remainStrLength = 0;
            for (int i = 0; i < newStrings.length; i++) {
                countLength += strings[stringsItem].length();
                if (offset <= countLength) {
                    countLength -= strings[stringsItem].length();
                    for (int j = 0; j < strings[stringsItem].length(); j++) {
                        if (countLength != 0)
                            countLength++;
                        if (countLength == offset) {
                            if (j == 0) {
                                newStrings[i++] = str;
                                break;
                            }
                            char[] chars = new char[j];
                            for (int k = 0; k < chars.length; k++) {
                                chars[k] = strings[stringsItem].charAt(k);
                                remainStrLength = k + 1;
                            }
                            String s = new String(chars);
                            newStrings[i++] = s;
                            chars = new char[strings[stringsItem].length() - remainStrLength];
                            for (int d = 0, k = remainStrLength; k < strings[stringsItem].length(); k++) {
                                chars[d++] = strings[stringsItem].charAt(k);
                            }
                            s = str + new String(chars);
                            newStrings[i++] = s;
                        }
                    }

//                    newStrings[i++] = str;
                }
                newStrings[i] = strings[stringsItem++];
            }

            for (String newString : newStrings) {
                System.out.println(newString);
            }
        }

        public void insert(int offset, String str) {
            int indexPos = 0;
            int strCounter = 0;
            int posInStr = 0;

            if (offset >= stringLength) {
                append(str);
                return;
            }

            for (int i = 0; i < capacity; i++) {
                for (int j = 0; j < strings[i].length(); j++) {
                    if (indexPos == offset) {
                        strCounter = i;
                        posInStr = j;
                    }
                    indexPos++;
                }
            }

            if (strings.length - capacity <= 1) {
                strings = ensureCapacity(strings, strings.length * 2);
            }

            if (posInStr == 0) {
                if (strings.length - 1 - strCounter >= 0)
                    System.arraycopy(strings, strCounter, strings, strCounter + 1, strings.length - 1 - strCounter);
                strings[strCounter] = str;
            } else {
                if (capacity - strCounter >= 0)
                    System.arraycopy(strings, strCounter, strings, strCounter + 1 + 1, capacity - strCounter);
                strings[strCounter + 1] = str;
                strings[strCounter + 2] = strings[strCounter].substring(posInStr);
                strings[strCounter] = strings[strCounter].substring(0, posInStr);
                capacity++;
            }
            stringLength += str.length();
            capacity++;
        }

        private static String[] ensureCapacity(String[] strings, int newCapacity) {
            String[] newStrings = new String[newCapacity];
            System.arraycopy(strings, 0, newStrings, 0, strings.length);
            return newStrings;
        }

        public String[] getMyStringBuilder() {
            return strings;
        }

        public String toString() {
            if (stringLength == 0) return "";
            int endRecord = 0;
            char[] chars = new char[stringLength];
            for (int j = 0; j < capacity; j++) {
                String string = strings[j];
                for (int i = 0; i < string.length(); i++) {
                    chars[endRecord++] = string.charAt(i);
                }
            }
            return new String(chars);
        }

    }
}