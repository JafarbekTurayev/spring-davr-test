package uz.pdp.springdavrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResGroupDTO {
    private String groupName;
    private String courseName;
    private String teacherName;
    private String roomName;
    private String statusName;
    //studentList DTO qaytadi keyinchalik
    private String startDate;
    private String endDate;
}
