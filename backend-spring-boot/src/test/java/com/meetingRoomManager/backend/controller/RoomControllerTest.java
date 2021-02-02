package com.meetingRoomManager.backend.controller;

import com.meetingRoomManager.backend.exception.ResourceNotFoundException;
import com.meetingRoomManager.backend.model.Room;
import com.meetingRoomManager.backend.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;

public class RoomControllerTest {

    private final RoomRepository roomRepository = Mockito.mock(RoomRepository.class);

    @Test
    @DisplayName("This should pass when the initial list of rooms is empty.")
    void shouldReturnEmptyListOfRooms() {
        Mockito.when(roomRepository.findAll()).thenReturn(null);
    }

    @Test
    @DisplayName("This should pass when a new room is sucessfuly created.")
    void shouldCreateRoom() {
        Room room = new Room(1,"Elon","12/12","starts 12","ends 13");
        Mockito.when(roomRepository.save(room)).thenReturn(room);
    }

    @Test
    @DisplayName("This should pass when a specific room is returned.")
    void shouldGetSpecificRoom() {
        RoomController roomController = new RoomController(roomRepository);

    }

    @Test
    @DisplayName("This should pass when the room can be deleted.")
    void shouldDeleteRoom() throws ResourceNotFoundException {
        RoomController roomController = new RoomController(roomRepository);
        Room room = new Room(1,"Elon","12/12","starts 12","ends 13");
        Mockito.when(roomRepository.save(room))
                .then(roomController.deleteRoom(1L)).thenReturn(roomRepository.getOne(1L)).;


    }

    @Test
    @DisplayName("This should pass when the user is able to change any information of a room.")
    void shouldUpdateRoomInfo() {

    }
}