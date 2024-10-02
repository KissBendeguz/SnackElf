package hu.elte.snackelf.repositories;

import hu.elte.snackelf.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByToken(UUID token);
}
