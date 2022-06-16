package uz.pdp.springdavrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDTO {

    private Double sum;
    private String description;
    private Long studentId;
    private Long payTypeId;

}
