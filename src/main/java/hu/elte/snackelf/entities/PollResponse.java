package hu.elte.snackelf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PollResponse extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "poll_happy_foods",
            joinColumns = @JoinColumn(name = "poll_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> happyFoods;

    @ManyToMany
    @JoinTable(
            name = "poll_sad_foods",
            joinColumns = @JoinColumn(name = "poll_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> sadFoods;

    @ManyToMany
    @JoinTable(
            name = "poll_neutral_foods",
            joinColumns = @JoinColumn(name = "poll_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> neutralFoods;

    private LocalDateTime submittedAt;

}
