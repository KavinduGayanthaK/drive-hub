package lk.ijse.driveHub.dto;

import java.sql.Date;

public class VehicleMaintainsDto {
    private int id;
    private int vehicleId;
    private int currentMileage;
    private int nextServiceMileage;

    private Date serviceDate;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public int getNextServiceMileage() {
        return nextServiceMileage;
    }

    public void setNextServiceMileage(int nextServiceMileage) {
        this.nextServiceMileage = nextServiceMileage;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public VehicleMaintainsDto(int id, int vehicleId, int currentMileage, int nextServiceMileage, Date serviceDate, double price) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.currentMileage = currentMileage;
        this.nextServiceMileage = nextServiceMileage;
        this.serviceDate = serviceDate;
        this.price = price;
    }

    public VehicleMaintainsDto() {
    }
}
