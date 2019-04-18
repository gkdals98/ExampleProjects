package com.cm.floor;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : floor.ctFloorMessage
 * <li> Created Time : 2017. 12. 4.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctFloorMessage {
    public static final int UP = 0;
    public static final int DOWN = 1;

    int type;
    int floor;
    boolean isClickedUp;
    boolean isClickedDown;

    public ctFloorMessage(int type, int floor, boolean isClicked) {
        this.type = type;
        this.floor = floor;
        if(type == UP) {
            this.isClickedUp = isClicked;
        }else if(type == DOWN) {
            this.isClickedDown = isClicked;
        }
    }

    public int getType () {
        return type;
    }

    public void setType ( int type ) {
        this.type = type;
    }

    public int getFloor () {
        return floor;
    }

    public void setFloor ( int floor ) {
        this.floor = floor;
    }

    public boolean isClickedUp () {
        return isClickedUp;
    }

    public void setClickedUp ( boolean isClickedUp ) {
        this.isClickedUp = isClickedUp;
    }

    public boolean isClickedDown () {
        return isClickedDown;
    }

    public void setClickedDown ( boolean isClickedDown ) {
        this.isClickedDown = isClickedDown;
    }



}
