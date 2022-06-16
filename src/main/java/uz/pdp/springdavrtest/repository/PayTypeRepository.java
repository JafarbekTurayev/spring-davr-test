package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.PayType;
import uz.pdp.springdavrtest.entity.Room;

//@Repository
public interface PayTypeRepository extends JpaRepository<PayType, Long> {
}

