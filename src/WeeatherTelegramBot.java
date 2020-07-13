import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        return "1253967624:AAHb29acz-y0oiu1CqSmpc32SzZ8M9Zl4iA";
    }
}
