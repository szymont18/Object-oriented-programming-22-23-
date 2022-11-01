package agh.ics.oop;
import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        ArrayList<MoveDirection> parsedMoves = new ArrayList<MoveDirection>();

        for (int i = 0; i < args.length; i++){
            switch(args[i]){
                case "f", "forward" ->{
                    parsedMoves.add(MoveDirection.FORWARD);
                }
                case "b", "backward"->{
                    parsedMoves.add(MoveDirection.BACKWARD);
                }
                case "r", "right" ->{
                    parsedMoves.add(MoveDirection.RIGHT);
                }
                case "l", "left" -> {
                    parsedMoves.add(MoveDirection.LEFT);
                }
                default -> System.out.print("");
            }
        }
        MoveDirection[] movesTable = new MoveDirection[parsedMoves.size()];
        parsedMoves.toArray(movesTable);
        return movesTable;
    }

}
