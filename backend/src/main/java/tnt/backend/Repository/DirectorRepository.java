package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
