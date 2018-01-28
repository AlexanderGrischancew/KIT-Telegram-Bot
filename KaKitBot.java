package KitBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import Commands.Commands;

public class KaKitBot extends TelegramLongPollingBot {

    final static String BOT_TOKEN = "_______________________";
    final static String BOT_NAME = "KaKit_bot";

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();

            Commands commands = new Commands();
            String output = (String) commands.getCommand(command.trim().toLowerCase()).execute();
            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId()).setText(output);
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
