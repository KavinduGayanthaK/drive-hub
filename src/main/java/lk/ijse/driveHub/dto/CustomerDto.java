package lk.ijse.driveHub.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDto implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String number;
    private String nic;
    private String email;
    private String isUtilityBillSoftCopy;
    private String isNicSoftCopy;
    private String status;

}
