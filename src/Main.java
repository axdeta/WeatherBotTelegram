import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        disableWarning();
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new WeeatherTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //disable WARNING --illegal access operation has occurred--
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
