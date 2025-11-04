package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Format;

@Repository
public interface FormatRepository extends JpaRepository<Format, Integer> {
}
