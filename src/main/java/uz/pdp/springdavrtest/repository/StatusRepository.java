package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Room;
import uz.pdp.springdavrtest.entity.Status;

//@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}

