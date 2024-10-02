package hu.elte.snackelf.services;

import hu.elte.snackelf.entities.Room;
import hu.elte.snackelf.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(String name) {

        Room room = Room.builder()
                .name(name)
                .token(UUID.randomUUID())
                .closed(false)
                .createdAt(LocalDateTime.now())
                .build();

        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Optional<Room> getRoomByToken(UUID token) {
        return roomRepository.findByToken(token);
    }

    public Room closeRoom(Room room, String aggregatedResults) {
        room.setClosed(true);
        room.setAggregatedResults(aggregatedResults);
        return roomRepository.save(room);
    }

}
