package agh.ics.oop;

public enum MapDirection{
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public String toString(){
        String res = new String();
        switch (this){
            case EAST -> res = "E";
            case WEST -> res = "W";
            case NORTH -> res = "N";
            case SOUTH -> res = "S";
        }
        return res;
    }

    public MapDirection next(){
        MapDirection res = null;
        switch(this){
            case EAST -> {res = MapDirection.SOUTH;}
            case WEST ->  {res = MapDirection.NORTH;}
            case NORTH -> {res = MapDirection.EAST;}
            case SOUTH -> {res = MapDirection.WEST;}
        }
        return res;
    }

    public MapDirection previous(){
        MapDirection res = null;
        switch (this){
            case SOUTH -> {res = MapDirection.EAST;}
            case NORTH -> {res = MapDirection.WEST;}
            case WEST -> {res = MapDirection.SOUTH;}
            case EAST -> {res = MapDirection.NORTH;}
        }
        return res;
    }

    public Vector2d toUnitVector(){
        Vector2d res = new Vector2d(0,0);
        switch (this){
            case EAST -> {res = res.add(new Vector2d(1, 0));}
            case WEST -> {res = res.add(new Vector2d(-1, 0));}
            case NORTH -> {res = res.add(new Vector2d(0, 1));}
            case SOUTH -> {res = res.add(new Vector2d(0, -1));}
        }
        return res;
    }

    public static MapDirection fromString(String s){
        return switch (s){
            case "N" -> MapDirection.NORTH;
            case "S" -> MapDirection.SOUTH;
            case "E" -> MapDirection.EAST;
            case "W" -> MapDirection.WEST;
            default -> throw new IllegalArgumentException();
        };
    }


}
