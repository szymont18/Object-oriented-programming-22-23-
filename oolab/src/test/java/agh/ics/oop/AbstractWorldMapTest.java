package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void engineTest(){
//        Given: IWorld Map, InitialPosition for each animal, MoveDirection for each animal and engine;
        Vector2d initialPosition[] = {new Vector2d(2,2), new Vector2d(2,3)};
        Vector2d initialPosition1[] = {new Vector2d(2,2)};



        String[] sMoves1 = {"f"};
        String[] sMoves2 = {"l","r","f","r","r","r","f","r","r","r","f"};
        String[] sMoves3 = {"r","r","r","f","r","r","r","f","r","r","r","f"};
        String[] sMoves4 = {"r","r","r","r","r","f"};
        String[] sMoves5 = {"f","f","f","f","f","f"};
        String[] sMoves6 = {"r","f","f","f","f","f"};
        String[] sMoves7 = {"l","f","f","f","f","f"};
        String[] sMoves8 = {"l","l","f","f","f","f"};

//
//
        MoveDirection[] moves1 = OptionsParser.parse(sMoves1);
        MoveDirection[] moves2 = OptionsParser.parse(sMoves2);
        MoveDirection[] moves3 = OptionsParser.parse(sMoves3);
        MoveDirection[] moves4 = OptionsParser.parse(sMoves4);
        MoveDirection[] moves5 = OptionsParser.parse(sMoves5);
        MoveDirection[] moves6 = OptionsParser.parse(sMoves6);
        MoveDirection[] moves7 = OptionsParser.parse(sMoves7);
        MoveDirection[] moves8 = OptionsParser.parse(sMoves8);

//

//       //When: engine worked
        //Then: Check if any of the animals did not enter the others and did not leave the map
        IWorldMap map1 = new RectangularMap();
        IEngine engine1 = new SimulationEngine(moves1, map1, initialPosition);
        engine1.run();
        assertEquals(new Animal(map1, new Vector2d(2,2),MapDirection.NORTH), map1.objectAt(new Vector2d(2,2)));

        IWorldMap map2 = new RectangularMap();
        IEngine engine2 = new SimulationEngine(moves2, map2, initialPosition);
        engine2.run();
        assertEquals(new Animal(map2, new Vector2d(1,3),MapDirection.EAST), map2.objectAt(new Vector2d(1,3)));
//
        IWorldMap map3 = new RectangularMap();
        IEngine engine3 = new SimulationEngine(moves3, map3, initialPosition);
        engine3.run();
        assertEquals(new Animal(map3, new Vector2d(3,2),MapDirection.WEST), map3.objectAt(new Vector2d(3,2)));
//
        IWorldMap map4 = new RectangularMap();
        IEngine engine4 = new SimulationEngine(moves4, map4, initialPosition);
        engine4.run();
        assertEquals(new Animal(map4, new Vector2d(2,3),MapDirection.SOUTH), map4.objectAt(new Vector2d(2,3)));
//
        IWorldMap map5 = new RectangularMap();
        IEngine engine5 = new SimulationEngine(moves5, map5, initialPosition1);
        engine5.run();
        assertEquals(new Animal(map5, new Vector2d(2,8),MapDirection.NORTH), map5.objectAt(new Vector2d(2,8)));

        IWorldMap map6 = new RectangularMap();
        IEngine engine6 = new SimulationEngine(moves6, map6, initialPosition1);
        engine6.run();
        assertEquals(new Animal(map6, new Vector2d(7,2),MapDirection.EAST), map6.objectAt(new Vector2d(7,2)));

        IWorldMap map7 = new RectangularMap();
        IEngine engine7 = new SimulationEngine(moves7, map7, initialPosition1);
        engine7.run();
        assertEquals(new Animal(map7, new Vector2d(-3,2),MapDirection.WEST), map7.objectAt(new Vector2d(-3,2)));

        IWorldMap map8 = new RectangularMap();
        IEngine engine8 = new SimulationEngine(moves8, map8, initialPosition1);
        engine8.run();
        assertEquals(new Animal(map8, new Vector2d(2,-2),MapDirection.SOUTH), map8.objectAt(new Vector2d(2,-2)));
    }
}