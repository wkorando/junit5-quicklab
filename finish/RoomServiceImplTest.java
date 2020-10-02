package com.ibm.developer.junit5quicklab;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.ExpectedException;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ibm.developer.junit5quicklab.RoomServiceException;
import com.ibm.developer.junit5quicklab.model.Room;
import com.ibm.developer.junit5quicklab.repo.RoomRepo;
import com.ibm.developer.junit5quicklab.service.impl.RoomServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@EnableRuleMigrationSupport
@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {
	private List<String> roomTypes = Arrays.asList("Single", "Double", "Suite");
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@DisabledIfSystemProperty(named = "junit5Disabled", matches = "true")
	@Test
	@Order(1)
	public void testFindByValidRoomType(@Mock RoomRepo repo) {
		System.out.println("testFindByValidRoomType");
		RoomServiceImpl service = new RoomServiceImpl(repo, roomTypes);
		when(repo.findRoomsByRoomType("Single")).thenReturn(Arrays.asList(//
				new Room(1L, "100", "Single", new BigDecimal(145.99))));
		List<Room> rooms = service.findRoomsByType("Single");

		assertEquals(1, rooms.size());
	}

	@Test
	public void testFindByInvalidRoomType() {
		System.out.println("testFindByInvalidRoomType");
		RoomRepo repo = mock(RoomRepo.class);
		RoomServiceImpl service = new RoomServiceImpl(repo, roomTypes);
		verify(repo, times(0)).findRoomsByRoomType(any());
		expectedException.expect(RoomServiceException.class);
		expectedException.expectMessage("Room type: NOT FOUND not found!");
		service.findRoomsByType("NOT FOUND");
	}

	@Test
	public void testFindByNullRoomType() {
		System.out.println("testFindByNullRoomType");
		RoomRepo repo = mock(RoomRepo.class);
		RoomServiceImpl service = new RoomServiceImpl(repo, roomTypes);
		verify(repo, times(0)).findRoomsByRoomType(any());
		RoomServiceException e = assertThrows(RoomServiceException.class, () -> service.findRoomsByType(null));

		assertEquals("Room type: null not found!", e.getMessage());

	}

	@Test
	@Order(Integer.MAX_VALUE)
	public void testAddRoom() {
		System.out.println("testAddRoom");
		RoomRepo repo = mock(RoomRepo.class);
		when(repo.save(any())).thenReturn(new Room(1L, "100", "Single", new BigDecimal(149.99)));
		RoomServiceImpl service = new RoomServiceImpl(repo, roomTypes);

		Room newRoom = service.addRoom(new Room());
		assertAll(() -> assertEquals(1L, newRoom.getId()), 
				() -> assertEquals("100", newRoom.getRoomNumber()),
				() -> assertEquals("Single", newRoom.getRoomType()),
				() -> assertEquals(new BigDecimal(149.99).setScale(2, RoundingMode.FLOOR),
						newRoom.getRoomRate().setScale(2, RoundingMode.FLOOR)));
	}

}
