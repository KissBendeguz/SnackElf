package hu.elte.snackelf.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity{
    private String name;

    @Column(unique = true, nullable = false)
    private UUID token;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollResponse> pollResponses;

    private LocalDateTime createdAt;

    private Boolean closed = false;

    @Lob
    private String aggregatedResults;

}
