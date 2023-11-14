package lk.ijse.driveHub.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class VehicleOwnerDto {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String MobileNumber;
    private String email;


}
