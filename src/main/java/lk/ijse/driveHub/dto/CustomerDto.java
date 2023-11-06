package lk.ijse.driveHub.dto;

import java.io.Serializable;

public class CustomerDto implements Serializable {
    private int id;
    private String nic;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private long number;
    private boolean isUtilityBillSoftCopy;
    private boolean isNicSoftCopy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean isUtilityBillSoftCopy() {
        return isUtilityBillSoftCopy;
    }

    public void setUtilityBillSoftCopy(boolean utilityBillSoftCopy) {
        isUtilityBillSoftCopy = utilityBillSoftCopy;
    }

    public boolean isNicSoftCopy() {
        return isNicSoftCopy;
    }

    public void setNicSoftCopy(boolean nicSoftCopy) {
        isNicSoftCopy = nicSoftCopy;
    }

    public CustomerDto(int id, String nic, String firstName, String lastName, String address, String email, long number, boolean isUtilityBillSoftCopy, boolean isNicSoftCopy) {
        this.id = id;
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.number = number;
        this.isUtilityBillSoftCopy = isUtilityBillSoftCopy;
        this.isNicSoftCopy = isNicSoftCopy;
    }

    public CustomerDto() {
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", nic='" + nic + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                ", isUtilityBillSoftCopy=" + isUtilityBillSoftCopy +
                ", isNicSoftCopy=" + isNicSoftCopy +
                '}';
    }
}
