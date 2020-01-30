package service;

import DAO.DailyReportDao;
import model.Car;
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

    public boolean addReport() {
        List<Car> cars = CarService.getInstance().getAllCarsIsPrice();
        long earnings = 0;
        for (Car car : cars) {
            earnings += car.getPrice();
        }

        if (new DailyReportDao(sessionFactory.openSession()).addReport(new DailyReport(earnings, cars.size())) > 0) {
            CarService.getInstance().deletePricedCar();
            return true;
        } else {
            return false;
        }
    }

    public void deleteAll() {
        new DailyReportDao(sessionFactory.openSession()).deleteAll();
    }
}
