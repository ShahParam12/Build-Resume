import java.util.*;

class ROT13 {
    public static final String abc = "abcdefghijklmnopqrstuvwxyz";

    public static String en(String message, int shiftKey) {
        message = message.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < message.length(); i++) {
            int charPosition = abc.indexOf(message.charAt(i));
            int keyVal = (shiftKey + charPosition) % 26;
            char rVal = abc.charAt(keyVal);
            cipherText += rVal;
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, int shiftKey) {
        cipherText = cipherText.toLowerCase();
        String message = "";
        for (int j = 0; j < cipherText.length(); j++) {
            int charPosition = abc.indexOf(cipherText.charAt(j));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0) {
                keyVal = abc.length() + keyVal;
            }
            char replaceVal = abc.charAt(keyVal);
            message += replaceVal;
        }
        return message;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = new String();
        int key = 13;
        System.out.print("Enter the String for Encryption:");
        message = sc.next();
        String encryptMsg = en(message, key);
        System.out.println("\nEncrpyted msg:" + encryptMsg);
        System.out.println("Decrypted Message:" + decrypt(encryptMsg, key));
        sc.close();
    }
}
