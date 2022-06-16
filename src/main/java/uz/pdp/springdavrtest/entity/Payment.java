package uz.pdp.springdavrtest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double sum;
    private String description;

    @ManyToOne
    private Student student;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    private PayType payType;
}

