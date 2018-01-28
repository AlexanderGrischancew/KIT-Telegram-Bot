package Commands;

import java.time.LocalDateTime;

class Cache {

    private Object cache;
    private LocalDateTime expiresAfter;

    public Cache(Object cache, String expiresAfter) {
        new Cache(cache, Utility.stringToDateTime(expiresAfter));
    }

    public Cache(Object cache, LocalDateTime dateTime) {
        this.cache = cache;
        this.expiresAfter = dateTime;
    }

    public Object getCache() {
        if (isExpired()) {
            return null;
        } else {
            return cache;
        }
    }

    private boolean isExpired() {
        return !expiresAfter.isAfter(Utility.getToday());
    }

}
//Assertions