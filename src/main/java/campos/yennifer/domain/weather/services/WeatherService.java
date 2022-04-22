package campos.yennifer.domain.weather.services;

import campos.yennifer.domain.weather.models.WeatherAPIResponse;

import java.util.Optional;

public interface WeatherService {
    Optional<WeatherAPIResponse> requestDataFromApi(String lat, String lon);


}
