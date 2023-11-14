package lk.ijse.driveHub.dto.tableDto;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerTableDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String email;
    private String mobileNumber;
    private String utilityBill;
    private String nicCopy;

}
