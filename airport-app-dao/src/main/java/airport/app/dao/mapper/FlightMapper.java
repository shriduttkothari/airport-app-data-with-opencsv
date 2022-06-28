package airport.app.dao.mapper;

import java.util.List;

import airport.app.dao.csv.model.FlightFromCsv;
import airport.app.dao.model.Flight;

public interface FlightMapper {

	public List<Flight> mapFlightsFromCSVToFlights(final List<FlightFromCsv> flightFromCsv);
}
