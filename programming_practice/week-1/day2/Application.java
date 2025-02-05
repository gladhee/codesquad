import java.util.List;

public class Application {

    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();
        Player A = new Player();
        Player B = new Player();
        DartApplication dartApplication = new DartApplication(input, output, A, B);

        dartApplication.run();
    }

}
