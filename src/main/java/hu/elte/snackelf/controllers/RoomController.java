package hu.elte.snackelf.controllers;

import hu.elte.snackelf.dtos.RoomDTO;
import hu.elte.snackelf.entities.Room;
import hu.elte.snackelf.services.RoomService;
import hu.elte.snackelf.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Endpoint to create a new room.
     *
     * @param name Name of the room.
     * @return RoomDTO containing room details and token.
     */
    @PostMapping("/create")
    public ResponseEntity<RoomDTO> createRoom(@RequestParam String name) {
        Room room = roomService.createRoom(name);
        RoomDTO roomDTO = MapperUtil.toRoomDTO(room);
        return ResponseEntity.ok(roomDTO);
    }

    /**
     * Endpoint to close a room.
     *
     * @param token UUID token of the room creator.
     * @return Aggregated poll results.
     */
    @PostMapping("/close")
    public ResponseEntity<?> closeRoom(@RequestParam UUID token) {
        Optional<Room> oRoom = roomService.getRoomByToken(token);
        if (oRoom.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Room room = oRoom.get();

        if (room.getClosed()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String aggregatedResults = MapperUtil.aggregatePollResults(room.getPollResponses());

        roomService.closeRoom(room, aggregatedResults);

        return ResponseEntity.ok(aggregatedResults);
    }

    /**
     * Endpoint to get room details.
     *
     * @param id Room ID.
     * @return RoomDTO containing room details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable Long id) {
        Optional<Room> oRoom = roomService.getRoomById(id);
        if (oRoom.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Room room = oRoom.get();

        RoomDTO roomDTO = MapperUtil.toRoomDTO(room);
        return ResponseEntity.status(HttpStatus.OK).body(roomDTO);
    }

    // Additional endpoints as needed
}
