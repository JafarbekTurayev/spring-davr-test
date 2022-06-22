package uz.pdp.springdavrtest.entity;

import lombok.*;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author "Husniddin Ulachov"
 */
@Entity
@Table(name = "time_table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Day day;
    private Date startTime;
    private Date endTime;
    @ManyToMany
    @JoinTable(name = "group_time_table",
            joinColumns = @JoinColumn(name = "time_table_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;
}