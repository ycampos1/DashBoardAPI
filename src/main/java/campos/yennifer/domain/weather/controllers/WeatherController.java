package campos.yennifer.domain.weather.controllers;

import campos.yennifer.domain.weather.models.WeatherAPIResponse;
import campos.yennifer.domain.weather.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("weather")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("")
    public ResponseEntity<WeatherAPIResponse> requestWeather(@RequestParam(name="lon", required = false) String lon,
                                                                 @RequestParam(name="lat", required = false) String lat){
        Optional<WeatherAPIResponse> response = weatherService.requestDataFromApi(lat, lon);
        log.info(response.toString());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
