package com.cm.elevator.operator.movement;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.operator.thread.ctElevatorMoveThread;
import com.cm.main.ctElevatorMainFrame;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.movement.ctDownStateMovement
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctDownStateMovement  extends ctMovingState{
    /**
     * constructor <br>
     * description:
     *
     * @param o
     */
    public ctDownStateMovement ( ctElevatorMainFrame o ) {
        super ( o );
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
        end = ctSimulatorConstant.FloorLength-1;
        nextArrival = -1;

        if(setEnd()&&setNextArrival()) {

            threadrun = true;
            timer = new ctElevatorMoveThread(this);
            timer.setNextArrivalTime(convertingTime(nextArrival));

            System.out.println(this + " :   다운스테이트가 엘리베이터 하강 준비 완료를 알림.");

            timer.start();
        }else {
            nextDirection = ctSimulatorConstant.STOP;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * overriding method <br>
     * description:
     *
     * @see elevator.operator.movement.ctMovement#locationUpdate()
     */
    public void locationUpdate () {
        location--;
        _elevator_model.setLocation(location);

        System.out.println(this + " :   ▼▼▼▼▼--------엘리베이터 하강 중 -------▼▼▼▼▼ " + location);

        if(wantToGo[location])
        {
            _elevator_model.setFloorSelect(location);
        }
        if(wantToDown[location])
        {
            _floor_Models[location].setClickedDown(false);
        }

        if(location ==  end && wantToUp[location])
        {
            _floor_Models[location].setClickedUp(false);
        }
    }


    /**
     * overriding method <br>
     * description:
     *
     * @see elevator.operator.movement.ctMovement#notifyFinish()
     */
    @Override
    public void notifyFinish () {
        boolean moveStateCheck = false; //방향이 바뀌었나를 확인하기 위한 채커

        //여기서 우선 향후 방향을 정해보자.
        for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
        {
            if(wantToUp[floor] || wantToDown[floor] || wantToGo[floor])
            {
                //System.out.println(this + " : " + floor + "층에서 오류 감지됨.");
                if(threadrun) {
                    timer.finish(); //스레드의 종료.
                }
                nextDirection = ctSimulatorConstant.UP;
                setChanged();
                notifyObservers();

                moveStateCheck = true;
                break;
            }

        }

        if(moveStateCheck == false) {   //만약 더 갈 곳이 없다면.
            if(threadrun) {
                timer.finish(); //스레드의 종료.
            }
            nextDirection = ctSimulatorConstant.STOP;
            setChanged(); //마지막은 옵저버에게 다음 상태를 넘겨주는 것.
            notifyObservers();
        }
    }


    /**
     * overriding method <br>
     * description:
     *
     * @return
     * @see elevator.operator.movement.ctMovement#setEnd()
     */
    @Override
    public boolean setEnd () {
        boolean isChanged = false;
        if(wantToUp[end] || wantToDown[end] || wantToGo[end]) //바텀이 새로 생긴 경우.
        {
            for(int floor = 0; floor < end; floor++)
            {
                if((wantToUp[floor] || wantToDown[floor] || wantToGo[floor]) && end != floor)
                {
                    System.out.println(this + " :   에서 조건 1로 Bottom을 다음과 같이 바꿨다고 알림. - " + floor);
                    end = floor;
                    isChanged = true;
                    break;
                }
            }
        }

        else if(!wantToUp[end] && !wantToDown[end] && !wantToGo[end])  //기존 바텀이 취소된 경우.
        {
            for(int floor = 0; floor < location; floor++)
            {
                if((wantToUp[floor] || wantToDown[floor] || wantToGo[floor]) && end != floor)
                {
                    System.out.println(this + " :   에서 조건 2 Bottom을 다음과 같이 바꿨다고 알림. - " + floor);
                    end = floor;
                    isChanged = true;
                    break;
                }
            }
        }


        return isChanged;
    }

    /**
     * overriding method <br>
     * description:
     *
     * @return
     * @see elevator.operator.movement.ctMovement#setNextArrival()
     */
    @Override
    public boolean setNextArrival () {
        boolean isChanged = false;
        for(int floor = location -1; floor >= end; floor--)
        {
            if(floor == nextArrival && (wantToDown[floor] || wantToGo[floor]))
            {
                break;
            }
            if((wantToDown[floor] || wantToGo[floor]) && nextArrival != floor)
            {
                System.out.println(this + " :   조건 1로 인해 다운 스테이트가 다음 목적지로 " + floor + " 를 알림");
                nextArrival = floor;
                isChanged = true;       //다음 도착지가 변경된 경우이기도 하다.
                break;
            }

            if(floor == end && nextArrival != floor)
            {   //끝까지 뒤졌는데 설정이 안 됐다는 건 bottm은 올라가고 싶은 리스트에 있다는 뜻이다.
                if(wantToUp[end])
                {   //그래도 확인차 한 번 더 봄.
                    System.out.println(this + " :   조건 2로 인해 다운 스테이트가 다음 목적지로 " + floor + " 를 알림");
                    nextArrival = end;
                    isChanged = true;           //for문을 다 돌았음에도 여기 들어오지 않았다는건 다음 도착지는 top이였다는 것.
                }
            }
        }

        return isChanged;
    }


    /**
     * overriding method <br>
     * description:
     *
     * @return
     * @see elevator.operator.movement.ctMovingState#locationCompare()
     */
    @Override
    public boolean locationCompare (int floor) {
        return floor < location;
    }

    public boolean[] forwardDirection() {
        return wantToDown;
    }

    public boolean[] reverseDirection() {
        return wantToUp;
    }

}
