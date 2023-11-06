package lk.ijse.driveHub.dto;

import java.sql.Date;

public class OfferDetailDto {
    private int id;
    private int vehicleId;
    private long offerId;
    private Date startDate;
    private Date endDate;
    private String code;

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

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OfferDetailDto(int id, int vehicleId, long offerId, Date startDate, Date endDate, String code) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.offerId = offerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.code = code;
    }

    public OfferDetailDto() {
    }
}
