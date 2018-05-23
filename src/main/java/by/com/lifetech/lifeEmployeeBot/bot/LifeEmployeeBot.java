package by.com.lifetech.lifeEmployeeBot.bot;

import by.com.lifetech.lifeEmployeeBot.keyboard.CustomKeyboardMarkup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Component
public class LifeEmployeeBot extends TelegramLongPollingBot {
    @Value("${TOKEN}")
    private String token;

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            String messageText = update.getMessage().getText();
            if(messageText.equalsIgnoreCase("/start")) {
                ReplyKeyboardMarkup keyboardMarkup = new CustomKeyboardMarkup.Builder()
                        .addRow()
                            .addButton("FirstButton")
                            .addButton("SecondButton")
                        .addRow()
                            .addButton("Third Button")
                        .build();

                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Here is your keyboard");

                message.setReplyMarkup(keyboardMarkup);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error(e.getMessage(), e);
                }

            } else {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(messageText);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "LifeEmployeeBot";
    }
}
