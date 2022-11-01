package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void isAt() {
        Animal dog1 = new Animal(new Vector2d(2, 2));
        Animal dog2 = new Animal(new Vector2d(1, 2));
        Animal dog3 = new Animal(new Vector2d(0, 0));
        Animal dog4 = new Animal(new Vector2d(3, 3));
        Animal dog5 = new Animal(new Vector2d(-10, 10));

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
        Animal testDog1 = new Animal(new Vector2d(2, 2));

        Animal northDog = new Animal(new Vector2d(2,2), MapDirection.NORTH);
        Animal eastDog = new Animal(new Vector2d(2,2), MapDirection.EAST);
        Animal southDog = new Animal(new Vector2d(2,2), MapDirection.SOUTH);
        Animal westDog = new Animal(new Vector2d(2,2), MapDirection.WEST);

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

        //Poruszanie się
        testDog1.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(2,3)),testDog1);

        testDog1.move(MoveDirection.BACKWARD);
        assertEquals(new Animal(new Vector2d(2,2)), testDog1);

        testDog1.move(MoveDirection.RIGHT);
        testDog1.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(3,2), MapDirection.EAST), testDog1);

        testDog1.move(MoveDirection.RIGHT);
        testDog1.move(MoveDirection.BACKWARD);
        assertEquals(new Animal(new Vector2d(3,3), MapDirection.SOUTH), testDog1);

        testDog1.move(MoveDirection.LEFT);
        testDog1.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(4,3), MapDirection.EAST), testDog1);

        testDog1.move(MoveDirection.LEFT);
        testDog1.move(MoveDirection.BACKWARD);
        assertEquals(new Animal(new Vector2d(4,2), MapDirection.NORTH), testDog1);


        //Wyjście poza granicę
        Animal testDog2 = new Animal(new Vector2d(4, 4));
        Animal testDog3 = new Animal(new Vector2d(0,0), MapDirection.SOUTH);

        testDog2.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(4,4)),testDog2);

        testDog2.move(MoveDirection.RIGHT);
        testDog2.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(4,4), MapDirection.EAST),testDog2);

        testDog3.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(0,0), MapDirection.SOUTH),testDog3);

        testDog3.move(MoveDirection.RIGHT);
        testDog3.move(MoveDirection.FORWARD);
        assertEquals(new Animal(new Vector2d(0,0), MapDirection.WEST),testDog3);

        //Inicjalizacja ze złymi granicami
        //Jeżeli ktoś poda wartości startowe które nie mieszczą w ramach planszy to zwierzę jest rzucane na pole (2,2)
        Animal testdog4 = new Animal(new Vector2d(100, 4));
        Animal testdog5 = new Animal(new Vector2d(-12, 6), MapDirection.SOUTH);

        assertEquals(new Animal(new Vector2d(2,2)), testdog4);
        assertEquals(new Animal(new Vector2d(2,2), MapDirection.SOUTH), testdog5);


    }
}