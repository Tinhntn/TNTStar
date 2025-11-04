package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.DetailedFood;

@Repository
public interface DetailedFoodRepository extends JpaRepository<DetailedFood, Integer> {
}
