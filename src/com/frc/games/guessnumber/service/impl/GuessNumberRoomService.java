package com.frc.games.guessnumber.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frc.games.framework.model.Player;
import com.frc.games.framework.model.Room;
import com.frc.games.framework.service.IRoomService;

@Service("GuessNumberRoomService")
public class GuessNumberRoomService implements IRoomService {
	public static Map<String, Room> roomMap = new HashMap<>();
	
	public Room createRoom(Player player, String roomName, int roomSize) {
		Room room = new Room();
		room.setName(roomName);
		room.setRoomSize(roomSize);
		room.addPlayer(player);
		room.setRoomId(UUID.randomUUID().toString());
		
		roomMap.put(room.getRoomId(), room);
		return room;
	}
	
	public Room findRoom(String roomId) {
		return roomMap.get(roomId);
	}
	
	public boolean joinRoom(Player player, String roomId) {
		Room room = findRoom(roomId);
		if (canJoin(room)) {
			room.addPlayer(player);
			return true;
		} else {
			return false;
		}
	}
	protected boolean canJoin(Room room) {
		if (room == null) {
			return false;
		} else if (room.getGame() == null) {
			return false;
		} else if (room.getGame().getStatus() != 1) {
			return false;
		} else {
			return true;
		}
	}
}
