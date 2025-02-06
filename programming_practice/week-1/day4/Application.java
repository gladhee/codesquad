public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Database database = new Database();
        CarRepository carRepository = new CarRepositoryImpl(database);

        CarService carService = new CarService(carRepository);
        CarController carController = new CarController(inputView, outputView, carService);

        carController.run();
    }

}
