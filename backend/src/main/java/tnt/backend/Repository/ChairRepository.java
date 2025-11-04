package tnt.backend.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Chairs;
@Repository
public interface ChairRepository extends JpaRepository<Chairs, Integer> {
    Page<Chairs> findAll(Pageable pageable);
}
