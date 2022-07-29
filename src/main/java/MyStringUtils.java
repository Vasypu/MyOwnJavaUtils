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

        stringBuilderTwo.insertTwo(32, "sad");
        System.out.println(stringBuilderTwo);
        stringBuilderTwo.insertTwo(4, "!!!");

        stringBuilderTwo.insertThree(5, "SomeSome");
    }

    public static class MyStringBuilder {
        private String[] strings;
        private int capacity = 0;
        private int stringLength = 0;
        private int fullLength = 0;

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
                String[] newStrings = new String[strings.length * 2];
                System.arraycopy(strings, 0, newStrings, 0, strings.length);
                strings = newStrings;
            }
            strings[capacity++] = str;
            stringLength += str.length();
        }

        public void insert(int offset, String str) {
            String[] newStrings = new String[strings.length + 1];
            capacity++;
            stringLength += str.length();
            int stringsItem = 0;
            for (int i = 0; i < newStrings.length; i++) {
                if (i == offset) {
                    newStrings[i] = str;
                } else {
                    newStrings[i] = strings[stringsItem++];
                }
            }
            strings = newStrings;
        }

        public void insertTwo(int offset, String str) {
            int endRecord = 0;
            char[] chars = new char[stringLength + str.length()];
            if (offset < chars.length - str.length()) {
                for (String string : strings) {
                    for (int i = 0; i < string.length(); i++) {
                        if (endRecord == offset) {
                            endRecord = writeNewStr(chars, endRecord, str);
                            i--;
                        } else {
                            chars[endRecord++] = string.charAt(i);
                        }
                    }
                }
            } else {
                for (String string : strings) {
                    for (int i = 0; i < string.length(); i++) {
                        chars[endRecord++] = string.charAt(i);
                    }
                }
                writeNewStr(chars, endRecord, str);
            }
            System.out.println("новый массив " + new String(chars));
        }

        private Integer writeNewStr(char[] chars, int endRecord, String str) {
            for (int k = 0; k < str.length(); k++) {
                chars[endRecord++] = str.charAt(k);
            }
            return endRecord;
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

        void insertFour(int offset, String str) {
            int indexPos = 0;
            outer:
                for (int i = 0; i < strings.length; i++) {
                    for (int j = 0; j < strings[i].length(); j++) {
                        if (indexPos == offset) {
                            for (int k = strings.length - 1; k > i; k--) {
                                strings[k] = strings[k - 1];
                            }
                            strings[i] = str;
                            break outer;
                        }
                        indexPos++;
                    }
                }

            for (String string : strings) {
                System.out.println(string);
            }
        }

        public Integer getFullLength() {
            return strings.length;
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