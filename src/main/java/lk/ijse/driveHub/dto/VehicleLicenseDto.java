package lk.ijse.driveHub.dto;

import java.sql.Date;

public class VehicleLicenseDto {
    private int id;
    private int vehicleId;

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

    public long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public VehicleLicenseDto(int id, int vehicleId, long licenseNumber, Date issueDate, Date expiryDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.licenseNumber = licenseNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public VehicleLicenseDto() {
    }

    private long licenseNumber;
    private Date issueDate;
    private Date expiryDate;
}
