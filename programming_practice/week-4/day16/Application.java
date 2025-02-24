import java.util.List;
import java.util.Queue;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        List<String> input = inputView.inputQRCode();
        char[][] qrCode = Convertor.toCharArray(input);
        Queue<MoveReader> moveReaders = DirectionalReadInstruction.getMoveReaders();
        QRDecode qrDecode = QRDecode.of(qrCode, moveReaders);
        List<Integer> decode = qrDecode.decode();

        OutputView outputView = new OutputView(decode);
        outputView.printDecode();
    }

}
