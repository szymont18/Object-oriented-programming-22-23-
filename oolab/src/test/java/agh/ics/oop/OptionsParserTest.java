package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        String[] test1 = {"f", "forward", "b","backward", "r", "right", "l", "left"};
//        String[] test2 = {"f", "ala", "ma", "kota", "b"};
        String[] test3 = {"b"};
//        String[] test4 = {"asf","asf","grdb","dsg"};

        MoveDirection[] answer1 = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
                                    MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        MoveDirection[] answer2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD};
        MoveDirection[] answer3 = {MoveDirection.BACKWARD};
        MoveDirection[] answer4 = {};

        assertArrayEquals(answer1, OptionsParser.parse(test1));
//        assertArrayEquals(answer2, OptionsParser.parse(test2));
        assertArrayEquals(answer3, OptionsParser.parse(test3));
//        assertArrayEquals(answer4, OptionsParser.parse(test4));
    }
}