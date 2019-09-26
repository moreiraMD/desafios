import bot.NothingToDoBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotApp {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new NothingToDoBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
