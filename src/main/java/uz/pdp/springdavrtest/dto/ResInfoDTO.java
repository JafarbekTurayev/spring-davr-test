package uz.pdp.springdavrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springdavrtest.entity.Group;
import uz.pdp.springdavrtest.entity.Teacher;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResInfoDTO {
    private String dayName;
    private String groupName;
    private String teacherName;
    private String roomName;
}
