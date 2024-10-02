package hu.elte.snackelf.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.elte.snackelf.dtos.RoomDTO;
import hu.elte.snackelf.entities.PollResponse;
import hu.elte.snackelf.entities.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static RoomDTO toRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setToken(room.getToken());
        dto.setClosed(room.getClosed());
        return dto;
    }

    /**
     * Aggregates poll responses to generate statistics.
     *
     * @param responses List of poll responses.
     * @return Aggregated results as a JSON string.
     */
    public static String aggregatePollResults(List<PollResponse> responses) {
        Map<String, Integer> happyFoodCount = new HashMap<>();
        Map<String, Integer> sadFoodCount = new HashMap<>();
        Map<String, Integer> neutralFoodCount = new HashMap<>();


        for (PollResponse response : responses) {
            if (response.getHappyFoods() != null) {
                response.getHappyFoods().forEach(food ->
                        happyFoodCount.put(food.getName(), happyFoodCount.getOrDefault(food.getName(), 0) + 1)
                );
            }
            if (response.getSadFoods() != null) {
                response.getSadFoods().forEach(food ->
                        sadFoodCount.put(food.getName(), sadFoodCount.getOrDefault(food.getName(), 0) + 1)
                );
            }
            if (response.getNeutralFoods() != null) {
                response.getSadFoods().forEach(food ->
                        neutralFoodCount.put(food.getName(), neutralFoodCount.getOrDefault(food.getName(), 0) + 1)
                );
            }
        }

        ObjectNode results = mapper.createObjectNode();
        results.putPOJO("happyFoods", happyFoodCount);
        results.putPOJO("sadFoods", sadFoodCount);
        results.putPOJO("neutralFoods", neutralFoodCount);

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
        } catch (Exception e) {
            return "{}"; // Return empty JSON in case of error
        }
    }
}
