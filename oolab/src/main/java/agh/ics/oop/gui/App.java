package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class App extends Application {
    public GridPane gridPane = new GridPane();
    private final int width = 50;
    private final int height = 50;

    public void start(Stage primaryStage){
        primaryStage.setTitle("World");
        Parameters args = getParameters();
        List<String> argsRaw = args.getRaw();
        String[] argsTab = new String[argsRaw.size()];
        argsRaw.toArray(argsTab);


            MoveDirection[] directions = OptionsParser.parse(argsTab);

            HashMap<Vector2d, Object> worldMap = new HashMap<Vector2d, Object>();
            IWorldMap map = new RectangularMap(worldMap);
            IWorldMap grassMap = new GrassField(5, worldMap);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(4, 4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions, this);


            Scene scene = new Scene(this.gridPane, 500,500);

            gridPane.setGridLinesVisible(true);
            this.gridPane.setAlignment(Pos.CENTER);
            primaryStage.setScene(scene);
            primaryStage.show();



            engine.run();

    }

    public void modifyGrid(AbstractWorldMap worldMap){

        Vector2d[] boundaries = worldMap.getBoundaries();
        int left = boundaries[0].x;
        int low = boundaries[0].y;
        int right = boundaries[1].x;
        int up = boundaries[1].y;

        Label label = new Label("y/x");
        this.gridPane.add(label, 0,0,1,1);
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
        this.gridPane.getRowConstraints().add(new RowConstraints(this.height));
        GridPane.setHalignment(label, HPos.CENTER);

        //Top Bar
        for (int i = left, j = 1; i <= right; i++, j++){
            Label label1 = new Label(""+i);
            this.gridPane.add(label1, j,0,1,1);

            this.gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
            GridPane.setHalignment(label1, HPos.CENTER);


        }

        //Left Bar
        for (int i = up, j = 1; i >= low; i--, j++){
            Label label1 = new Label(""+i);
            this.gridPane.add(label1,0,j,1,1);

            this.gridPane.getRowConstraints().add(new RowConstraints(this.height));
            GridPane.setHalignment(label1, HPos.CENTER);


        }
        //Animals and grasses
        Set<Vector2d> occupiedPositions = worldMap.getAllPositions();

        for (Vector2d position : occupiedPositions){
            Object object = worldMap.objectAt(position);

            if (object instanceof Animal) {
                Animal specificObject = (Animal) object;
                Label label1 = new Label(specificObject.toString());
                this.gridPane.add(label1, 1 + position.x - left, 1 + up - position.y, 1, 1);
                GridPane.setHalignment(label1, HPos.CENTER);


            }
            if (object instanceof Grass){
                Grass specificObject = (Grass) object;
                Label label1 = new Label(specificObject.toString());
                this.gridPane.add(label1, 1 + position.x - left, 1 + up - position.y, 1, 1);
                GridPane.setHalignment(label1, HPos.CENTER);


            }
        }
    }
    public void getMap(AbstractWorldMap worldMap) {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getChildren().clear();
        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setAlignment(Pos.CENTER);
        modifyGrid(worldMap);
    }


}
