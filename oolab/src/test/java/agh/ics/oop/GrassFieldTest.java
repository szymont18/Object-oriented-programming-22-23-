package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    HashMap <Vector2d, Object> worldMap1 = new HashMap<Vector2d, Object>();
    GrassField field1 = new GrassField(0, worldMap1);
    Grass grass1 = new Grass(new Vector2d(1,2), field1);
    Grass grass2 = new Grass(new Vector2d(2,2), field1);
    Grass grass3 = new Grass(new Vector2d(3,7), field1);
    Grass grass4 = new Grass(new Vector2d(Integer.MAX_VALUE,Integer.MIN_VALUE), field1);
    Grass grass5 = new Grass(new Vector2d(Integer.MIN_VALUE,Integer.MAX_VALUE), field1);

    @Test
    void place() {
//        Given: GrassField and grass


//        When grass is growing on the grassfield (object grass is placed on the grassfield)
        field1.place(grass1);
        field1.place(grass2);
        field1.place(grass3);
        field1.place(grass4);
        field1.place(grass5);


//        Then: Check if positions are occupied
        assertEquals(grass1, field1.objectAt(grass1.getPosition()));
        assertEquals(grass2, field1.objectAt(grass2.getPosition()));
        assertEquals(grass3, field1.objectAt(grass3.getPosition()));
        assertEquals(grass4, field1.objectAt(grass4.getPosition()));
        assertEquals(grass5, field1.objectAt(grass5.getPosition()));

    }


    @Test
    void replantGrass() {
//        Given: Grassfield and grass
        field1.place(grass1);
        field1.place(grass2);
        field1.place(grass3);
        field1.place(grass4);
        field1.place(grass5);

//        When: Grass has placed on the grassfield
        field1.grassNumber = 5;
        grass1.replant();
        grass2.replant();
        grass3.replant();
        grass4.replant();
        grass5.replant();

//        Then: Replant the tree and check if the information about their cooridnates is stored corectly
        assertEquals(grass1, field1.objectAt(grass1.getPosition()));
        assertEquals(grass2, field1.objectAt(grass2.getPosition()));
        assertEquals(grass3, field1.objectAt(grass3.getPosition()));
        assertEquals(grass4, field1.objectAt(grass4.getPosition()));
        assertEquals(grass5, field1.objectAt(grass5.getPosition()));

    }
}