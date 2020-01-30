package DAO;

import model.DailyReport;
import org.hibernate.Query;
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

    public DailyReport getLastReport() {
        Transaction transaction = session.beginTransaction();
        DailyReport dailyReports = (DailyReport) session.createQuery("FROM DailyReport ORDER BY id DESC")
                .setMaxResults(1)
                .uniqueResult();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public long addReport(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        long id = (long) session.save(dailyReport);
        transaction.commit();
        session.close();
        return id;
    }

    public void deleteAll() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from DailyReport");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
