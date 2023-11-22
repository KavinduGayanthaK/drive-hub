package lk.ijse.driveHub.dto;

import javafx.scene.text.Text;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDto {
    private int id;
    private int reservationId;
    private double depositAmount;
    private double amount;
    private String type;
    private String about;
    private LocalDate date;

}
