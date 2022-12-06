package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    public int grassNumber;


    public GrassField(int grassNumber, HashMap<Vector2d, Object>worldMap){
        super();
        this.grassNumber = grassNumber;
        this.occupiedPositions = worldMap;
        for (int i = 0; i < grassNumber; i++){

               plantGrass();
        }
    }

    @Override
    public boolean place(Object object) {
        Grass grass = (Grass) object;
        Object objAt = objectAt(grass.getPosition());

        if (objAt != null && objAt.getClass() == Animal.class) throw new IllegalArgumentException();

        if (!isOccupied(grass.getPosition())) {

            occupiedPositions.put(grass.getPosition(), grass);

            grass.addObserver(this);

            changeCorners(null, grass.getPosition());
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
//                Already done in place(grass1)
//                occupiedPositions.put(newPosition, grass1);

                break;
            }
        }
    }

    @Override
    public void positionChangeObserver(Vector2d start, Vector2d end) {

        Grass grass = (Grass) objectAt(start);

        occupiedPositions.remove(start);
        occupiedPositions.put(end, grass);
        changeCorners(start, end);
    }
}
