package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected static Vector2d leftLowCorner;
    protected static Vector2d rightUpCorner;

    protected static MapVisualizer visualizer;

    protected HashMap<Vector2d, Object> occupiedPositions = new HashMap<Vector2d, Object>();

    public AbstractWorldMap(){
        leftLowCorner = new Vector2d(0,0);
        rightUpCorner = new Vector2d(0,0);
        visualizer = new MapVisualizer(this);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (objectAt(position) == null || objectAt(position).getClass() == Grass.class);
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


    public void changeCorners(Vector2d position){
        leftLowCorner = position.lowerLeft(leftLowCorner);
        rightUpCorner = position.upperRight(rightUpCorner);
    }

}
