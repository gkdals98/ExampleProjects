package com.cm.elevator.operator.movement;

import java.util.Observable;
import java.util.Observer;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.ctElevatorModel;
import com.cm.floor.ctFloorModel;
import com.cm.main.ctElevatorMainFrame;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.movement.ctMovement
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  : abstract class for Operating Elevator Move.
 * </ul>
 * </p>
 */
public abstract class ctMovement extends Observable implements Observer{

    ctElevatorModel _elevator_model;
    ctElevatorMainFrame o;

    boolean[] wantToUp;        //올라가고 싶어하는 사람이 있는 곳.
    boolean[] wantToDown;   //내려가고 싶어하는 사람이 있는 곳.
    boolean[] wantToGo; //목적지로 지정된 곳
    ctFloorModel[] _floor_Models;

    int location;           //지금 자신의 위치.
    int end;
    int nextArrival;
    int latestArrival;

    int nextDirection;



    public ctMovement(ctElevatorMainFrame o)
    {
        this.o = o;
    }


    public void setElevatorReady()
    {
        _elevator_model = o.getElevator();
        addObserver(o);

        wantToGo = _elevator_model.getClickedFloorList();
        location = _elevator_model.getLocation();

        wantToUp = new boolean[ctSimulatorConstant.FloorLength];
        wantToDown = new boolean[ctSimulatorConstant.FloorLength];
        wantToGo = _elevator_model.getClickedFloorList();

        _floor_Models = o.getFloorModels();

        for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
        {
            wantToUp[floor] = _floor_Models[floor].isClickedUp();
            wantToDown[floor] = _floor_Models[floor].isClickedDown();
        }

        latestArrival = location;   //시작시 가장 마지막 위치는 원래 있던 곳.

        eachInit();
    }

    public abstract void eachInit();

    //이제부터 써나가는 스레드와의 통신 메소드들.
    public abstract void openCloseUpdate(boolean open);

    public abstract void notifyFinish();

    public int getNextDirection () {
        return nextDirection;
    }

    public void setNextDirection ( int nextDirection ) {
        this.nextDirection = nextDirection;
    }

}
