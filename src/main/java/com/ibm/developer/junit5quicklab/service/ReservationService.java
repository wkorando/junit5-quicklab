package com.ibm.developer.junit5quicklab.service;

import java.util.List;

public interface ReservationService {
	List<String> verifyReservationDates(String startDate, String endDate);
}
