package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Room;
import uz.pdp.springdavrtest.entity.Student;

//@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

