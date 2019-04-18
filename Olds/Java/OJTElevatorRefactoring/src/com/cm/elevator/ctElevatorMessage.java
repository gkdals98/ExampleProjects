package com.cm.elevator;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.ctElevatorMessage
 * <li> Created Time : 2017. 12. 4.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctElevatorMessage {
    public static final int DESTINATION = 0;
    public static final int OPENCLOSE = 1;
    public static final int UPDOWN = 2;
    public static final int LOCATION = 3;


    int type;
    int floor;
    boolean isClicked;
    boolean openClose;
    int upDown;
    int location;

    public ctElevatorMessage() {}

    public ctElevatorMessage(int type, int floor, boolean isClicked) {
        this.type = type;
        this.floor = floor;
        this.isClicked = isClicked;
    }
    public ctElevatorMessage(int type, boolean openCloes) {
        this.type = type;
        this.openClose = openCloes;
    }
    public ctElevatorMessage(int type, int value) {
        if(type == UPDOWN) {
            this.type = type;
            this.upDown = value;
        }else if (type == LOCATION) {
            this.type = type;
            this.location = value;
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

    public boolean isClicked () {
        return isClicked;
    }

    public void setClicked ( boolean isClicked ) {
        this.isClicked = isClicked;
    }

    public boolean isOpenClose () {
        return openClose;
    }

    public void setOpenClose ( boolean openClose ) {
        this.openClose = openClose;
    }

    public int getUpDown () {
        return upDown;
    }

    public void setUpDown ( int upDown ) {
        this.upDown = upDown;
    }

    public int getLocation () {
        return location;
    }


    public void setLocation ( int location ) {
        this.location = location;
    }

}
