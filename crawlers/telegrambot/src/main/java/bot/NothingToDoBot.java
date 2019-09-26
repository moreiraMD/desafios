package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import service.CrawlerService;

public class NothingToDoBot extends TelegramLongPollingBot {

    private static final String NAME = System.getenv("BOT_NAME");
    private static final String TOKEN = System.getenv("BOT_TOKEN");

    private static final String NADAPRAFAZER_COMMAND = "/NadaPraFazer";

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message;

        if (update.hasMessage() && update.getMessage().hasText()) {

            if (update.getMessage().getText().contains(NADAPRAFAZER_COMMAND)) {
                String subreddits = update.getMessage().getText().replace(NADAPRAFAZER_COMMAND, "").replaceAll("\\s", "");

                CrawlerService service = new CrawlerService();

                String trendings = service.getTrendings(subreddits);

                if(trendings.length() > 0) {
                    message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(trendings);
                } else {
                    message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText("Found nothing...");
                }

                try {
                    sendMessage(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
