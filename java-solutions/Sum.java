public class Sum {
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
                while (pointer < str.length() && Character.isDigit(str.charAt(pointer))) {
                    sb.append(str.charAt(pointer++));
                }
                answer += sb.isEmpty() ? 0 : Integer.parseInt(sb.toString());
                sb.setLength(0);
            }
        }
        System.out.println(answer);
    }
}
