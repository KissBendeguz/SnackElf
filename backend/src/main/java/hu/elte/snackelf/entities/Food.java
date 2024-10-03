package hu.elte.snackelf.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food extends BaseEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private FoodType type;

}