package Test;

import Model.Directions;
import Model.Graph.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kamil on 14.07.17.
 */
class LocationTest {
    @Test
    void movementOfTheObjectIfItIsOnTheNorthernBorder() {
        Location location = new Location(3,3);
        location.setX(1);
        location.setY(0);
        location.move(Directions.North);

        assertEquals(1,location.getX());
        assertEquals(2,location.getY());
    }

    @Test
    void movementOfTheObjectIfItIsOnTheSouthernBorder() {
        Location location = new Location(3,3);
        location.setX(1);
        location.setY(2);
        location.move(Directions.South);

        assertEquals(1,location.getX());
        assertEquals(0,location.getY());
    }

    @Test
    void movementOfTheObjectIfItIsOnTheWesternBorder() {
        Location location = new Location(3,3);
        location.setX(0);
        location.setY(1);
        location.move(Directions.West);

        assertEquals(2,location.getX());
        assertEquals(1,location.getY());
    }

    @Test
    void movementOfTheObjectIfItIsOnTheEasternBorder() {
        Location location = new Location(3,3);
        location.setX(2);
        location.setY(1);
        location.move(Directions.East);

        assertEquals(0,location.getX());
        assertEquals(1,location.getY());
    }
    @Test
    void movementOfTheObjectIfItIsOnTheNorthEasterBorder() {
        Location location = new Location(3,3);
        location.setX(2);
        location.setY(0);
        location.move(Directions.NorthEast);

        assertEquals(0,location.getX());
        assertEquals(2,location.getY());
    }

    @Test
    void movementOfTheObjectIfItIsOnTheNorthWesternBorder() {
        Location location = new Location(3,3);
        location.setX(0);
        location.setY(0);
        location.move(Directions.NorthWest);

        assertEquals(2,location.getX());
        assertEquals(2,location.getY());
    }
    @Test
    void movementOfTheObjectIfItIsOnTheSouthWesternBorder() {
        Location location = new Location(3,3);
        location.setX(0);
        location.setY(2);
        location.move(Directions.SouthWest);

        assertEquals(2,location.getX());
        assertEquals(0,location.getY());
    }

    @Test
    void movementOfTheObjectIfItIsOnTheSouthEasternBorder() {
        Location location = new Location(3,3);
        location.setX(2);
        location.setY(2);
        location.move(Directions.SouthEast);

        assertEquals(0,location.getX());
        assertEquals(0,location.getY());
    }
    @Test
    void setLocationIfItIsBeyondBorders() {
        Location location = new Location(2,2);
        location.setX(5);
        location.setY(-5);
        assertEquals(1,location.getX());
        assertEquals(1,location.getY());
    }
}