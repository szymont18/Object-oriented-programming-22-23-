package agh.ics.oop;

import java.util.concurrent.ThreadLocalRandom;

public class Grass implements IMapElement {
    private Vector2d coordinates;
    private IWorldMap map;

    private IPositionChangeObserver observerMap;

    public Grass(Vector2d initialPosition, IWorldMap map){
        this.coordinates = initialPosition;
        this.map = map;
    }

    @Override
    public String toString(){
        return "*";
    }

    public Vector2d getPosition(){
        return this.coordinates;
    }

    public void changeCordinates(Vector2d position){
        this.coordinates = position;
    }

    public void replant(){
        GrassField gMap = (GrassField) map;
        int grassNumber = gMap.grassNumber;
        while (true) {
            int xPos = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(grassNumber * 10));
            int yPos = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(grassNumber * 10));
            Vector2d newPosition = new Vector2d(xPos, yPos);
            if (map.objectAt(newPosition) == null) {

                observerMap.positionChangeObserver(this.getPosition(), newPosition);
                changeCordinates(newPosition);
                break;
            }
        }

    }

    public void addObserver(IPositionChangeObserver observer){
        this.observerMap = observer;
    }

    public String getImagePath() {
        return "src/main/resources/grass.png";
    }
}
