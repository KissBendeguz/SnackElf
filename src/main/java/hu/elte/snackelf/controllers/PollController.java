package hu.elte.snackelf.controllers;

import hu.elte.snackelf.dtos.PollResponseDTO;
import hu.elte.snackelf.entities.PollResponse;
import hu.elte.snackelf.entities.Room;
import hu.elte.snackelf.services.PollService;
import hu.elte.snackelf.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private RoomService roomService;

    /**
     * Endpoint to submit a poll response.
     *
     * @param roomId ID of the room.
     * @param pollResponseDTO Poll response data.
     * @return Confirmation of submission.
     */
    @PostMapping("")
    public ResponseEntity<?> submitPollResponse(
            @RequestParam Long roomId,
            @RequestBody PollResponseDTO pollResponseDTO) {

        Optional<Room> oRoom = roomService.getRoomById(roomId);
        if(oRoom.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Room room = oRoom.get();

        if (room.getClosed()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        PollResponse response = pollService.submitPollResponse(
                room,
                pollResponseDTO.getHappyFoodIds(),
                pollResponseDTO.getSadFoodIds(),
                pollResponseDTO.getNeutralFoodIds()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * (Optional) Endpoint to get aggregated results after closing the room.
     * Since the room is closed via RoomController, this might not be necessary here.
     */

    // Additional endpoints as needed
}
