package Commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utility {
    private Utility(){
        
    }
    
    public static boolean isAfter(String date) {
        LocalDateTime dateTime = Utility.stringToDateTime(date);
        LocalDateTime today = getToday();
        return dateTime.isAfter(today);
    }
    
    public static LocalDateTime stringToDateTime(String date){
        String dateTime = date.replace("T", " ");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.from(f.parse(dateTime));
    }
    
    public static LocalDateTime getToday(){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String todayString = today.format(f);
        return LocalDateTime.from(f.parse(todayString));
    }
    
    public static LocalDateTime getTomorrowMidnight(){
        LocalDateTime today = Utility.getToday();
        return today.plusDays(1).withHour(0).withMinute(0);
    }
}


