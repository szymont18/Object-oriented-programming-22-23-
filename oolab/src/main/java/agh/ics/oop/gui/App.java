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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.scene.control.TextField;


public class App extends Application {
    public GridPane gridPane = new GridPane();
    private final int width = 75;
    private final int height = 75;

    private ArrayList<Vector2d> initialPos = new ArrayList<>();
    private ArrayList<MapDirection> initialDir = new ArrayList<>();


//    @Override public void init() throws Exception{
//        super.init();
//    }


    public void start(Stage primaryStage){
        try {
            Label label1 = new Label("Wprowadz ruchy zwierzat");
            TextField moveTextField = new TextField();
            VBox movesBox = new VBox(label1, moveTextField);


            Button startButton = getStartButton(moveTextField);


            Label label2 = new Label("Dodaj poczatkowe pozycje zwierzatek");
            TextField animalTextField = new TextField();
            TextField directionTextField = new TextField();
            Button animalButton = getAnimalButton(animalTextField, directionTextField);
            HBox animalBox = new HBox(20, animalTextField, directionTextField);
            animalBox.setAlignment(Pos.CENTER);

            VBox animalVbox = new VBox(label2, animalBox, animalButton);
            animalVbox.setAlignment(Pos.CENTER);

            HBox movesAndPosition = new HBox(50, animalVbox, movesBox);
            movesAndPosition.setAlignment(Pos.CENTER);



            VBox mainBox = new VBox(20, movesAndPosition, startButton, this.gridPane);
            mainBox.setAlignment(Pos.CENTER);
            Scene scene = new Scene(mainBox, 800, 800);
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
        startButton.setMaxSize(200, 250);
        startButton.setOnAction((action) -> {
            String text = textField.getText();

            //Creating Map
            MoveDirection[] directions = OptionsParser.parse(text.split(" "));
            HashMap<Vector2d, Object> worldMap = new HashMap<Vector2d, Object>();
            IWorldMap map = new RectangularMap(worldMap);
            IWorldMap grassMap = new GrassField(5, worldMap);
            SimulationEngine engine = new SimulationEngine(directions, map, this.initialPos, this.initialDir, this);

            Thread engineThread = new Thread(engine);
            engineThread.start();
        });
        return startButton;
    }

    public Button getAnimalButton(TextField pos, TextField dir){
        Button animalButton = new Button("Dodaj zwierzaka");
        animalButton.setMaxSize(200, 200);
        animalButton.setOnAction((action) ->{
            String[] sPos = pos.getText().split(" ");
            String sDir = dir.getText();

            int x = Integer.parseInt(sPos[0]);
            int y = Integer.parseInt(sPos[1]);
            this.initialPos.add(new Vector2d(x,y));
            this.initialDir.add(MapDirection.fromString(sDir));
            pos.clear();
            dir.clear();
        });
        return animalButton;
    }
}
