package airport.app.dao.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import airport.app.dao.csv.model.FlightFromCsv;
import airport.app.dao.model.Flight;

public class CsvFlightMapperTest {

	private static CsvFlightMapper classUnderTest;

	@BeforeAll
	public static void init() {
		classUnderTest = new CsvFlightMapper();
	}

	@Test
	public void test_map_flight_from_csv_to_flight_returns_null_for_null_input() {

		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(null);

		assertThat(flightActual).isNull();
	}

	@Test
	public void test_departure_time_mapped_correctly() {

		FlightFromCsv flightFromCsv = buildFlightFromCsv();
		flightFromCsv.setDepartureTime("20:21");
		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(Collections.singletonList(flightFromCsv));

		assertThat(flightActual.get(0).getDepartureTime()).isEqualTo(LocalTime.of(20, 21));
	}

	@Test
	public void test_destination_mapped_correctly() {

		FlightFromCsv flightFromCsv = buildFlightFromCsv();
		flightFromCsv.setDestination("Barbados");

		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(Collections.singletonList(flightFromCsv));

		assertThat(flightActual.get(0).getDestination()).isEqualTo("Barbados");
	}

	@Test
	public void test_destination_airport_iata_mapped_correctly() {

		FlightFromCsv flightFromCsv = buildFlightFromCsv();
		flightFromCsv.setDestinationAirportIATA("BGI");

		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(Collections.singletonList(flightFromCsv));

		assertThat(flightActual.get(0).getDestinationAirportIATA()).isEqualTo("BGI");
	}

	@Test
	public void test_flight_no_mapped_correctly() {

		FlightFromCsv flightFromCsv = buildFlightFromCsv();
		flightFromCsv.setFlightNo("VS029");

		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(Collections.singletonList(flightFromCsv));

		assertThat(flightActual.get(0).getFlightNo()).isEqualTo("VS029");
	}

	@Test
	public void test_available_on_day_of_week_mapped_correctly_for_all_available() {

		FlightFromCsv flightFromCsv = buildFlightFromCsv();
		flightFromCsv.setSunday("x");
		flightFromCsv.setMonday("x");
		flightFromCsv.setTuesday("x");
		flightFromCsv.setWednesday("x");
		flightFromCsv.setThursday("x");
		flightFromCsv.setFriday("x");
		flightFromCsv.setSaturday("x");

		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(Collections.singletonList(flightFromCsv));

		assertThat(flightActual.get(0).getAvailableOnDayOfWeek()).contains(DayOfWeek.values());
	}
	
	@Test
	public void test_available_on_day_of_week_mapped_correctly_for_all_non_available() {

		FlightFromCsv flightFromCsv = buildFlightFromCsv();
		
		List<Flight> flightActual = classUnderTest.mapFlightsFromCSVToFlights(Collections.singletonList(flightFromCsv));

		assertThat(flightActual.get(0).getAvailableOnDayOfWeek()).doesNotContain(DayOfWeek.values());
	}

	private FlightFromCsv buildFlightFromCsv() {
		FlightFromCsv flightFromCsv = new FlightFromCsv();
		flightFromCsv.setDepartureTime("19:20");
		return flightFromCsv;
	}
}
