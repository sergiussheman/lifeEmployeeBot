package by.com.lifetech.lifeEmployeeBot.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomKeyboardMarkup {
    private List<KeyboardRow> rows = new ArrayList<>();
    private KeyboardRow currentRow;

    public void addRow(KeyboardRow row){
        rows.add(row);
        this.currentRow = row;
    }

    public void addButton(String buttonContent) {
        if(this.currentRow == null) {
            this.currentRow = new KeyboardRow();
            this.rows.add(this.currentRow);
        }
        this.currentRow.add(buttonContent);
    }

    public ReplyKeyboardMarkup createKeyboard() {
        return (new ReplyKeyboardMarkup()).setKeyboard(this.rows);
    }


    public static class Builder {
        private CustomKeyboardMarkup instance;

        public Builder() {
            this.instance = new CustomKeyboardMarkup();
        }

        public Builder addRow() {
            this.instance.addRow(new KeyboardRow());
            return this;
        }

        public Builder addButton(String buttonContent) {
            this.instance.addButton(buttonContent);
            return this;
        }

        public ReplyKeyboardMarkup build() {
            return this.instance.createKeyboard();
        }
    }
}
