package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Position;

@Repository
public interface PositionRepositoy extends JpaRepository<Position, Integer> {
}
