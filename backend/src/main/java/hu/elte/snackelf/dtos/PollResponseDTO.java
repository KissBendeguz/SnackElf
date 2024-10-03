package hu.elte.snackelf.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PollResponseDTO {
    private List<Long> happyFoodIds;
    private List<Long> sadFoodIds;
    private List<Long> neutralFoodIds;

}
