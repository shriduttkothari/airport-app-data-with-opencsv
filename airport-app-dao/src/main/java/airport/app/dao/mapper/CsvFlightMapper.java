package airport.app.dao.mapper;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import airport.app.dao.csv.model.FlightFromCsv;
import airport.app.dao.model.Flight;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CsvFlightMapper implements FlightMapper {

	public List<Flight> mapFlightsFromCSVToFlights(final List<FlightFromCsv> flightFromCsvList) {
		if (flightFromCsvList == null) {
			return null;
		}
		return flightFromCsvList.stream().map(this::mapFlightFromCSVToFlight).collect(Collectors.toList());
	}

	private Flight mapFlightFromCSVToFlight(final FlightFromCsv flightFromCsv) {
		if (flightFromCsv == null) {
			return null;
		}

		String departureTimeString = flightFromCsv.getDepartureTime();
		LocalTime departureTime = null;
		if (null != departureTimeString) {
			departureTimeString = departureTimeString.trim();
			try {
				departureTime = LocalTime.parse(departureTimeString);
			} catch (Exception e) {
				log.error("Invalid value for departureTime: " + departureTimeString);
			}
		}

		final Flight transformedFlight = Flight.builder().departureTime(departureTime)
				.destination(flightFromCsv.getDestination())
				.destinationAirportIATA(flightFromCsv.getDestinationAirportIATA()).flightNo(flightFromCsv.getFlightNo())
				.availableOnDayOfWeek(buildAvailableOnDayOfWeek(flightFromCsv)).build();

		return transformedFlight;

	}

	private Set<DayOfWeek> buildAvailableOnDayOfWeek(final FlightFromCsv flightFromCsv) {
		final Set<DayOfWeek> availableOnDayOfWeek = new HashSet<>();
		if (isAvaialbleOn(flightFromCsv.getSunday())) {
			availableOnDayOfWeek.add(DayOfWeek.SUNDAY);
		}
		if (isAvaialbleOn(flightFromCsv.getMonday())) {
			availableOnDayOfWeek.add(DayOfWeek.MONDAY);
		}
		if (isAvaialbleOn(flightFromCsv.getTuesday())) {
			availableOnDayOfWeek.add(DayOfWeek.TUESDAY);
		}
		if (isAvaialbleOn(flightFromCsv.getWednesday())) {
			availableOnDayOfWeek.add(DayOfWeek.WEDNESDAY);
		}
		if (isAvaialbleOn(flightFromCsv.getThursday())) {
			availableOnDayOfWeek.add(DayOfWeek.THURSDAY);
		}
		if (isAvaialbleOn(flightFromCsv.getFriday())) {
			availableOnDayOfWeek.add(DayOfWeek.FRIDAY);
		}
		if (isAvaialbleOn(flightFromCsv.getSaturday())) {
			availableOnDayOfWeek.add(DayOfWeek.SATURDAY);
		}

		return availableOnDayOfWeek;
	}

	private boolean isAvaialbleOn(String value) {
		if ("x".equals(value)) {
			return true;
		}
		{
			return false;
		}
	}

}
