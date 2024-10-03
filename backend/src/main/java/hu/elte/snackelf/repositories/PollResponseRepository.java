package hu.elte.snackelf.repositories;

import hu.elte.snackelf.entities.PollResponse;
import hu.elte.snackelf.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollResponseRepository extends JpaRepository<PollResponse, Long> {
    List<PollResponse> findByRoom(Room room);
}
