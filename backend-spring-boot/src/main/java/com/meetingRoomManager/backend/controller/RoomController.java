package com.meetingRoomManager.backend.controller;

import com.meetingRoomManager.backend.exception.ResourceNotFoundException;
import com.meetingRoomManager.backend.model.Room;
import com.meetingRoomManager.backend.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms () {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById (@PathVariable (value = "id") long roomId)
                                                throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() ->
                                          new ResourceNotFoundException("Room not found:: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom (@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom (@PathVariable (value = "id") Long roomId,
                                            @Valid @RequestBody Room roomDetails)
                                            throws ResourceNotFoundException {

        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() ->
                                          new ResourceNotFoundException("Room not found :: " + roomId));

        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable (value = "id") Long roomId,
                           @Valid @RequestBody Room roomDetails)
                           throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() ->
                                          new ResourceNotFoundException("Room not found :: " + roomId));
        roomRepository.delete(room);
    }
}