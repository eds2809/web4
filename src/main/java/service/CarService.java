package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import javax.servlet.http.HttpServletResponse;
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
        return new CarDao(sessionFactory.openSession()).getAllCars();

    }

    public boolean addCar(Car car) {
        return car.validate() && getAllCarsFromBrand(car.getBrand()).size() < 10 && new CarDao(sessionFactory.openSession()).addCar(car) > 0;
    }

    public List<Car> getAllCarsFromBrand(String brand) {
        return new CarDao(sessionFactory.openSession()).getCarsFromBrand(brand);
    }

    public List<Car> getAllCarsIsPrice() {
        return new CarDao(sessionFactory.openSession()).getAllCarsIsPrice();
    }

    public void deleteAll() {
        new CarDao(sessionFactory.openSession()).deleteAll();
    }

    public Car getCar(Car car) {
        Car c = new CarDao(sessionFactory.openSession()).getCar(car);
        return car.validateWithoutPrice() && c != null ? c : null;
    }

    public void update(Car car) {
        new CarDao(sessionFactory.openSession()).update(car);
    }

    public void deletePricedCar() {
        new CarDao(sessionFactory.openSession()).deletePricedCar();
    }

    public boolean priceCar(Car car) {
        Car c = CarService.getInstance().getCar(car);
        if (c != null) {
            c.setSale(true);
            update(c);
            return true;
        }
        return false;
    }
}
