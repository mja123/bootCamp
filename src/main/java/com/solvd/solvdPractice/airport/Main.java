package com.solvd.solvdPractice.airport;

//region imports
import com.solvd.solvdPractice.airport.exceptions.EmptyAirsTripException;
import com.solvd.solvdPractice.airport.exceptions.FollowingPlanesException;
import com.solvd.solvdPractice.airport.exceptions.NotPilotException;
import com.solvd.solvdPractice.airport.exceptions.NotPlaneException;
import com.solvd.solvdPractice.airport.fly.Pilot;
import com.solvd.solvdPractice.airport.fly.Plane;
import com.solvd.solvdPractice.airport.physical_place.AirStrip;
import com.solvd.solvdPractice.airport.physical_place.Airport;
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
