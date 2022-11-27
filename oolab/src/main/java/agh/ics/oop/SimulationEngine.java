package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;

    private List<Animal> animals;

    private MapVisualizer visualizer;

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

    @Override
    public void run() {
        int i = 0;

        SwingVisualizer swing = new SwingVisualizer();
        for (MoveDirection move : this.moves){
            animals.get(i).move(move);
//            System.out.println(this.map.toString());
//            swing.changeLabel(map.toString());
            i = (i + 1)%animals.size();
        }


    }
}
