import java.util.stream.Collectors;

public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String searchByYearMonthAndCapacity(int yearMonth, int passengerCapacity) {
        return carRepository.findAll().stream()
                .filter(car -> car.isActiveIn(yearMonth))
                .filter(car -> car.canCarry(passengerCapacity))
                .sorted()
                .map(Car::toString)
                .collect(Collectors.joining(","));
    }

}
