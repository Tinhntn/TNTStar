package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Food;
@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
