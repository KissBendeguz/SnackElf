package hu.elte.snackelf.services;

import hu.elte.snackelf.entities.Food;
import hu.elte.snackelf.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Set<Food> getFoods(){
        return new HashSet<>(foodRepository.findAll());
    }
}
