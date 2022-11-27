package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection aDirection = MapDirection.NORTH;
    private Vector2d coordinates;
    private IWorldMap map;

    private IPositionChangeObserver observerMap;




    public Animal(Vector2d other){
        if(other.x >= 0 && other.x <= 4 && other.y >= 0 && other.y <= 4) coordinates = other;
        else coordinates = new Vector2d(2, 2);

    }
    public Animal(Vector2d other, MapDirection dir){
        if(other.x >= 0 && other.x <= 4 && other.y >= 0 && other.y <= 4) coordinates = other;
        else coordinates = new Vector2d(2, 2);
        aDirection = dir;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.coordinates = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection initialDirection){
        this.map = map;
        this.coordinates = initialPosition;
        this.aDirection = initialDirection;
    }


    @Override
    public String toString(){
        return aDirection.toString();
    }

    public boolean isAt(Vector2d position){
        return coordinates.x == position.x && coordinates.y == position.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return aDirection == animal.aDirection && coordinates.equals(animal.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aDirection, coordinates);
    }


    private Vector2d moveTo(MoveDirection direction){
        int fOrb = (direction == MoveDirection.FORWARD) ? 1:-1;
        Vector2d tmp = this.aDirection.toUnitVector();
        return new Vector2d(tmp.x * fOrb + this.coordinates.x, tmp.y*fOrb + this.coordinates.y);

    }

    public void move(MoveDirection direction){
        if (direction == MoveDirection.RIGHT) this.aDirection = this.aDirection.next();
        else if (direction == MoveDirection.LEFT) this.aDirection = this.aDirection.previous();
        else{
            Vector2d afterMove = moveTo(direction);
            Vector2d beforeMove = new Vector2d(this.coordinates.x, this.coordinates.y);
            this.coordinates = (this.map.canMoveTo(afterMove)) ? afterMove : this.coordinates;


            //Change status of the map after move
            observerMap.positionChangeObserver(beforeMove, this.coordinates);
        }
    }

    public Vector2d getPosition(){
        return this.coordinates;
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observerMap = observer;
    }
}
