import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("CSE442: Security1");
        System.out.println(computeHash("hello"));
//        System.out.println(bruteForceAttack("8f434346648f6b96df89dda901c5176b10a6d83961dd3c1ac88b59b2dc327aa4"));
    }

    public static String computeHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes());
        String result = DatatypeConverter.printHexBinary(hash);
        return result;
    }

    public static String bruteForceAttack(String hashValue) throws NoSuchAlgorithmException {
        char[] characterSet = {'a', 'b'};
//        char[] characterSet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//        char[] characterSet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
//                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//        char[] characterSet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
//                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
//                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
//        char[] characterSet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
//                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
//                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
//                '5', '6', '7', '8', '9', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
//                ':' ,';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'};
        int length = 1;
        while (true) {
            System.out.println("Testing all strings of length " + length);
            String attempt = bruteForceAttackRecursion(hashValue.toUpperCase(), characterSet, length, "");
            if (attempt.compareTo("") != 0) {
                return attempt;
            }
            length++;
        }
    }

    private static String bruteForceAttackRecursion(String hashValue, char[] characterSet, int length, String prefix)
            throws NoSuchAlgorithmException {
        if (prefix.length() == length) {
//            System.out.println("testing: " + prefix);
            if (computeHash(prefix).compareTo(hashValue) == 0) {
                return prefix;
            }
        } else {
            for (char nextChar : characterSet) {
                String result = bruteForceAttackRecursion(hashValue, characterSet, length, prefix + nextChar);
                if (result.compareTo("") != 0) {
                    return result;
                }
            }
        }
        return "";
    }
}
