package KitBot;

import java.util.Scanner;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        System.out.println("RUNNING");
        boolean quit = false;
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new KaKitBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
        Scanner scanner = new Scanner(System.in);
        while (!quit) {
            String input = scanner.nextLine();
            if(input.matches("exit") || input.matches("quit")){
                quit = true;
                System.out.println("TERMINATED");
                scanner.close();
                System.exit(0);
            }
        }
    }

}
