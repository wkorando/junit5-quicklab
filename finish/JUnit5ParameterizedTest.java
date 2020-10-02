package com.ibm.developer.junit5quicklab;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.ibm.developer.junit5quicklab.service.impl.ReservationServiceImpl;

public class JUnit5ParameterizedTest {

	@ParameterizedTest
	@MethodSource("data")
	public void verifyDateValidation(DateValidationBean dateValidation) {
		ReservationServiceImpl service = new ReservationServiceImpl();
		List<String> errorMsgs = service.verifyReservationDates(dateValidation.checkInDate, dateValidation.checkOutDate);
		assertThat(errorMsgs).containsExactlyInAnyOrder(dateValidation.errorMsgs);
	}

	static Stream<DateValidationBean> data() {
		return Stream.of(new DateValidationBean("Valid booking dates", "03/03/2022", "03/07/2022"),
				new DateValidationBean("Null check-in date", null, "11/27/2020", "Must provide a check-in date."),
				new DateValidationBean("Both dates null", null, null, "Must provide a check-in date.",
						"Must provide a check-out date."),
				new DateValidationBean("Invalid check-in date", "02/30/2022", "03/07/2022",
						"check-in date of: 02/30/2022 is not a valid date or does not match date format of: MM/DD/YYYY"));
	}
	
	@Test
	public void justATest() {
		
	}
	
	static class DateValidationBean {
		final String testName;
		final String checkInDate;
		final String checkOutDate;
		final String errorMsgs[];

		DateValidationBean(String testName, String checkInDate, String checkOutDate, String... errorMsgs) {
			this.testName = testName;
			this.checkInDate = checkInDate;

			this.checkOutDate = checkOutDate;
			this.errorMsgs = errorMsgs;
		}

		@Override
		public String toString() {
			return testName;
		}
	}
}