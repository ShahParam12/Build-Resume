import java.util.*;

class playFair {
    static String elemDup(String s) {
        int j, index = 0, len = s.length();
        char c[] = s.toCharArray();
        for (int i = 0; i < len; i++) {
            for (j = 0; j < i; j++) {
                if (c[i] == c[j])
                    break;
            }
            if (i == j)
                c[index++] = c[i];
        }
        s = new String((Arrays.copyOf(c, index)));
        return s;
    }

    static String remWht(char[] ch, String key) {
        char[] c = key.toCharArray();
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < ch.length; j++) {
                if (c[i] == ch[j])
                    c[i] = ' ';
            }
        }
        key = new String(c);
        key = key.replaceAll(" ", "");
        return key;
    }

    static String pairing(String plainText) {
        String s = "";
        char c = 'a';
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) == ' ')
                continue;
            else {
                c = plainText.charAt(i);
                s += plainText.charAt(i);
            }
            if (i < plainText.length() - 1)
                if (plainText.charAt(i) == plainText.charAt(i + 1))
                    s += "x";
        }
        if (s.length() % 2 != 0)
            s += "x";
        System.out.println("Plain text:");
        System.out.println(s);
        return s;
    }

    static int[] findIndex(char a, char b, char x[][]) {
        int[] y = new int[4];
        if (a == 'j')
            a = 'i';
        else if (b == 'j')
            b = 'i';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (x[i][j] == a) {
                    y[0] = i;
                    y[1] = j;
                } else if (x[i][j] == b) {
                    y[2] = i;
                    y[3] = j;
                }
            }
        }
        if (y[0] == y[2]) {
            y[1] += 1;
            y[3] += 1;
        } else if (y[1] == y[3]) {
            y[0] += 1;
            y[2] += 1;
        }
        for (int i = 0; i < 4; i++)
            y[i] %= 5;
        return y;
    }

    static String encryptPlainText(String plainText, char x[][]) {
        char ch[] = plainText.toCharArray();
        int a[] = new int[4];
        for (int i = 0; i < plainText.length(); i += 2) {
            if (i < plainText.length() - 1) {
                a = findIndex(plainText.charAt(i), plainText.charAt(i + 1), x);
                if (a[0] == a[2]) {
                    ch[i] = x[a[0]][a[1]];
                    ch[i + 1] = x[a[0]][a[3]];
                } else if (a[1] == a[3]) {
                    ch[i] = x[a[0]][a[1]];
                    ch[i + 1] = x[a[2]][a[1]];
                } else {
                    ch[i] = x[a[0]][a[3]];
                    ch[i + 1] = x[a[2]][a[1]];
                }
            }
        }
        plainText = new String(ch);
        return plainText;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String plainText = "RamShyamSita";
        String key = "playfair";
        key = elemDup(key);
        char[] ch = key.toCharArray();
        String st = "abcdefghiklmnopqrstuvwxyz";
        st = remWht(ch, st);
        char[] c = st.toCharArray();
        char[][] x = new char[5][5];
        int indexOfSt = 0, indexOfKey = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (indexOfKey < key.length())
                    x[i][j] = ch[indexOfKey++];
                else
                    x[i][j] = c[indexOfSt++];
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(x[i][j] + " ");
            System.out.println();
        }
        plainText = pairing(plainText);
        plainText = encryptPlainText(plainText, x);
        System.out.println("encrypted text:");
        System.out.println(plainText);
    }
}
