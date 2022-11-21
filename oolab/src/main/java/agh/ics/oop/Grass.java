package agh.ics.oop;

public class Grass {
    private Vector2d coordinates;
    private IWorldMap map;

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
        GrassField gField = (GrassField) this.map;
        gField.replantGrass(this);
    }
}
