package model;

import javax.persistence.*;

@Entity
@Table(name = "daily_reports")
public class DailyReport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "earnings")
    private Long earnings;

    @Column(name = "soldCars")
    private Integer soldCars;

    public DailyReport() {

    }

    public DailyReport(Long earnings, Integer soldCars) {
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public Integer getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(Integer soldCars) {
        this.soldCars = soldCars;
    }
}
