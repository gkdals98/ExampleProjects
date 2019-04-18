package com.cm.floor;

import java.util.Observable;


/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : floor.ctFloorModel
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctFloorModel extends Observable{

    private int floor;
    private boolean isClickedUp;
    private boolean isClickedDown;



    public ctFloorModel(int floor) {
        isClickedUp = false;
        isClickedDown = false;

        this.floor = floor;
    }


    public int getFloor()
    {
        return floor;
    }

    public boolean isClickedUp()
    {
        return isClickedUp;
    }

    public void setClickedUp(boolean isClickedUp)
    {
        System.out.println(this + " :   " + floor + "층에서 ▲ 버튼의 상태를 바꿈 + " + isClickedUp);
        this.isClickedUp = isClickedUp;

        ctFloorMessage message = new ctFloorMessage(ctFloorMessage.UP, floor, isClickedUp);
        setChanged();
        notifyObservers(message);
    }

    public boolean isClickedDown()
    {
        return isClickedDown;
    }

    public void setClickedDown(boolean isClickedDown)
    {
        System.out.println(this + " :   " + floor + "층에서 ▼ 버튼의 상태를 바꿈 + " + isClickedDown);
        this.isClickedDown = isClickedDown;

        ctFloorMessage message = new ctFloorMessage(ctFloorMessage.DOWN, floor, isClickedDown);
        setChanged();
        notifyObservers(message);
    }
}
