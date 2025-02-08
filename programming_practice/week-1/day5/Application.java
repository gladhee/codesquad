public class Application {

    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();
        CardFactory cardFactory = new CardFactory();

        GameApplication gameApplication = new GameApplication(input, output, cardFactory);
        gameApplication.run();
    }

}
