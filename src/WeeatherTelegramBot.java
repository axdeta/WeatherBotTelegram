import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;

public class WeeatherTelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {


        //Check for the update has a message
        if (update.hasMessage()) {
            SendMessage message = new SendMessage();
            //Check for the update has a Location
            if(update.getMessage().hasLocation()){
                message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        //get weather
                        .setText(new OpenWeather().getWeather(update.getMessage().getLocation()));
            }
            else { //have no Location in message
                message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText("Для получение прогноза отправьте геолокацию!");
            }


            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "WeeatherTelegramBot";
    }

    @Override
    public String getBotToken() {
        File file = new File("tokenTelegram");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String result = br.readLine();
            return result;
        } catch (IOException e) {
            System.out.println("Telegram token not found");
        }
        throw new RuntimeException();
    }
}
