package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.core.ApplicationFramework;

public class LoggerFactory {
    public Logger createLogger(String tip) {
        if (tip.toLowerCase().equals("console")) {
            return new ConsoleLogger();
        }
        else if (tip.toLowerCase().equals("file")) {
            return new FileLogger();
        }
        return null;
    }
}
