package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.MovieActor;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActor, Integer> {
}
