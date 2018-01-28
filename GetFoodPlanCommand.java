package Commands;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetFoodPlanCommand implements Command {

    private static final String FOOD_PLAN_URL = "http://mensa.akk.uni-karlsruhe.de/?DATUM=heute&uni=1&schnell=1";
    private static final String CACHE_NAME = "FoodPlan";
    private static final String FOOD_PLAN_NOT_AVALIABLE = "Mensaplan zurzeit nicht verfügbar";

    @Override
    public Object execute() {
        if (CacheDatabase.getCache(CACHE_NAME) == null || CacheDatabase.getCache(CACHE_NAME).getCache() == null) {
            try {
                String output = "";
                Document doc = Jsoup.connect(FOOD_PLAN_URL).get();
                Elements table = doc.select("table");
                for (Element row : table.select("tr")) {
                    Elements tds = row.select("td");
                    for (Element td : tds) {
                        output += td.text();
                        if (td.text().matches("")) {
                            output += " ";
                        }
                    }
                    output += "\n";
                }
                output = output.replaceAll("Linie", "\nLinie");
                if (output.isEmpty()) {
                    return FOOD_PLAN_NOT_AVALIABLE;
                }
                CacheDatabase.addCache(CACHE_NAME, new Cache(output.trim(),Utility.getTomorrowMidnight()));
                return output.trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return FOOD_PLAN_NOT_AVALIABLE;
        }
        else{
            return CacheDatabase.getCache(CACHE_NAME).getCache();
        }
    }

}
