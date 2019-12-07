package service;

import DAO.CarDao;
import model.Car;
import model.SoldCar;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCar();
    }

    public boolean addCar(String brand, String model, String licensePlate, Long price){
        List<Car> cars = new CarDao(sessionFactory.openSession()).getAllCar();
        long count = cars.stream().filter(x -> x.getBrand().equals(brand)).count();
        if(count < 10){
            new CarDao(sessionFactory.openSession()).addCar(new Car(brand, model, licensePlate, price));
            return true;
        }
        return false;
    }

    public boolean buyCar(String brand, String model, String licensePlate) {
        Car car = new CarDao(sessionFactory.openSession()).getCarByLP(licensePlate);
        if(car != null && brand != null && model != null && licensePlate != null
            && car.getBrand().equals(brand) && car.getModel().equals(model)) {
            new CarDao(sessionFactory.openSession())
                    .addSoldCar(new SoldCar(car.getBrand(), car.getModel(), car.getLicensePlate(), car.getPrice()));
            new CarDao((sessionFactory.openSession())).deleteCar(car);
            return true;
        } else {
            return false;
        }
    }
}