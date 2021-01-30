package com.meetingRoomManager.backend.controller;

import com.meetingRoomManager.backend.model.Room;
import com.meetingRoomManager.backend.repository.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomControllerTest {

    private final RoomRepository roomRepository = Mockito.mock(RoomRepository.class);

    @Test
    @DisplayName("This should pass when the initial list of rooms is empty.")
    void shouldReturnEmptyListOfRooms() {
        RoomController roomController = new RoomController(roomRepository);
        Mockito.when(roomRepository.findAll()).thenReturn(null);
    }

    @Test
    @DisplayName("This should pass when a new room is sucessfuly created.")
    void shouldCreateRoom() {
        RoomController roomController = new RoomController(roomRepository);
        Room room = new Room(1,"Elon","12/12","starts 12","ends 13");
        Mockito.when(roomRepository.save(room)).thenReturn(room);
    }
}