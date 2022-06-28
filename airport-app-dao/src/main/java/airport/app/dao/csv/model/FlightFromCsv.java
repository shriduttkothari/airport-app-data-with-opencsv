package airport.app.dao.csv.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightFromCsv {

	@CsvBindByName(column = "Departure Time")
	private String departureTime;
	@CsvBindByName(column = "Destination")
	private String destination;
	@CsvBindByName(column = "Destination Airport IATA")
	private String destinationAirportIATA;
	@CsvBindByName(column = "Flight No")
	private String flightNo;
	@CsvBindByName(column = "Sunday")
	private String sunday;
	@CsvBindByName(column = "Monday")
	private String monday;
	@CsvBindByName(column = "Tuesday")
	private String tuesday;
	@CsvBindByName(column = "Wednesday")
	private String wednesday;
	@CsvBindByName(column = "Thursday")
	private String thursday;
	@CsvBindByName(column = "Friday")
	private String friday;
	@CsvBindByName(column = "Saturday")
	private String saturday;
}
