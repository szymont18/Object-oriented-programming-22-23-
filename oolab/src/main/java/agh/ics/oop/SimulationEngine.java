package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine, Runnable {
    private MoveDirection[] moves;
    private IWorldMap map;

    private List<Animal> animals;

    private MapVisualizer visualizer;

    private App guiObserver;

    private final int moveDelay = 1000;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsStart) {
        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<Animal>();
        for (Vector2d start : animalsStart) {
            Animal animal = new Animal(map, start);
            animals.add(animal);
            map.place(animal);
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsStart, App app) {
        this.moves = moves;
        this.map = map;
        this.guiObserver = app;
        this.animals = new ArrayList<Animal>();
        for (Vector2d start : animalsStart) {
            Animal animal = new Animal(map, start);
            animals.add(animal);
            map.place(animal);
        }
    }


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, ArrayList<Vector2d> animalsStart,
                            ArrayList<MapDirection> initial, App app) {
        this.moves = moves;
        this.map = map;
        this.guiObserver = app;
        this.animals = new ArrayList<Animal>();
        for (int i = 0; i < animalsStart.size(); i++) {
            Animal animal = new Animal(map, animalsStart.get(i), initial.get(i));
            animals.add(animal);
            map.place(animal);
        }
    }


    @Override
    public void run() {
        Platform.runLater(() -> {
            this.guiObserver.getMap(this.map);
        });

        try {
            Thread.sleep(this.moveDelay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        int i = 0;
        for (MoveDirection move : this.moves) {
            animals.get(i).move(move);
            Platform.runLater(() -> {
                this.guiObserver.getMap(this.map);
            });
            try {
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            i = (i + 1)%animals.size();
        }
    }
}
