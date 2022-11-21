package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void isAt() {
//        Given: Animals
//        When: Animals is placed in 2D space
        Animal dog1 = new Animal(new Vector2d(2, 2));
        Animal dog2 = new Animal(new Vector2d(1, 2));
        Animal dog3 = new Animal(new Vector2d(0, 0));
        Animal dog4 = new Animal(new Vector2d(3, 3));
        Animal dog5 = new Animal(new Vector2d(-10, 10));


//        Then:Check if each animal is placed where it should be

        //Not Null
        assertNotNull(dog1);
        assertNotNull(dog2);
        assertNotNull(dog3);
        assertNotNull(dog4);
        assertNotNull(dog5);

        //True
        assertTrue(dog1.isAt(new Vector2d(2,2)));
        assertTrue(dog2.isAt(new Vector2d(1, 2)));
        assertTrue(dog3.isAt(new Vector2d(0,0)));
        assertTrue(dog4.isAt(new Vector2d(3,3)));
        assertTrue(dog5.isAt(new Vector2d(2,2)));

        //False
        assertFalse(dog1.isAt(new Vector2d(1,5)));
        assertFalse(dog2.isAt(new Vector2d(2,2)));
        assertFalse(dog3.isAt(new Vector2d(0,1)));
        assertFalse(dog4.isAt(new Vector2d(-10,10)));
        assertFalse(dog5.isAt(new Vector2d(10,-10)));
    }

    @Test
    void move() {
//        Given: Animals with initialPosition and Direction
        Animal testDog1 = new Animal(new Vector2d(2, 2));

        Animal northDog = new Animal(new Vector2d(2, 2), MapDirection.NORTH);
        Animal eastDog = new Animal(new Vector2d(2, 2), MapDirection.EAST);
        Animal southDog = new Animal(new Vector2d(2, 2), MapDirection.SOUTH);
        Animal westDog = new Animal(new Vector2d(2, 2), MapDirection.WEST);

//        When:The animals turns to the right and to the left

//        Then:Check if animals have got correct Direction

        //Orientacja
        testDog1.move(MoveDirection.RIGHT);
        assertEquals(eastDog, testDog1);
        testDog1.move(MoveDirection.RIGHT);
        assertEquals(southDog, testDog1);
        testDog1.move(MoveDirection.RIGHT);
        assertEquals(westDog, testDog1);
        testDog1.move(MoveDirection.RIGHT);
        assertEquals(northDog, testDog1);

        testDog1.move(MoveDirection.LEFT);
        assertEquals(westDog, testDog1);
        testDog1.move(MoveDirection.LEFT);
        assertEquals(southDog, testDog1);
        testDog1.move(MoveDirection.LEFT);
        assertEquals(eastDog, testDog1);
        testDog1.move(MoveDirection.LEFT);
        assertEquals(northDog, testDog1);


    }

}