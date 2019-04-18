package com.cm.elevator.operator.movement;

import java.util.Observable;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.ctElevatorMessage;
import com.cm.elevator.operator.thread.ctStayThread;
import com.cm.floor.ctFloorMessage;
import com.cm.main.ctElevatorMainFrame;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.movement.stStopStateMovement
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctStopStateMovement  extends ctMovement{


    ctStayThread _stay_Thread;
    boolean _open_Semaphore;              //문이 열려있어 움직일 수 없음을 알림
    /**
     * constructor <br>
     * description:
     *
     * @param o
     */
    public ctStopStateMovement ( ctElevatorMainFrame o ) {
        super ( o );
        _open_Semaphore = false;
    }

    /**
     * overriding method <br>
     * description:
     *
     * @see elevator.operator.movement.ctMovement#eachInit()
     */
    @Override
    public void eachInit ()
    {
        _floor_Models[location].setClickedUp ( false );
        _floor_Models[location].setClickedDown ( false );

        if(wantToGo[location]) {
            _elevator_model.setFloorSelect ( location );
        }
    }

    /**
     * overriding method <br>
     * description:
     *
     * @param o
     * @param arg
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update ( Observable o, Object arg ) {
        if(arg instanceof ctElevatorMessage) {
            ctElevatorMessage message = (ctElevatorMessage) arg;
            if(message.getType () == ctElevatorMessage.DESTINATION) {
                wantToGo[message.getFloor ()] = message.isClicked ();
                updateIsClickedSomeWhere(message.getFloor (), message.isClicked ());
            }
        }else if(arg instanceof ctFloorMessage) {
            ctFloorMessage message = (ctFloorMessage) arg;
            if(message.getType () == ctFloorMessage.UP) {
                wantToUp[message.getFloor ()] = message.isClickedUp ();
                updateIsClickedSomeWhere(message.getFloor (), message.isClickedUp ());
            }
            else if(message.getType () == ctFloorMessage.DOWN) {
                wantToDown[message.getFloor ()] = message.isClickedDown ();
                updateIsClickedSomeWhere(message.getFloor (), message.isClickedDown ());
            }
        }
    }

    public void updateIsClickedSomeWhere(int floor, boolean isClicked)
    {
        if(isClicked && !_open_Semaphore) {
            nextDirection = findNextDirection();
            if(nextDirection != ctSimulatorConstant.STOP)    //어떤 경우에도 다음 방향이 Stop이 아니면 전환.
            {
                notifyFinish();
            }
            else if(nextDirection == ctSimulatorConstant.STOP)
            {
                //System.out.println(this + " : 제 자리에서 멈춰있는 동작이 선택되었다고 알림.");
                openInSamePlace(true);
            }
        }
    }

    private void openInSamePlace(boolean open)
    {
        _open_Semaphore = true;
        _elevator_model.setOpen(open);

        _stay_Thread = new ctStayThread(this);
        _stay_Thread.start();
    }

    /**
     * overriding method <br>
     * description:
     *
     * @param open
     * @see elevator.operator.movement.ctMovement#openCloseUpdate(boolean)
     */
    @Override
    public void openCloseUpdate ( boolean open ) {
        wantToUp[location] = false;
        wantToDown[location] = false;
        _open_Semaphore = false;

        if(wantToGo[location]) {
            _elevator_model.setFloorSelect(location);
        }
        if(_floor_Models[location].isClickedUp())
        {
            _floor_Models[location].setClickedUp(false);
        }
        if(_floor_Models[location].isClickedDown())
        {
            _floor_Models[location].setClickedDown(false);
        }

        _elevator_model.setOpen(false);
        nextDirection = findNextDirection();

        if(nextDirection != ctSimulatorConstant.STOP)
        {
            notifyFinish();
        }
    }

    /**
     * overriding method <br>
     * description:
     *
     * @return
     * @see elevator.operator.movement.ctMovement#setNextArrival()
     */
    private int findNextDirection()
    {
        int direction = ctSimulatorConstant.STOP;

        //System.out.println(this + " : 멈추고 다음 목적지 탐색중");
        for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
        {
            if(wantToUp[floor] || wantToDown[floor] || wantToGo[floor])
            {
                if(floor > location)
                {

                    // System.out.println(this + " : 스탑 스테이트에서 올라갈 목적지로 " + floor + "를 찾음 " + "지금은" + location);

                    direction = ctSimulatorConstant.UP;
                    break;
                }
                else if(floor == location)
                {
                    break;
                }
                else if(floor < location)
                {
                    System.out.println(this + " :    스탑 스테이트에서 내려갈 목적지로 " + floor + "를 찾음" + " 지금은" + location);

                    direction = ctSimulatorConstant.DOWN;
                    break;
                }
            }
        }

        return direction;
    }

    /**
     * overriding method <br>
     * description:
     *
     * @see elevator.operator.movement.ctMovement#notifyFinish()
     */
    @Override
    public void notifyFinish () {
        setChanged();
        notifyObservers();
    }

}
