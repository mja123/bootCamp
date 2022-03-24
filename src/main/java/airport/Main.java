package airport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.debug("Works debug");
        LOGGER.info("Works info");
        LOGGER.error("Works error");
        LOGGER.fatal("Works fatal");
        LOGGER.warn("Works warn");
        LOGGER.trace("Works trace");

        /*
        Airport airport1 = new Airport();
        Plane plane1 = new Plane();
        Pilot pilot1 = new Pilot();

        plane1.setPilot(pilot1);
        airport1.addPilot(pilot1);
        airport1.addPlane(plane1);

        plane1.takeOff();


         */
    }
}
