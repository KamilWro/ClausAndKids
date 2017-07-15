package Test;

import Main.Configuration;
import Model.Graph.Distance;
import Model.Graph.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kamil on 14.07.17.
 */
class DistanceTest {
    Configuration conf;
    @BeforeEach
    void setUp() throws Exception {
        conf = new Configuration(0,3,3,1);

    }

    @Test
    void objectIsInTheNorthIfThereIsAConnection() {
        Location source = new Location(conf.row, conf.column);
        source.setX(0);
        source.setY(1);
        Distance distance = new Distance(conf,source,conf.area);
        distance.setBorder();
        Location target = new Location(conf.row, conf.column);
        target.setX(2);
        target.setY(1);
        boolean isAround = distance.isAround(target);
        assertEquals(true,isAround);
    }

    @Test
    void objectIsInTheSouthIfThereIsAConnection() {
        Location source = new Location(conf.row, conf.column);
        source.setX(2);
        source.setY(1);
        Distance distance = new Distance(conf,source,conf.area);
        distance.setBorder();
        Location target = new Location(conf.row, conf.column);
        target.setX(0);
        target.setY(1);
        boolean isAround = distance.isAround(target);
        assertEquals(true,isAround);
    }

    @Test
    void objectIsInTheEastIfThereIsAConnection() {
        Location source = new Location(conf.row, conf.column);
        source.setX(1);
        source.setY(2);
        Distance distance = new Distance(conf,source,conf.area);
        distance.setBorder();
        Location target = new Location(conf.row, conf.column);
        target.setX(1);
        target.setY(0);
        boolean isAround = distance.isAround(target);
        assertEquals(true,isAround);
    }

    @Test
    void objectIsInTheWestIfThereIsAConnection() {
        Location source = new Location(conf.row, conf.column);
        source.setX(1);
        source.setY(0);
        Distance distance = new Distance(conf,source,conf.area);
        distance.setBorder();
        Location target = new Location(conf.row, conf.column);
        target.setX(1);
        target.setY(2);
        boolean isAround = distance.isAround(target);
        assertEquals(true,isAround);
    }

    @Test
    void returnFalseIfThereIsNotAConnection() throws Exception {
        conf = new Configuration(0,5,5,1);
        Location source = new Location(conf.row, conf.column);
        source.setX(1);
        source.setY(1);
        Distance distance = new Distance(conf,source,conf.area);
        distance.setBorder();
        Location target = new Location(conf.row, conf.column);
        target.setX(4);
        target.setY(4);
        boolean isAround = distance.isAround(target);
        assertEquals(false,isAround);
    }
}