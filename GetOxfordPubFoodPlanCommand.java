package Commands;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class GetOxfordPubFoodPlanCommand implements Command {

    private static final String PATH_TO_PDF = "/home/alex/Kit_Bot/Wochenkarte_Times.pdf";
    // private static final String PATH_TO_PDF =
    // "/home/alexander/Wochenkarte_Times.pdf";
    private static final String COULDNT_FETCH_FOODPLAN = "Food Plan currently not avaliable";

    private static final String CACHE_NAME = "OxfordFoodPlan";

    @Override
    public Object execute() {

        PDDocument document;
        if (CacheDatabase.getCache(CACHE_NAME) == null || CacheDatabase.getCache(CACHE_NAME).getCache() == null) {
            try {
                document = PDDocument.load(new File(PATH_TO_PDF));
                PDFTextStripper reader = new PDFTextStripper();

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                String daysArray[] = {
                        "SONNTAG", "MONTAG", "DIENSTAG", "MITTWOCH", "DONNERSTAG", "FREITAG", "SAMSTAG"
                };
                String dayOfWeekEnd = daysArray[(day) % 7];
                String dayOfWeek = daysArray[(day - 1) % 7];

                String pdfRead = reader.getText(document).trim();
                pdfRead = formatHtml(pdfRead, dayOfWeek, dayOfWeekEnd);
                Cache cache = new Cache(pdfRead, Utility.getTomorrowMidnight());
                CacheDatabase.addCache(CACHE_NAME, cache);
                return pdfRead;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return COULDNT_FETCH_FOODPLAN;
        } else {
            return CacheDatabase.getCache(CACHE_NAME).getCache();
        }
    }

    private String formatHtml(String pdfRead, String dayOfWeek, String dayOfWeekEnd) {
        pdfRead = pdfRead.replaceAll("\n", "");
        pdfRead = pdfRead.replaceAll(".*" + dayOfWeek, dayOfWeek + ":");
        pdfRead = pdfRead.replaceAll("\\s{2,}", " ");
        pdfRead = pdfRead.replaceAll(dayOfWeekEnd + ".*", "");
        pdfRead = pdfRead.replaceAll("0 ", "0€\n\n");
        pdfRead = pdfRead.replaceAll("-\\s*", "\n");
        pdfRead = pdfRead.replaceAll("\\s,", ",");
        return pdfRead;

    }

}
