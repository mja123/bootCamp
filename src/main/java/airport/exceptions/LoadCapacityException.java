package airport.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadCapacityException extends Exception {

    private static final Logger LOGGER = LogManager.getLogger(LoadCapacityException.class);

    public LoadCapacityException() {
        LOGGER.error("Load capacity was exceeded.");
    }

}
