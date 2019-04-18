package com.cm.elevator;

import java.util.Observable;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.operator.movement.ctDownStateMovement;
import com.cm.elevator.operator.movement.ctMovement;
import com.cm.elevator.operator.movement.ctStopStateMovement;
import com.cm.elevator.operator.movement.ctUpStateMovement;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.ctElevatorModel
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  : Model of Elevator, hold some data about elevator states
 * </ul>
 * </p>
 */
public class ctElevatorModel extends Observable{

    /**
     * default constructor
     */
    ctElevatorModel () {
        // implementation
    }


    private ctMovement currentMove;
    private boolean[] clickedFloorList;
    private int location;
    private boolean open;


    public ctElevatorModel(ctMovement movement)
    {
        currentMove = movement;
        clickedFloorList = new boolean[ctSimulatorConstant.FloorLength];

        for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
        {
            clickedFloorList[floor] = false;
        }

        location = ctSimulatorConstant.UnderGround;
        open = false;
    }

    public ctMovement getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(ctMovement currentMove) {
        this.currentMove = currentMove;
        ctElevatorMessage message = new ctElevatorMessage();
        if(currentMove instanceof ctUpStateMovement) {
            message = new ctElevatorMessage(ctElevatorMessage.UPDOWN, ctSimulatorConstant.UP);
        }
        else if(currentMove instanceof ctDownStateMovement) {
            message = new ctElevatorMessage(ctElevatorMessage.UPDOWN, ctSimulatorConstant.DOWN);
        }
        else if(currentMove instanceof ctStopStateMovement) {
            message = new ctElevatorMessage(ctElevatorMessage.UPDOWN, ctSimulatorConstant.STOP);
        }
        setChanged();
        notifyObservers(message);
    }





    public boolean[] getClickedFloorList() {
        return clickedFloorList;
    }

    public void setFloorSelect(int floor) {
        if(clickedFloorList[floor])
        {
            clickedFloorList[floor] = false;
        }
        else if(!clickedFloorList[floor])
        {
            clickedFloorList[floor] = true;
        }

        ctElevatorMessage message = new ctElevatorMessage(ctElevatorMessage.DESTINATION, floor, clickedFloorList[floor]);

        setChanged();
        notifyObservers(message);
    }




    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        System.out.println(this + " : 엘리베이터 모델상 위치값이 다음과 같음 : " + location);
        this.location = location;

        ctElevatorMessage message = new ctElevatorMessage(ctElevatorMessage.LOCATION, location);
        setChanged();
        notifyObservers(message);
    }




    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        System.out.println(this + " :   엘리베이터 모델이 문의 상태를 알림 - " + open);
        this.open = open;

        ctElevatorMessage message = new ctElevatorMessage(ctElevatorMessage.OPENCLOSE, open);
        setChanged();
        notifyObservers(message);
    }
}
