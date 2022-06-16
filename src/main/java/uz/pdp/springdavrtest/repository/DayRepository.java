package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Day;
import uz.pdp.springdavrtest.entity.Room;

//@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
}

