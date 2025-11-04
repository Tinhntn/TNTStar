package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.MovieFormat;

@Repository
public interface MovieFormatRepository extends JpaRepository<MovieFormat, Integer> {
}
