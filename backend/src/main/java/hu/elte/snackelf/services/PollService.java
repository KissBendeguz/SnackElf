package hu.elte.snackelf.services;

import hu.elte.snackelf.entities.Food;
import hu.elte.snackelf.entities.PollResponse;
import hu.elte.snackelf.entities.Room;
import hu.elte.snackelf.repositories.FoodRepository;
import hu.elte.snackelf.repositories.PollResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollResponseRepository pollResponseRepository;

    @Autowired
    private FoodRepository foodRepository;

    public PollResponse submitPollResponse(Room room, List<Long> happyFoodIds, List<Long> sadFoodIds, List<Long> neutralFoodIds) {
        List<Food> happyFoods = foodRepository.findAllById(happyFoodIds);
        List<Food> sadFoods = foodRepository.findAllById(sadFoodIds);
        List<Food> neutralFoods = foodRepository.findAllById(neutralFoodIds);

        PollResponse response = PollResponse.builder()
                .room(room)
                .happyFoods(happyFoods)
                .sadFoods(sadFoods)
                .neutralFoods(neutralFoods)
                .submittedAt(LocalDateTime.now())
                .build();

        return pollResponseRepository.save(response);
    }

    public List<PollResponse> getPollResponses(Room room) {
        return pollResponseRepository.findByRoom(room);
    }

}
