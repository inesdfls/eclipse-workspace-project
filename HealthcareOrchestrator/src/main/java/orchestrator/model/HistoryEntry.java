package orchestrator.model;

import java.time.LocalDateTime;

public class HistoryEntry {
    private LocalDateTime time;
    private String step;
    private String message;

    public HistoryEntry() {}

    public HistoryEntry(String step, String message) {
        this.time = LocalDateTime.now();
        this.step = step;
        this.message = message;
    }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}