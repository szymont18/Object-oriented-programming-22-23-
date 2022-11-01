package agh.ics.oop;

import java.util.ArrayList;

public class World {

    public static void main(String[] args) {

        Animal dog = new Animal(new Vector2d(2, 2));
        String[] moves = {"right", "f","a","forward","f","x"};

        MoveDirection[] parsedMoves = OptionsParser.parse(moves);
        for (MoveDirection move : parsedMoves){
            dog.move(move);
        }
        System.out.println(dog);

//        System.out.println("System wystartowal");
//        Direction[] eTab = new Direction[args.length];
//        for (int i = 0; i < args.length; i++){
//            switch (args[i]){
//                case "f" -> eTab[i] = Direction.Forward;
//                case "b" -> eTab[i] = Direction.Backward;
//                case "r" -> eTab[i] = Direction.Right;
//                case "l" -> eTab[i] = Direction.Left;
//                default -> eTab[i] = Direction.NONE;
//            }
//        }
//        run(eTab);
//        System.out.println("System zakonczyl swoje dzialanie");
    }

    static void run(Direction[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case Forward-> System.out.println("Zwierzak idzie do przodu");
                case Backward -> System.out.println("Zwierzak idzie do tylu");
                case Left -> System.out.println("Zwierzak idzie w lewo");
                case Right -> System.out.println("Zwierzak idzie w prawo");
                default -> System.out.print("");
            }
        }
    }
}
