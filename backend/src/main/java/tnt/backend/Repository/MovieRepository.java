package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
