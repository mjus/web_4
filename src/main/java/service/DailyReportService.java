package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return new DailyReportDao(sessionFactory.openSession()).getLastReport();
    }

    public void createDailyReport() {
        //создается отчет из текущего дня
        DailyReport dailyReport;
        if(new DailyReportDao(sessionFactory.openSession()).isTableSoldCarsEmpty()){
            dailyReport = new DailyReport(0L,0L);
        } else {
            dailyReport = new DailyReport(new DailyReportDao(sessionFactory.openSession()).getEarnings()
                    , new DailyReportDao(sessionFactory.openSession()).getSoldCars());
        }
        //добавляется в отчеты
        new DailyReportDao(sessionFactory.openSession()).addReport(dailyReport);
        new DailyReportDao(sessionFactory.openSession()).cleanSoldCars();
//        new DailyReportDao(sessionFactory.openSession()).getLastReport();
    }

    public void delete() {
        new DailyReportDao(sessionFactory.openSession()).delete();
    }
}