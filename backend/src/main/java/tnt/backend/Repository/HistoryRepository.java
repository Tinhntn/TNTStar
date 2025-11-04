package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.History;
@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
}
