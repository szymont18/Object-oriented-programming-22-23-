package agh.ics.oop;

public interface IPositionChangeObserver {
    /*
    Change position of Animal or Grass from oldPosition to newPosition
     */
    public void positionChangeObserver(Vector2d oldPosition, Vector2d newPosition);

}
