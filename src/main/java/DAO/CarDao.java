package DAO;

import model.Car;
import model.SoldCar;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCar() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("from Car").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public List<SoldCar> getAllSoldCar() {
        Transaction transaction = session.beginTransaction();
        List<SoldCar> cars = session.createQuery("from SoldCar").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public void addCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public Car getCarByLP(String licensePlate) {
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Car.class);
        Car car = (Car) criteria.add(Restrictions.eq("licensePlate", licensePlate)).uniqueResult();
        transaction.commit();
        return car;
    }

    public void deleteCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public void addSoldCar(SoldCar soldCar) {
        Transaction transaction = session.beginTransaction();
        session.save(soldCar);
        transaction.commit();
        session.close();
    }
}