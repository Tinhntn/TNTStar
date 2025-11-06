package tnt.backend.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer> {

    Page<Actor> findAll(Pageable pageable);

    boolean existsActorByActorCode(String actorCode);
}
