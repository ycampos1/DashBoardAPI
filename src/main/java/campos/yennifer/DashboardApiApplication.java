package campos.yennifer;

import campos.yennifer.domain.weather.models.WeatherAPIResponse;
import campos.yennifer.domain.weather.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;


@SpringBootApplication
public class DashboardApiApplication implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(DashboardApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DashboardApiApplication.class, args);
	}

	@Autowired
	private WeatherService weatherService;

	@Override
	public void run(String... args) throws Exception {
		Optional<WeatherAPIResponse> response = weatherService.requestDataFromApi("39.7447", "-75.5484");
		if(response.isEmpty()){
			log.info("Not Good");
			return;
		}
		WeatherAPIResponse data = response.get();
		log.info(data.toString());
	}
}
