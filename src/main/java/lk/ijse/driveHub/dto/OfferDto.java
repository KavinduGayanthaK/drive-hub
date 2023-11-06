package lk.ijse.driveHub.dto;

public class OfferDto {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfferDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public OfferDto() {
    }
}
