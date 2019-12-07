package DAO;

import model.DailyReport;
import model.SoldCar;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public List<SoldCar> getAllSoldCars() {
        Transaction transaction = session.beginTransaction();
        List<SoldCar> list = session.createQuery("FROM SoldCar").list();
        transaction.commit();
        session.close();
        return list;
    }

    public DailyReport getLastReport() {
        if(getAllDailyReport().size() == 0){
            return null;
        } else {
            return getAllDailyReport().get(getAllDailyReport().size() - 1);
        }
    }

    public void delete() {
        Transaction transaction = session.beginTransaction();
        session.clear();
        transaction.commit();
        session.close();
    }

    public boolean isTableSoldCarsEmpty(){
        Transaction transaction = session.beginTransaction();
        List<SoldCar> dailyReports = session.createQuery("FROM SoldCar").list();
        int i = dailyReports.size();
        transaction.commit();
        session.close();
        return i == 0;
    }

    public long getEarnings() {
        Transaction transaction = session.beginTransaction();
        List<SoldCar> dailyReports = session.createQuery("FROM SoldCar").list();
        long result = dailyReports.stream().mapToLong(x -> x.getPrice()).sum();
        transaction.commit();
        session.close();
        return result;
    }

    public long getSoldCars() {
        Transaction transaction = session.beginTransaction();
        List<SoldCar> dailyReports = session.createQuery("FROM SoldCar").list();
        int i = dailyReports.size();
        transaction.commit();
        session.close();
        return i;
    }

    public void addReport(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public void cleanSoldCars() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from SoldCar").executeUpdate();
        transaction.commit();
        session.close();
    }
}