package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {

    public static void main(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,0), new Vector2d(4, 1) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
//        {"r","f","f","b","f","r","f","l","b","b","f","r","f","b","r"};

    }


}
