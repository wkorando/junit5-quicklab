package com.ibm.developer.junit5quicklab.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.developer.junit5quicklab.RoomServiceException;
import com.ibm.developer.junit5quicklab.model.Room;
import com.ibm.developer.junit5quicklab.repo.RoomRepo;
import com.ibm.developer.junit5quicklab.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	private RoomRepo roomRepo;
	private List<String> roomTypes;

	public RoomServiceImpl(RoomRepo roomRepo, List<String> roomTypes) {
		this.roomRepo = roomRepo;
		this.roomTypes = roomTypes;
	}

	@Override
	public List<Room> findRoomsByType(String type) {
		if (roomTypes.contains(type)) {
			return roomRepo.findRoomsByRoomType(type);
		} else {
			throw new RoomServiceException("Room type: " + type + " not found!");
		}

	}

	@Override
	public List<Room> findRoomsByFloor(String floor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Room addRoom(Room room) {
		List<String> errorMessages = new ArrayList<>();
		if(roomRepo.findByRoomNumber(room.getRoomNumber()) != null) {
			errorMessages.add("Duplicated room number!");
		}
		return roomRepo.save(room);
	}

}
