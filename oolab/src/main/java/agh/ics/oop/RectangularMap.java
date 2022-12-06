package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{



    public RectangularMap(int width, int height){
        super();
    }

    public RectangularMap(HashMap <Vector2d, Object> worldMap){
        super();
        this.occupiedPositions = worldMap;
    }


    @Override
    public boolean place(Object object) throws IllegalArgumentException {
        Animal animal = (Animal) object;
        Object objAt = objectAt(animal.getPosition());

        if (objAt != null && objAt.getClass() == Animal.class) throw new IllegalArgumentException();

        if (objAt != null && objAt.getClass() == Grass.class){
            Grass grass = (Grass) objAt;
            grass.replant();
        }
        if (objAt == null || objAt.getClass() != animal.getClass()){

            occupiedPositions.put(animal.getPosition(), animal);
            animal.addObserver(this);
            changeCorners(null, animal.getPosition());

            return true;
        }
        return false;
    }

    /**
     Change status of the map when animal move to another position
     */
    @Override
    public void positionChangeObserver(Vector2d start, Vector2d end){
        Animal animal = (Animal) objectAt(start);
        occupiedPositions.remove(start);


        Object potentialGrass = objectAt(end);

        if (potentialGrass != null && potentialGrass.getClass() == Grass.class){
            Grass grass = (Grass) potentialGrass;
            grass.replant();
            occupiedPositions.put(end, animal);
        }
        else occupiedPositions.put(end, animal);
        changeCorners(start, end);
    }
}
