package my.side.project.calendarchatbot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Output {

    // Colors for standard output
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";
    private static final String YELLOW = "\033[0;33m";  // YELLOW
    private static final String RED = "\033[0;31m";     // RED



    public static void print(LogLevel logLevel, String message) {
        String output = String.format("%s  %s %s", getNow(), decoratedLogLevel(logLevel), message);
        System.out.println(output);
    }

    /**
     * decorate the output for log level text
     * @param logLevel
     * @return
     */
    private static String decoratedLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case INFO:
            case DEBUG:
                return GREEN + logLevel + RESET;
            case WARN:
                return YELLOW + logLevel + RESET;
            case ERROR:
                return RED + logLevel + RESET;
            default:
                return logLevel.toString();
        }
    }

    /**
     * Get the current time
     *
     * @return string
     */
    private static String getNow() {
        Date now = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-d HH:mm:ss.SSS");
        return df.format(now);
    }
}
