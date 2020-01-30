package model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "price")
    private Long price;

    @Column(name = "isSale")
    private boolean isSale;

    public Car() {

    }

    public Car(String brand, String model, String licensePlate, Long price, boolean isSale) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.price = price;
        this.isSale = isSale;
    }

    public Car(String brand, String model, String licensePlate) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public Car(String brand, String model, String licensePlate, Long price) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean isSale) {
        this.isSale = isSale;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean validateWithoutPrice() {
        return !brand.isEmpty() && !model.isEmpty() && !licensePlate.isEmpty();
    }

    public boolean validate() {
        return !brand.isEmpty() && !model.isEmpty() && !licensePlate.isEmpty() && price > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car that = (Car) o;

        if (!getBrand().equals(that.getBrand())) return false;
        if (!getModel().equals(that.getModel())) return false;
        if (!getLicensePlate().equals(that.getLicensePlate())) return false;
        return getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getBrand().hashCode();
        result = 31 * result + getModel().hashCode();
        result = 31 * result + getLicensePlate().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
