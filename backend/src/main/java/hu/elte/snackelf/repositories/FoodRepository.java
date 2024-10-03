package hu.elte.snackelf.repositories;

import hu.elte.snackelf.entities.Food;
import hu.elte.snackelf.entities.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByType(FoodType type);
}
