package dev.purbanowicz.scheduler.repository;

import dev.purbanowicz.scheduler.entity.Shift;
import dev.purbanowicz.scheduler.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByUser(UserEntity user);
}
