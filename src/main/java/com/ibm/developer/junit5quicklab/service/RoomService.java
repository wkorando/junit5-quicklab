package com.ibm.developer.junit5quicklab.service;

import java.util.List;

import com.ibm.developer.junit5quicklab.model.Room;

public interface RoomService {
	List<Room> findRoomsByType(String type);

	List<Room> findRoomsByFloor(String floor);
}
