package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {

    public static void main(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);

        IWorldMap map = new RectangularMap();
        IWorldMap grassMap = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(1, 6)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
//        f b r l f f r r f f f f f f f f
        //f l r b b b l f r b r r l b r b b r f f f f f l r f f r b f f l r f f b r f f f r l f


    }


}
