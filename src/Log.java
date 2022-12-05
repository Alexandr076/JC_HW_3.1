public class Log {
    private StringBuilder oLog = new StringBuilder();

    public StringBuilder getoLog() {
        return oLog;
    }

    public void add(String logMessage) {
        oLog.append(logMessage + "\n");
    }
}
