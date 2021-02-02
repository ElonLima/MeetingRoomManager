package com.meetingRoomManager.backend.controller;

import com.meetingRoomManager.backend.exception.ResourceNotFoundException;
import com.meetingRoomManager.backend.model.Room;
import com.meetingRoomManager.backend.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {

    @Mock
    private RoomController roomController;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @DisplayName("This should pass when the initial list of rooms is empty.")
    void shouldReturnEmptyListOfRooms() {
        RoomController roomController = new RoomController(roomRepository);
        assertEquals(0, roomRepository.count());

    }

    @Test
    @DisplayName("This should pass when a new room is sucessfuly created.")
    void shouldCreateRoom() {
        RoomController roomController = new RoomController(roomRepository);
        Room room = new Room(1,"New person","12/12/12","12","13");
        Room savedUser = roomController.createRoom(room);

        assertThat(savedUser).usingRecursiveComparison().isEqualTo(room);
    }

    @Test
    @DisplayName("This should pass when the information properly updated.")
    void shouldUpdateListOfRooms() throws ResourceNotFoundException {
        RoomController roomController = new RoomController(roomRepository);

        Room firstRoom = new Room(1,"Old person","1/1/1","1","1");
        Room updatedRoom = new Room(1,"New person","12/12/12","12","13");

        Room firstSavedRoom = roomController.createRoom(firstRoom);
        roomController.updateRoom(firstSavedRoom.getId(), updatedRoom);

    assertAll( () -> {
            assertEquals(roomRepository.getOne(firstSavedRoom.getId()).getName(), updatedRoom.getName());
            assertEquals(roomRepository.getOne(firstSavedRoom.getId()).getDate(), updatedRoom.getDate());
            assertEquals(roomRepository.getOne(firstSavedRoom.getId()).getStartHour(), updatedRoom.getStartHour());
            assertEquals(roomRepository.getOne(firstSavedRoom.getId()).getEndHour(), updatedRoom.getEndHour());
        });
    }

    @Test
    @DisplayName("This should pass when specific rooms were properly deleted.")
    void shouldDeleteRoom() throws ResourceNotFoundException {
        RoomController roomController = new RoomController(roomRepository);

        Room firstRoom = new Room(1L,"Name", "date", "starting hour", "ending hour");
        Room secondRoom = new Room(1L, "Name", "date", "starting hour", "ending hour");
        Room thirdRoom = new Room(1L, "Name", "date", "starting hour", "ending hour");

        Room r1 = roomController.createRoom(firstRoom);
        Room r2 = roomController.createRoom(secondRoom);
        Room r3 = roomController.createRoom(thirdRoom);

        assertEquals(3, roomRepository.count());

        roomController.deleteRoom(r1.getId());
        assertEquals(2, roomRepository.count());

        roomController.deleteRoom(r3.getId());
        assertEquals(1, roomRepository.count());

        roomController.deleteRoom(r2.getId());
        assertEquals(0, roomRepository.count());

    }
}