package airport.app.dao.repository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import airport.app.dao.csv.model.FlightFromCsv;
import airport.app.dao.csv.reader.CsvReader;
import airport.app.dao.exception.NoFlightAvailableException;
import airport.app.dao.mapper.CsvFlightMapper;
import airport.app.dao.model.Flight;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class FlightRepositoryImpl implements FlightRepository {

	private static final List<Flight> AVAILABLE_FLIGHTS = new ArrayList<>();

	@Autowired
	private CsvReader csvReader;

	@Autowired
	private CsvFlightMapper csvFlightMapper;

	@Override
	public void saveFlights(List<? extends Flight> items) {
		AVAILABLE_FLIGHTS.addAll(items);
	}

	@Override
	public List<Flight> getAllFlights() {
		return AVAILABLE_FLIGHTS;
	}

	@Override
	public List<Flight> getFlightsForGivenDayOfWeek(DayOfWeek dayOfTheWeek) throws NoFlightAvailableException {

		return AVAILABLE_FLIGHTS.stream().filter(flight -> flight.getAvailableOnDayOfWeek().contains(dayOfTheWeek))
				.collect(Collectors.toList());

	}

	@PostConstruct
	private void loadCsv() throws Exception {
		List<FlightFromCsv> flightFromCsvList = csvReader.readFlightFromCsv();
		List<Flight> fightList = csvFlightMapper.mapFlightsFromCSVToFlights(flightFromCsvList);
		if (null != fightList) {
			AVAILABLE_FLIGHTS.addAll(fightList);
		} else {
			log.error("Could not load Flight details from CSV!!");
		}
	}

}
