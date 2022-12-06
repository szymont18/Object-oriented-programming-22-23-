package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;


public class MapBoundary implements IPositionChangeObserver{
    SortedMap<Vector2d, Integer> xAxis = new TreeMap<Vector2d, Integer>(Comparator.comparingInt(p -> p.x));
    SortedMap<Vector2d, Integer> yAxis = new TreeMap<Vector2d, Integer>(Comparator.comparingInt(p -> p.y));

    Vector2d leftLowCorner = new Vector2d(0,0);
    Vector2d rightUpCorner = new Vector2d(0,0);
    @Override
    public void positionChangeObserver(Vector2d oldPosition, Vector2d newPosition) {
        if (oldPosition != null) removeKey(oldPosition);
        addKey(newPosition);
        this.leftLowCorner = new Vector2d(xAxis.firstKey().x, yAxis.firstKey().y);
        this.rightUpCorner = new Vector2d(xAxis.lastKey().x, yAxis.lastKey().y);
    }

    private void removeKey(Vector2d key){
        if (xAxis.get(key) != null){
            Integer number = xAxis.get(key) - 1;
            if (number.equals(0)) xAxis.remove(key);
            else {
                xAxis.put(key, number);
            }
        }
        if (yAxis.get(key) != null){
            Integer number = yAxis.get(key) - 1;
            if (number.equals(0)) yAxis.remove(key);
            else {
                yAxis.put(key, number);
            }
        }
    }

    private void addKey(Vector2d key){
        if (xAxis.get(key) != null){
            xAxis.put(key, xAxis.get(key) + 1);
        }
        else xAxis.put(key, 1);

        if (yAxis.get(key) != null){
            yAxis.put(key, yAxis.get(key) + 1);
        }
        else yAxis.put(key, 1);
    }

}
