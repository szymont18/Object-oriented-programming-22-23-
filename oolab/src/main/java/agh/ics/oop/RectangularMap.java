package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public final Vector2d leftLowCorner;
    public final Vector2d rightUpCorner;

    private List<Animal> animals = new ArrayList<Animal>();

    private HashMap<Vector2d, Animal> occupiedPositions = new HashMap<Vector2d, Animal>();

    private MapVisualizer visualizer = new MapVisualizer(this);


    public RectangularMap(int width, int height){
        this.leftLowCorner = new Vector2d(0,0);
        this.rightUpCorner = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(rightUpCorner) && position.follows(leftLowCorner) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition()) &&
                animal.getPosition().precedes(rightUpCorner) &&
                animal.getPosition().follows(leftLowCorner)){
            this.animals.add(animal);
            occupiedPositions.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (occupiedPositions.get(position) != null);

    }

    @Override
    public Object objectAt(Vector2d position) {
        return occupiedPositions.get(position);
    }

    @Override
    public String toString(){
        return visualizer.draw(leftLowCorner,rightUpCorner);
    }

    /**
     Change status of the map when animal move to another position
     */
    public void changeStatus(Vector2d start, Vector2d end, Animal animal){
        this.occupiedPositions.put(start, null);
        this.occupiedPositions.put(end, animal);
    }
}
