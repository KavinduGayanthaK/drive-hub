package lk.ijse.driveHub.dto;

import java.sql.Date;

public class VehicleInsuranceDto {
    private int id;
    private int vehicleId;
    private long insuranceNumber;
    private Date issueDate;

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

    public long getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(long insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
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

    public VehicleInsuranceDto(int id, int vehicleId, long insuranceNumber, Date issueDate, Date expiryDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.insuranceNumber = insuranceNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public VehicleInsuranceDto() {
    }

    private Date expiryDate;
}
