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
    private double amount;
    private String type;
    private Text about;
    private LocalDate date;

}
