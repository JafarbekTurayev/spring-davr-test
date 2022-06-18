package uz.pdp.springdavrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.entity.Room;
import uz.pdp.springdavrtest.entity.Status;
import uz.pdp.springdavrtest.entity.Teacher;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupDTO {
    private Long id;
    private String name;
    private Long courseId;
    private Long teacherId;
    private String startDate;
    private String endDate;
    private Long roomId;
    private Long statusId;
}
