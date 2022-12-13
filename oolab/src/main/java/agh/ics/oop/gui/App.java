package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.HashMap;
import java.util.Set;
import javafx.scene.control.TextField;


public class App extends Application {
    public GridPane gridPane = new GridPane();
    private final int width = 75;
    private final int height = 75;

    public void start(Stage primaryStage){
        try {
            TextField textField = new TextField();
            Button startButton = getStartButton(textField);
            VBox vBox = new VBox(20, this.gridPane, textField, startButton);
            vBox.setAlignment(Pos.CENTER);
            Scene scene = new Scene(vBox, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }


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
            IMapElement imp = (IMapElement) worldMap.objectAt(position);
            GuiElementBox vBox = new GuiElementBox(imp);
            Label label2 = new Label();
            GridPane.setHalignment(label2, HPos.CENTER);

            this.gridPane.add(vBox.vBox, 1 + position.x - left, 1 + up - position.y, 1, 1);
        }
    }
    public void getMap(IWorldMap worldMap) {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getChildren().clear();
        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setAlignment(Pos.CENTER);
        modifyGrid((AbstractWorldMap) worldMap);
    }

    public Button getStartButton(TextField textField) {
        Button startButton = new Button("Start");
        startButton.setMaxSize(100, 200);
        startButton.setOnAction((action) -> {
            String text = textField.getText();

            //Creating Map
            MoveDirection[] directions = OptionsParser.parse(text.split(" "));
            HashMap<Vector2d, Object> worldMap = new HashMap<Vector2d, Object>();
            IWorldMap map = new RectangularMap(worldMap);
            IWorldMap grassMap = new GrassField(5, worldMap);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(4, 4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions, this);

            Thread engineThread = new Thread(engine); //:: run ???
            engineThread.start();
        });
        return startButton;
    }


}
