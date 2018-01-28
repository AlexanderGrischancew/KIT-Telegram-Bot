package Commands;

public class GetAkkCommand implements Command {

    private static final String CACHE_NAME = "AkkCommand";

    @Override
    public Object execute() {
        if (CacheDatabase.getCache(CACHE_NAME) == null || CacheDatabase.getCache(CACHE_NAME).getCache() == null) {
            String message = "---SCHLONZE---\n" + ((String) new GetSchlonzeCommand().execute()).trim()
                    + "\n\n---SONDER---\n" + ((String) new GetSonderveranstaltungenCommand().execute()).trim();
            CacheDatabase.addCache(CACHE_NAME, new Cache(message, Utility.getTomorrowMidnight()));
            return message;
        } else {
            return CacheDatabase.getCache(CACHE_NAME).getCache();
        }
    }

}
