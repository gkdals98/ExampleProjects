package com.cm.elevator.operator.thread;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.operator.movement.ctMovingState;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.thread.ctElevatorMoveThread
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctElevatorMoveThread extends Thread {

    ctMovingState movement;
    boolean operate;

    //타이머카운트를 증가시킨다.
    int timerCounter;
    //이게 가장 마지막에 출발했던 시간을 정하고
    int latestDepartureTime;
    //다음에 도착해야 하는 시간.
    int nextArrivalTime;
    /**
     * default constructor
     */
    public ctElevatorMoveThread (ctMovingState movement) {
        // implementation
        this.movement = movement;
        operate = true;
        timerCounter = 0;
        latestDepartureTime = 0;
        nextArrivalTime = 0;
    }


    @Override
    public void run()
    {
        while(operate)
        {
            try
            {
                if((timerCounter - latestDepartureTime)%ctSimulatorConstant.MoveSpeed == 0
                        && (timerCounter - latestDepartureTime) != 0)
                {
                    movement.locationUpdate();
                    System.out.println(this + " :   다음 도착시간" + nextArrivalTime);

                    if(timerCounter == nextArrivalTime)
                    {
                        System.out.println(this + " :   열렸다 닫힘. 현재 시각 - " + timerCounter);
                        setDepartureTime(timerCounter);
                        movement.openCloseUpdate(true);
                        Thread.sleep(ctSimulatorConstant.OpenCloseSpeed);
                        movement.openCloseUpdate(false);
                    }

                }
                Thread.sleep(500);
                timerCounter = timerCounter+500;
                System.out.println(timerCounter);
                //System.out.println(this + " : 스레드상의 latestDepartureTime : " + latestDepartureTime);

                System.out.println (this + " :  다음 도착시간을 일단 보자 - " + nextArrivalTime );
            }
            catch (InterruptedException e) {    //ignore
            }
        }
        System.out.println("타이머 종료");
    }




    public void setDepartureTime(int latestDepartureTime)
    {
        this.latestDepartureTime = latestDepartureTime;
    }

    //Movement와의 통신을 위한 메소드

    public void finish()    //동작을 멈추고 다른 Movement로 넘어갈 시 호출
    {
        operate = false;
    }

    public int getDepartureTime() //출발 시간을 알아야 다음 시간도 받아올 수 있다.
    {
        //System.out.println("\n마지막 도착시간 - "+latestDepartureTime+"\n");
        return latestDepartureTime;
    }

    public void setNextArrivalTime(int nextArrivalTime) //ArrivalTime을 업데이트 한다.
    {
        this.nextArrivalTime = nextArrivalTime;
    }
}
