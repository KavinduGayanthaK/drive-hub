package lk.ijse.driveHub.dto;

import java.util.Date;

public class VehicleDto {
    private int id;
    private String brand;
    private boolean isCollectedBookCopy;
    private String registerNumber;
    private Enum transmissionType;

    private String model;
    private int vehicleTypeId;
    private int ownerId;
    private Date manufactureYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isCollectedBookCopy() {
        return isCollectedBookCopy;
    }

    public void setCollectedBookCopy(boolean collectedBookCopy) {
        isCollectedBookCopy = collectedBookCopy;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Enum getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(Enum transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Date manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public VehicleDto(int id, String brand, boolean isCollectedBookCopy, String registerNumber, Enum transmissionType, String model, int vehicleTypeId, int ownerId, Date manufactureYear) {
        this.id = id;
        this.brand = brand;
        this.isCollectedBookCopy = isCollectedBookCopy;
        this.registerNumber = registerNumber;
        this.transmissionType = transmissionType;
        this.model = model;
        this.vehicleTypeId = vehicleTypeId;
        this.ownerId = ownerId;
        this.manufactureYear = manufactureYear;
    }

    public VehicleDto() {
    }
}
