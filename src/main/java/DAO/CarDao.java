package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createCriteria(Car.class)
                .add(Restrictions.eq("isSale",false))
                .list();
        transaction.commit();
        session.close();
        return cars;
    }

    public long addCar(Car car) {
        Transaction transaction = session.beginTransaction();
        long id = (long) session.save(car);
        transaction.commit();
        session.close();
        return id;
    }

    public List<Car> getCarsFromBrand(String brand) {
        List<Car> cars = session.createCriteria(Car.class)
                .add(Restrictions.eq("isSale",false))
                .add(Restrictions.eq("brand", brand))
                .list();
        session.close();
        return cars;
    }

    public List<Car> getAllCarsIsPrice() {
        List<Car> cars = session.createCriteria(Car.class)
                .add(Restrictions.eq("isSale", true))
                .list();
        session.close();
        return cars;
    }

    public void deleteAll() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Car");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public Car getCar(Car carDataSet) {
        Car car = (Car) session.createCriteria(Car.class)
                .add(Restrictions.eq("brand", carDataSet.getBrand()))
                .add(Restrictions.eq("model", carDataSet.getModel()))
                .add(Restrictions.eq("licensePlate", carDataSet.getLicensePlate()))
                .add(Restrictions.eq("isSale", false))
                .setMaxResults(1).uniqueResult();
        session.close();
        return car;
    }

    public void update(Car car) {
        Transaction transaction = session.beginTransaction();
        session.update(car);
        transaction.commit();
        session.close();
    }

    public void deletePricedCar() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Car WHERE isSale = true");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
