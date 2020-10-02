package com.ibm.developer.junit5quicklab.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibm.developer.junit5quicklab.model.Room;

public interface RoomRepo extends CrudRepository<Room, Long> {

	List<Room> findRoomsByRoomType(String type);

	Room findByRoomNumber(String roomNumber);

}
