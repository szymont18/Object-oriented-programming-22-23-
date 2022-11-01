package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection aDirection = MapDirection.NORTH;
    private Vector2d coordinates;

    public Animal(){
        coordinates = new Vector2d(2,2);
    }
    public Animal(Vector2d other){
        if(other.x >= 0 && other.x <= 4 && other.y >= 0 && other.y <= 4) coordinates = other;
        else coordinates = new Vector2d(2, 2);

    }
    public Animal(Vector2d other, MapDirection dir){
        if(other.x >= 0 && other.x <= 4 && other.y >= 0 && other.y <= 4) coordinates = other;
        else coordinates = new Vector2d(2, 2);
        aDirection = dir;
    }
    @Override
    public String toString(){
        return "Direction: " + aDirection.toString() + ". Coordinates: " + coordinates.toString();
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

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> {
                aDirection = aDirection.next();
            }
            case LEFT -> {
                aDirection = aDirection.previous();
            }
            case FORWARD -> {
                switch (aDirection){
                    case NORTH -> {
                        if (coordinates.y + 1 <= 4) coordinates = coordinates.add(new Vector2d(0, 1));
                    }
                    case EAST -> {
                        if (coordinates.x + 1 <= 4) coordinates = coordinates.add(new Vector2d(1,0));
                    }
                    case WEST -> {
                        if (coordinates.x - 1 >= 0) coordinates = coordinates.add(new Vector2d(-1, 0));
                    }
                    case SOUTH -> {
                        if (coordinates.y - 1>= 0) coordinates = coordinates.add(new Vector2d(0, -1));
                    }
                }
            }

            case BACKWARD -> {
                switch (aDirection){
                    case NORTH -> {
                        if (coordinates.y - 1 >= 0) coordinates = coordinates.add(new Vector2d(0,-1));
                    }
                    case EAST -> {
                        if (coordinates.x - 1 >= 0) coordinates = coordinates.add(new Vector2d(-1, 0));
                    }
                    case WEST -> {
                        if (coordinates.x + 1 <= 4) coordinates = coordinates.add(new Vector2d(1,0));
                    }
                    case SOUTH -> {
                        if (coordinates.y + 1 <= 4) coordinates = coordinates.add(new Vector2d(0, 1));
                    }

                }
            }
        }

    }



}
