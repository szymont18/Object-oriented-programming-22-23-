package agh.ics.oop;

import agh.ics.oop.gui.App;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;

    private List<Animal> animals;

    private MapVisualizer visualizer;

    private App app;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsStart){
        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<Animal>();
        for (Vector2d start: animalsStart){
            Animal animal = new Animal(map, start);
            animals.add(animal);
            map.place(animal);
        }
    }
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsStart, App app){
        this.moves = moves;
        this.map = map;
        this.app = app;
        this.animals = new ArrayList<Animal>();
        for (Vector2d start: animalsStart){
            Animal animal = new Animal(map, start);
            animals.add(animal);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        int i = 0;
//        this.app.getMap((AbstractWorldMap) this.map);
        SwingVisualizer swing = new SwingVisualizer();
        for (MoveDirection move : this.moves){
            animals.get(i).move(move);
//            System.out.println(this.map.toString());
//            swing.changeLabel(map.toString());
//            this.app.getMap((AbstractWorldMap) this.map);
            i = (i + 1)%animals.size();
        }


    }
}
