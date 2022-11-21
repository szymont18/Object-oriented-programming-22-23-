package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    private List<Grass> grassArray = new ArrayList<Grass>();
    public int grassNumber;
    public GrassField(int grassNumber){
        super();
        this.grassNumber = grassNumber;
        for (int i = 0; i < grassNumber; i++){

               plantGrass();
        }
    }

    @Override
    public boolean place(Object object) {
        Grass grass = (Grass) object;
        if (!isOccupied(grass.getPosition())) {
            this.grassArray.add(grass);
            occupiedPositions.put(grass.getPosition(), grass);
            changeCorners(grass.getPosition());
            return true;
        }
        return false;
    }

    public void plantGrass() {
        while (true) {
            int xPos = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(grassNumber * 10));
            int yPos = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(grassNumber * 10));
            Vector2d newPosition = new Vector2d(xPos, yPos);
            if (objectAt(newPosition) == null) {
                Grass grass1 = new Grass(newPosition, this);
                place(grass1);
                occupiedPositions.put(newPosition, grass1);
                grassArray.add(grass1);

                break;
            }
        }
    }

    public void replantGrass(Grass grass) {
        while (true) {
            int xPos = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(grassNumber * 10));
            int yPos = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(grassNumber * 10));
            Vector2d newPosition = new Vector2d(xPos, yPos);
            if (objectAt(newPosition) == null) {
                grass.changeCordinates(newPosition);
                occupiedPositions.put(newPosition, grass);

                break;
            }
        }
    }

}
