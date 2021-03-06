package Commands;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class GetSchlonzeCommand implements Command {

    private static final String AKK_SCHLONZE_URL = "https://www.akk.org/schlonze/index.php?";

    @Override
    public Object execute() {
        try {
            String output = "";
            Document doc = Jsoup.connect(AKK_SCHLONZE_URL).get();

            Elements table = doc.select("table");
            for (Element trs : table.select("tr")) {
                Elements tds = trs.select("td");
                for (Element td : tds) {
                    Elements lin = td.select("time");
                    if (!lin.isEmpty()) {
                        if (!Utility.isAfter(lin.get(0).attr("datetime"))) {
                            break;
                        }
                    }
                    String line = td.text().replaceAll("Altes Stadion", "");
                    if(line.contains("Uhr")){
                        line += "\n";
                    }
                    output += line;
                }
                if (!tds.isEmpty()) {
                    output += "\n";
                }
            }

            return output;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
