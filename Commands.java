package Commands;

import java.util.Map;
import java.util.TreeMap;

public final class Commands {
    private static final String COMMAND_NOT_FOUND = "/commandnotfound";
    private static final String START = "/start";
    private static final String GET_FOOD_PLAN = "/getfoodplan";
    private static final String GET_SCHLONZE = "/getschlonze";
    private static final String GET_SONDERVERANSTALTUNG = "/getsonderveranstaltungen";
    private static final String GET_AKK = "/getakk";
    private static final String GET_OXFORD_FOOD_PLAN = "/getoxfordfoodplan";

    static Map<String, Command> commandsMap = new TreeMap<>();

    public Commands() {
        commandsMap.put(GET_FOOD_PLAN, new GetFoodPlanCommand());
        commandsMap.put(COMMAND_NOT_FOUND, new CommandNotFound());
        commandsMap.put(GET_SCHLONZE, new GetSchlonzeCommand());
        commandsMap.put(START, new StartCommand());
        commandsMap.put(GET_SONDERVERANSTALTUNG, new GetSonderveranstaltungenCommand());
        commandsMap.put(GET_AKK, new GetAkkCommand());
        commandsMap.put(GET_OXFORD_FOOD_PLAN, new GetOxfordPubFoodPlanCommand());
    }

    public Command getCommand(String command) {
        if (commandsMap.containsKey(command)) {
            return commandsMap.get(command);
        }
        return commandsMap.get(COMMAND_NOT_FOUND);
    }
}
