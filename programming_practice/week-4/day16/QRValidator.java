import java.util.List;

public class QRValidator {

    private static final int END_INDEX = 22;
    private static final int VALID_START_CODE = 0b1100;
    private static final int VALID_END_CODE = 0b0110;

    private QRValidator() {
    }

    public static void validateDecode(List<Integer> decode) {
        int start = decode.getFirst();
        int end = decode.get(END_INDEX);

        if (start != VALID_START_CODE || end != VALID_END_CODE) {
            throw new IllegalArgumentException("잘못된 QR코드입니다.");
        }
    }

}
