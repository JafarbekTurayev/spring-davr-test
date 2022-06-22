package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springdavrtest.entity.TimeTable;


@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

}

