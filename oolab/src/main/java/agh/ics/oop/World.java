package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {

    public static void main(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);

        HashMap<Vector2d, Object> worldMap = new HashMap<Vector2d, Object>();
        IWorldMap map = new RectangularMap(worldMap);
        IWorldMap grassMap = new GrassField(10, worldMap);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,3), new Vector2d(4, 4)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
//        f b r l f f r r f f f f f f f f
        //f l r b b b l f r b r r l b r b b r f f f f f l r f f r b f f l r f f b r f f f r l f


    }


}
