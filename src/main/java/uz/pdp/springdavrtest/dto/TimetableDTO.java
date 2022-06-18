package uz.pdp.springdavrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springdavrtest.entity.Day;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TimetableDTO {

    private Long dayId;
    //vaqt
    private String startTime;
    private String endTime;
    private List<Long> groupIds;
}
