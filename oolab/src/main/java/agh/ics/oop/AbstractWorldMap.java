package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected static MapVisualizer visualizer;

    protected HashMap<Vector2d, Object> occupiedPositions = new HashMap<Vector2d, Object>();

    protected static MapBoundary mapBoundary;

    public AbstractWorldMap(){
        mapBoundary = new MapBoundary();
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
        return visualizer.draw(mapBoundary.leftLowCorner,mapBoundary.rightUpCorner);
    }


    public void changeCorners(Vector2d start, Vector2d end){
        mapBoundary.positionChangeObserver(start, end);
    }

    public Vector2d[] getBoundaries(){
        return new Vector2d[]{mapBoundary.leftLowCorner, mapBoundary.rightUpCorner};
    }

    public Set<Vector2d> getAllPositions(){
        return occupiedPositions.keySet();
    }

}
