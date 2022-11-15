package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    IWorldMap test1 = new RectangularMap(5,5);
    IWorldMap test2 = new RectangularMap(5,5);
    Animal animal1 = new Animal(test1, new Vector2d(2,2));
    Animal animal2 = new Animal(test1, new Vector2d(2,3));
    Animal animal3 = new Animal(test1, new Vector2d(1,1));
    Animal animal4 = new Animal(test1, new Vector2d(2,1));
    Animal animal5 = new Animal(test1, new Vector2d(3,3));
    Animal animal6 = new Animal(test1, new Vector2d(3,5));
    @Test
    void canMoveTo() {
//        Given:
//        IWorldMap test1 5x5 and Animals



//        When:
//        There are 6 animals. One of them want to sit on the seconds one square.
        test1.place(animal1);
        test1.place(animal2);
        test1.place(animal3);
        test1.place(animal4);
        test1.place(animal5);
        test1.place(animal6);


//        Then:it is not allowed for two animals to sit in the same place.
//        Animal cannot move to the position out of the map

        assertFalse(test1.canMoveTo(animal1.getPosition()));
        assertFalse(test1.canMoveTo(animal2.getPosition()));
        assertFalse(test1.canMoveTo(animal3.getPosition()));
        assertFalse(test1.canMoveTo(animal4.getPosition()));
        assertFalse(test1.canMoveTo(animal5.getPosition()));
        assertFalse(test1.canMoveTo(animal6.getPosition()));

        assertTrue(test1.canMoveTo(new Vector2d(1,2)));
        assertTrue(test1.canMoveTo(new Vector2d(2,0)));
        assertTrue(test1.canMoveTo(new Vector2d(0,0)));
        assertTrue(test1.canMoveTo(new Vector2d(5,5)));
        assertTrue(test1.canMoveTo(new Vector2d(4,2)));
        assertTrue(test1.canMoveTo(new Vector2d(5,3)));

        assertFalse(test1.canMoveTo(new Vector2d(-1,0)));
        assertFalse(test1.canMoveTo(new Vector2d(0,-1)));
        assertFalse(test1.canMoveTo(new Vector2d(6,0)));
        assertFalse(test1.canMoveTo(new Vector2d(0,6)));
    }

    @Test
    void place() {
//        Given: IWorldMap test2 5x5 and the animals
//        When: Animals want to sit on the map's square
        test2.place(animal1);
        test2.place(animal2);
        test2.place(animal3);
        test2.place(animal4);
        test2.place(animal5);
        test2.place(animal6);

//        Then: Check if place function put the animal in the correct position
        assertEquals(animal1, test2.objectAt( new Vector2d(2,2)));
        assertEquals(animal2, test2.objectAt( new Vector2d(2,3)));
        assertEquals(animal3, test2.objectAt( new Vector2d(1,1)));
        assertEquals(animal4, test2.objectAt( new Vector2d(2,1)));
        assertEquals(animal5, test2.objectAt( new Vector2d(3,3)));
        assertEquals(animal6, test2.objectAt( new Vector2d(3,5)));

        assertNull(test2.objectAt(new Vector2d(0,0)));
        assertNull(test2.objectAt(new Vector2d(0,2)));
        assertNull(test2.objectAt(new Vector2d(5,6)));
        assertNull(test2.objectAt(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE)));
        assertNull(test2.objectAt(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE)));
        assertNull(test2.objectAt(new Vector2d(Integer.MIN_VALUE,Integer.MAX_VALUE)));

    }

    @Test
    void isOccupied() {
//        Given: IWorldMap test1 5x5 and animals
//        When:Animals are sitting on the map
        test1.place(animal1);
        test1.place(animal2);
        test1.place(animal3);
        test1.place(animal4);
        test1.place(animal5);
        test1.place(animal6);

//        Then:Check if position is occupied by animal
        assertTrue(test1.isOccupied(new Vector2d(2,2)));
        assertTrue(test1.isOccupied(new Vector2d(2,3)));
        assertTrue(test1.isOccupied(new Vector2d(1,1)));
        assertTrue(test1.isOccupied(new Vector2d(2,1)));
        assertTrue(test1.isOccupied(new Vector2d(3,3)));
        assertTrue(test1.isOccupied(new Vector2d(3,5)));

        assertFalse(test1.isOccupied(new Vector2d(1,2)));
        assertFalse(test1.isOccupied(new Vector2d(2,4)));
        assertFalse(test1.isOccupied(new Vector2d(0,0)));
        assertFalse(test1.isOccupied(new Vector2d(5,5)));
        assertFalse(test1.isOccupied(new Vector2d(4,2)));
        assertFalse(test1.isOccupied(new Vector2d(5,3)));
    }


    @Test
    void objectAt() {
//        Given: IWorldMap test1 5x5 and animals
//        When:Animals are sitting on the map
        test1.place(animal1);
        test1.place(animal2);
        test1.place(animal3);
        test1.place(animal4);
        test1.place(animal5);
        test1.place(animal6);

//        Then: Check if the animals sitting where they should
        assertEquals(animal1, test1.objectAt( new Vector2d(2,2)));
        assertEquals(animal2, test1.objectAt( new Vector2d(2,3)));
        assertEquals(animal3, test1.objectAt( new Vector2d(1,1)));
        assertEquals(animal4, test1.objectAt( new Vector2d(2,1)));
        assertEquals(animal5, test1.objectAt( new Vector2d(3,3)));
        assertEquals(animal6, test1.objectAt( new Vector2d(3,5)));
    }
}