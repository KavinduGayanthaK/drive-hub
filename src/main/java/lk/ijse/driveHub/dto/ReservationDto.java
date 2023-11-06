package lk.ijse.driveHub.dto;

import java.sql.Date;

public class ReservationDto {
    private int id;
    private int vehicleId;
    private int customerId;
    private Date reservationDate;

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public ReservationDto(int id, int vehicleId, int customerId, Date reservationDate, Date returnDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.returnDate = returnDate;
    }

    public ReservationDto() {
    }

    private Date returnDate;
}
