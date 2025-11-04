package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Row;
@Repository
public interface RowRepository extends JpaRepository<Row,Integer> {
}
