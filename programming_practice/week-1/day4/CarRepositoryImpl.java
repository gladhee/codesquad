import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepositoryImpl implements CarRepository {

    private final Map<String, Car> cars = new HashMap<>();

    public CarRepositoryImpl(Database database) {
        List<String> data = database.getData();
        initData(data);
        System.out.println(cars);
    }

    private void initData(List<String> data) {
        for (String line : data) {
            String[] parts = line.split(",");
            Car car = Car.builder()
                    .name(parts[0])
                    .category(CarCategory.fromString(parts[1]))
                    .passengerCapacity(Integer.parseInt(parts[2]))
                    .introductionYearMonth(Integer.parseInt(parts[3]))
                    .discontinuationYearMonth(Integer.parseInt(parts[4]))
                    .discontinued(Boolean.parseBoolean(parts[5]))
                    .build();

            save(car);
        }
    }

    @Override
    public void save(Car car) {
        cars.put(car.getName(), car);
    }

    @Override
    public List<Car> findAll() {
        return List.copyOf(cars.values());
    }

}
