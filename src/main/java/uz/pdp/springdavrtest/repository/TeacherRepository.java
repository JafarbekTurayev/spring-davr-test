package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Room;
import uz.pdp.springdavrtest.entity.Teacher;

//@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}

