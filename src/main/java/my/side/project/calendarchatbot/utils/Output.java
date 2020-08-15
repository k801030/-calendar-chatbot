package my.side.project.calendarchatbot.utils;

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

    private String name;

    private Output(String name) {
        this.name = name;
    }

    /**
     * to create an output class
     * @param name
     * @return
     */
    public static Output getOutput(String name) {
        return new Output(name);
    }

    public void print(LogLevel logLevel, String message) {
        String output = String.format("%s  %s %s - %s", getNow(), decoratedLogLevel(logLevel), name, message);
        System.out.println(output);
    }

    public void print(LogLevel logLevel, String message, String... args) {
        for (String arg : args)
            message = message.replaceFirst("\\{\\}", arg);
        print(logLevel, message);
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
