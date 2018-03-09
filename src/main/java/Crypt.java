import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Crypt {
    public String synhEncryptionLinear(int a, int b, int m,String y_0, String text) {
        String encryptionText = "";
        int[] tmp = new int [text.length()];
        BigInteger[] inc = stringToByte(text);
        BigInteger y_0_Byte = stringToByte(y_0)[0];
        int[] y = new int[text.length()];
        y[0] = stringToByte(y_0)[0].byteValue();
        tmp[0] += inc[0].byteValue() ^ y[0];
        encryptionText += String.valueOf(Character.toChars(tmp[0]));
        for (int i = 1; i < text.length(); i++) {
            y[i] = (a * y[i-1] + b) % m;
            tmp[i] += inc[i].byteValue() ^ y[i];
            encryptionText += String.valueOf(Character.toChars(tmp[i]));
        }
        return encryptionText;
    }

    public BigInteger[] stringToByte(String text) {
        String encoding = "UTF-8";
        char [] charArray = text.toCharArray ();
        String[] tmp = new String[charArray.length];
        BigInteger[] solve = new BigInteger[tmp.length];
        for (int i = 0; i < charArray.length; i++) {
            tmp[i] = "" + charArray[i];
        }

        try
        {
            for (int i = 0; i < tmp.length; i++) {
                solve[i] = new BigInteger(1, tmp[i].getBytes(encoding));
//                .toString(2)
            }

        }
        catch (UnsupportedEncodingException e)
        {
        }
        return solve;
    }
}
