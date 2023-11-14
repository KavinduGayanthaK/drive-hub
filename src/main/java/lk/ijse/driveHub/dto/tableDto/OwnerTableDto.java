package lk.ijse.driveHub.dto.tableDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OwnerTableDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String mobileNumber;
    private String email;
}
