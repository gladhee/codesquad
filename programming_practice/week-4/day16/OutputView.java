import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int DATA_START = 2;
    private static final int ERROR_START = 23;
    private static final int LEN_INDEX = 1;

    private final List<Integer> decode;

    public OutputView(List<Integer> decode) {
        this.decode = decode;
    }

    public void printDecode() {
        String data = String.format("\"%s\"", getData());
        String error = String.format("\"0x%s\"", getError());

        System.out.printf("data = %s, error = %s\n", data, error);
        System.out.println(List.of(data, error));
    }

    private String getData() {
        int len = decode.get(LEN_INDEX);

        return decode.subList(DATA_START, len + DATA_START)
                .stream()
                .map(Convertor::convertCode)
                .collect(Collectors.joining());
    }

    private String getError() {
        return decode.subList(ERROR_START, decode.size())
                .stream()
                .map(Convertor::convertHex)
                .collect(Collectors.joining());
    }

}
