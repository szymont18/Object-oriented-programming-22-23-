package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private List<Animal> animals = new ArrayList<Animal>();


    public RectangularMap(int width, int height){
        super();
        leftLowCorner = new Vector2d(0,0);
        rightUpCorner = new Vector2d(width, height);
    }

    public RectangularMap(){
        super();
    }


    @Override
    public boolean place(Object object) {
        Animal animal = (Animal) object;
        Object objAt = objectAt(animal.getPosition());
        if (objAt != null && objAt.getClass() == Grass.class){
            Grass grass = (Grass) objAt;
            grass.replant();
        }
        if (objAt == null || objAt.getClass() != animal.getClass()){
            this.animals.add(animal);
            occupiedPositions.put(animal.getPosition(), animal);

            changeCorners(animal.getPosition());

            return true;
        }
        return false;
    }

    /**
     Change status of the map when animal move to another position
     */
    public void changeStatus(Vector2d start, Vector2d end, Animal animal){
        occupiedPositions.put(start, null);


        Object potentialGrass = objectAt(end);
        if (potentialGrass != null && potentialGrass.getClass() == Grass.class){
            occupiedPositions.put(end, animal);
            Grass grass = (Grass) potentialGrass;
            grass.replant();
        }
        else occupiedPositions.put(end, animal);
        changeCorners(end);
    }
}
