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

        Animal northDog = new Animal(new Vector2d(2,2), MapDirection.NORTH);
        Animal eastDog = new Animal(new Vector2d(2,2), MapDirection.EAST);
        Animal southDog = new Animal(new Vector2d(2,2), MapDirection.SOUTH);
        Animal westDog = new Animal(new Vector2d(2,2), MapDirection.WEST);

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


        //Given: IWorld Map, InitialPosition for each animal, MoveDirection for each animal and engine;
        IWorldMap map1 = new RectangularMap(5,5);
        IWorldMap map2 = new RectangularMap(5,5);
        IWorldMap map3 = new RectangularMap(5,5);
        IWorldMap map4 = new RectangularMap(5,5);
        IWorldMap map5 = new RectangularMap(5,5);
        IWorldMap map6 = new RectangularMap(5,5);
        IWorldMap map7 = new RectangularMap(5,5);
        IWorldMap map8 = new RectangularMap(5,5);
        IWorldMap map9 = new RectangularMap(5,5);


        Vector2d initialPosition[] = {new Vector2d(2,2), new Vector2d(2,3)};
        Vector2d initialPosition1[] = {new Vector2d(2,2)};
        Vector2d initialPosition2[] = {new Vector2d(2,2), new Vector2d(3,0), new Vector2d(4,1)};


        String[] sMoves1 = {"f"};
        String[] sMoves2 = {"l","r","f","r","r","r","f","r","r","r","f"};
        String[] sMoves3 = {"r","r","r","f","r","r","r","f","r","r","r","f"};
        String[] sMoves4 = {"r","r","r","r","r","f"};
        String[] sMoves5 = {"f","f","f","f","f","f"};
        String[] sMoves6 = {"r","f","f","f","f","f"};
        String[] sMoves7 = {"l","f","f","f","f","f"};
        String[] sMoves8 = {"l","l","f","f","f","f"};
        String[] sMoves9 = {"l","l","f","f","r","r","b","r","b","f","l","b","r","r","f"};


        MoveDirection[] moves1 = OptionsParser.parse(sMoves1);
        MoveDirection[] moves2 = OptionsParser.parse(sMoves2);
        MoveDirection[] moves3 = OptionsParser.parse(sMoves3);
        MoveDirection[] moves4 = OptionsParser.parse(sMoves4);
        MoveDirection[] moves5 = OptionsParser.parse(sMoves5);
        MoveDirection[] moves6 = OptionsParser.parse(sMoves6);
        MoveDirection[] moves7 = OptionsParser.parse(sMoves7);
        MoveDirection[] moves8 = OptionsParser.parse(sMoves8);
        MoveDirection[] moves9 = OptionsParser.parse(sMoves9);



        IEngine engine1 = new SimulationEngine(moves1, map1, initialPosition);
        IEngine engine2 = new SimulationEngine(moves2, map2, initialPosition);
        IEngine engine3 = new SimulationEngine(moves3, map3, initialPosition);
        IEngine engine4 = new SimulationEngine(moves4, map4, initialPosition);
        IEngine engine5 = new SimulationEngine(moves5, map5, initialPosition1);
        IEngine engine6 = new SimulationEngine(moves6, map6, initialPosition1);
        IEngine engine7 = new SimulationEngine(moves7, map7, initialPosition1);
        IEngine engine8 = new SimulationEngine(moves8, map8, initialPosition1);
        IEngine engine9 = new SimulationEngine(moves9, map9, initialPosition2);



        //When: engine worked
        engine1.run();
        engine2.run();
        engine3.run();
        engine4.run();
        engine5.run();
        engine6.run();
        engine7.run();
        engine8.run();
        engine9.run();


        //Then: Check if any of the animals did not enter the others and did not leave the map
        assertEquals(new Animal(map1, new Vector2d(2,2),MapDirection.NORTH), map1.objectAt(new Vector2d(2,2)));
        assertEquals(new Animal(map2, new Vector2d(1,3),MapDirection.EAST), map2.objectAt(new Vector2d(1,3)));
        assertEquals(new Animal(map3, new Vector2d(3,2),MapDirection.WEST), map3.objectAt(new Vector2d(3,2)));
        assertEquals(new Animal(map4, new Vector2d(2,3),MapDirection.SOUTH), map4.objectAt(new Vector2d(2,3)));
        assertEquals(new Animal(map5, new Vector2d(2,5),MapDirection.NORTH), map5.objectAt(new Vector2d(2,5)));
        assertEquals(new Animal(map6, new Vector2d(5,2),MapDirection.EAST), map6.objectAt(new Vector2d(5,2)));
        assertEquals(new Animal(map7, new Vector2d(0,2),MapDirection.WEST), map7.objectAt(new Vector2d(0,2)));
        assertEquals(new Animal(map8, new Vector2d(2,0),MapDirection.SOUTH), map8.objectAt(new Vector2d(2,0)));

        assertEquals(new Animal(map9, new Vector2d(1,2),MapDirection.NORTH), map9.objectAt(new Vector2d(1,2)));
        assertEquals(new Animal(map9, new Vector2d(3,0),MapDirection.EAST), map9.objectAt(new Vector2d(3,0)));
        assertEquals(new Animal(map9, new Vector2d(3,2),MapDirection.EAST), map9.objectAt(new Vector2d(3,2)));
    }


}