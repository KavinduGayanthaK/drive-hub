package lk.ijse.driveHub.dto;

import javafx.scene.text.Text;

public class PaymentDto {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public Text getAbout() {
        return about;
    }

    public void setAbout(Text about) {
        this.about = about;
    }

    public PaymentDto(int id, int reservationId, double amount, Enum type, Text about) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.type = type;
        this.about = about;
    }

    public PaymentDto() {
    }

    private int id;
    private int reservationId;
    private double amount;
    private Enum type;
    private Text about;
}
