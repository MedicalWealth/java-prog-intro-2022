public class SumOctal {
    public static void main(String[] args) {
        int answer = 0;
        for (String str: args) {
            int pointer = 0;
            StringBuilder sb = new StringBuilder();
            while (pointer < str.length()) {
                while (pointer < str.length() && Character.isWhitespace(str.charAt(pointer))) {
                    pointer++;
                }
                if (pointer < str.length()) {
                    sb.append(str.charAt(pointer++));
                }
                while (pointer < str.length() &&
                      (Character.isDigit(str.charAt(pointer)) || Character.toLowerCase(str.charAt(pointer)) == 'o')) {
                    sb.append(str.charAt(pointer++));
                }
                if (!sb.isEmpty()) {
                    answer +=
                        (Character.toLowerCase(str.charAt(pointer - 1)) == 'o')
                            ? Integer.parseUnsignedInt(sb.substring(0, sb.length() - 1), 8)
                            : Integer.parseInt(sb.toString());
                }
                sb.setLength(0);
            }
        }
        System.out.println(answer);
    }
}