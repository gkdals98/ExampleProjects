package com.cm.elevator.operator.thread;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.operator.movement.ctMovement;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.thread.ctStayThread
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctStayThread extends Thread{

    private int timerCounter;
    private boolean operate;
    private ctMovement stop;

    public ctStayThread(ctMovement stop) {
        timerCounter = 0;
        operate = true;
        this.stop = stop;
    }

    public void run()
    {
        while(operate)
        {

            try
            {

                if(timerCounter == ctSimulatorConstant.OpenCloseSpeed)
                {
                    operate = false;
                    stop.openCloseUpdate(false);
                }

                Thread.sleep(100);
                timerCounter = timerCounter + 100;
            }

            catch (InterruptedException e) {
            }
        }
        //System.out.println("스레드 종료");
    }
}
