package airport.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FollowingPlanesException extends Exception{
    private final static Logger LOGGER = LogManager.getLogger(FollowingPlanesException.class);

    public FollowingPlanesException(String message) {
        super(message);
    }
}
