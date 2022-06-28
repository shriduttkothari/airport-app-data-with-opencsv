package airport.app.dao.csv.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import airport.app.dao.csv.model.FlightFromCsv;
import airport.app.dao.util.AppConstants;

@Component
public class CsvReader {

	public List<FlightFromCsv> readFlightFromCsv() throws Exception {
		return convertFileToTargetObject(AppConstants.CSV_FILE_PATH, FlightFromCsv.class);
	}

	private <T> List<T> convertFileToTargetObject(String path, Class<T> target) {
		List<T> targetList = null;
		try {
			InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
			InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(streamReader).withType(target)
					.withIgnoreLeadingWhiteSpace(true).build();
			targetList = csvToBean.parse();

			streamReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return targetList;
	}
}
