import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryLogger {
    private List<String> logs;
    private DateTimeFormatter formatter;
    
    public LibraryLogger() {
        this.logs = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    public String logActivity(String activity) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = timestamp + " " + activity;
        logs.add(logEntry);
        return logEntry;
    }
    
    public String getLogs() {
        if (logs.isEmpty()) {
            return "Tidak ada log aktivitas";
        }
        return String.join("\n", logs);
    }
    
    public void clearLogs() {
        logs.clear();
    }
}