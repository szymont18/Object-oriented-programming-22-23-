package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import javafx.scene.Scene;

import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class World {

    public static void main(String[] args) {
//        GUI
//        try {
//        Application.launch(App.class, args);
//        }
//        catch (IllegalArgumentException exception){
//            System.out.println(exception);
//        }

        try {
//        Without GUI
        MoveDirection[] directions = OptionsParser.parse(args);

        HashMap<Vector2d, Object> worldMap = new HashMap<Vector2d, Object>();
        IWorldMap map = new RectangularMap(worldMap);
        IWorldMap grassMap = new GrassField(5, worldMap);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(4, 4)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();
        }
        catch (IllegalArgumentException exception){
            System.out.println(exception);
        }
//        f b r l f f r r f f f f f f f f
//        f l r b b b l f r b r r l b r b b r f f f f f l r f f r b f f l r f f b r f f f r l f

    }


}
