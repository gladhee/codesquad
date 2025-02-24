import java.util.List;

public class Convertor {

    private Convertor() {
    }

    public static char[][] toCharArray(List<String> lines) {
        char[][] qrCode = new char[21][21];
        for (int i = 0; i < 21; i++) {
            qrCode[i] = lines.get(i).toCharArray();
        }
        return qrCode;
    }

    public static String convertCode(int code) {
        String set = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";

        return set.charAt(code) + "";
    }

    public static String convertHex(int code) {
        return Integer.toHexString(code).toUpperCase();
    }

}
