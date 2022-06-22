package uz.pdp.springdavrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TimeTableDto {
    private Long dayId;
    private String startTime;
    private String endTime;
    private List<Long> groupIds;
}
