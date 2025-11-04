package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
