package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.entity.Timetable;

import java.util.UUID;

//@Repository
public interface TimetableRepository extends JpaRepository<Timetable, UUID> {
}

