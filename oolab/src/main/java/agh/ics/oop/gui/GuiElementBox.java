package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {

    VBox vBox = new VBox();
    Label label;

    public GuiElementBox(IMapElement element) {
        try {
            Image image = new Image(new FileInputStream(element.getImagePath()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            if (element instanceof Animal) {
                this.label = new Label(element.getPosition().toString());
            } else
                this.label = new Label("Grass");
            this.vBox.getChildren().addAll(imageView, label);
            this.vBox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found.");
        }
    }


}


