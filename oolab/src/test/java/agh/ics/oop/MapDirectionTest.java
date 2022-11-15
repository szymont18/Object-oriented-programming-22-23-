package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        //Not null
        assertNotNull(MapDirection.NORTH.next());
        assertNotNull(MapDirection.EAST.next());
        assertNotNull(MapDirection.SOUTH.next());
        assertNotNull(MapDirection.WEST.next());

        //Equal
        assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
    }

    @Test
    void previous() {
        //Not null
        assertNotNull(MapDirection.NORTH.previous());
        assertNotNull(MapDirection.EAST.previous());
        assertNotNull(MapDirection.SOUTH.previous());
        assertNotNull(MapDirection.WEST.previous());

        //Equal
        assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
        assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
        assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
        assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
    }

    @Test
    void toUnitVector() {
        //Not Null
        assertNotNull(MapDirection.NORTH.toUnitVector());
        assertNotNull(MapDirection.EAST.toUnitVector());
        assertNotNull(MapDirection.SOUTH.toUnitVector());
        assertNotNull(MapDirection.WEST.toUnitVector());

        //Equals
        assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector());
        assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector());
    }
}