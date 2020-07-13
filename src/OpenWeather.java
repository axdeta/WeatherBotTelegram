import Weather.WeatherModel;
import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.io.*;
import java.net.URL;

public class OpenWeather {
    private  static String LINK_OPEN_WEATHER =
            "http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric,uk&APPID=";
    private Gson gson = new Gson();

    public OpenWeather() {
        File file = new File("APPID");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            LINK_OPEN_WEATHER +=br.readLine();
        } catch (IOException e) {
            System.out.println("Open Weather APPID not found");
        }
    }

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
