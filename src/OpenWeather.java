import Weather.WeatherModel;
import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class OpenWeather {
    private final static String LINK_OPEN_WEATHER =
            "http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric,uk&APPID=f8921b454364cd5d513cfeb970011d5f";
    private Gson gson = new Gson();

    public String getWeather(Location location) {

        String url = String.format(LINK_OPEN_WEATHER, location.getLatitude(), location.getLongitude());

        try {
            System.out.println(url);
            InputStream inputStream = new URL(url).openConnection().getInputStream();
            WeatherModel weatherModel = gson.fromJson(new InputStreamReader(inputStream), WeatherModel.class);

            System.out.println(weatherModel);
            return parseWeather(weatherModel);


        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }
    };

    private String parseWeather(WeatherModel weatherModel) {
        String temp = Double.toString((int)(weatherModel.getModel().getTemp() - 273.15)) ; //temperature
        String place = weatherModel.getName(); //place of weather

        return String.format("Место: %s\nТемпература: %s ", place, temp);
    }
}
