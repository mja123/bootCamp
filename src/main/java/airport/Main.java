package airport;

//region imports
import airport.exceptions.EmptyAirsTripException;
import airport.exceptions.FollowingPlanesException;
import airport.exceptions.NotPilotException;
import airport.exceptions.NotPlaneException;
import airport.fly.Pilot;
import airport.fly.Plane;
import airport.physical_place.AirStrip;
import airport.physical_place.Airport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//endregion

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {


        Pilot pilot1 = new Pilot();
        Plane plane1 = new Plane(pilot1, 1);
        AirStrip airStrip1 = new AirStrip();
        Airport airport1 = new Airport("MDZ");
        Airport airport2 = new Airport("BS");

        airport1.addPlane(plane1);
        airport1.addPilot(pilot1);

        airStrip1.addPlanesQueue(plane1);

        //This doesn't enter the catch block, execute the startTravel method correctly.
        try {
            airport1.startTravel();
        } catch (FollowingPlanesException | NotPilotException | NotPlaneException | EmptyAirsTripException e) {
            LOGGER.error(e);
        }

        //This enters the catch block, because doesn't have plane and pilot. Log in console and file.
        try {
            airport2.startTravel();
        } catch (FollowingPlanesException | NotPlaneException | NotPilotException | EmptyAirsTripException e) {
            LOGGER.error(e);
        }
    }
}
