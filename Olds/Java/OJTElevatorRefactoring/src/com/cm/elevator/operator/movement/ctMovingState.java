package com.cm.elevator.operator.movement;

import java.util.Observable;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.ctElevatorMessage;
import com.cm.elevator.operator.thread.ctElevatorMoveThread;
import com.cm.floor.ctFloorMessage;
import com.cm.main.ctElevatorMainFrame;


/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.operator.movement.ctMovingState
 * <li> Created Time : 2017. 12. 4.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public abstract class ctMovingState extends ctMovement {

    protected ctElevatorMoveThread timer;
    protected boolean threadrun = false;
    /**
     * constructor <br>
     * description:
     *
     * @param o
     */
    public ctMovingState ( ctElevatorMainFrame o ) {
        super ( o );
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
                isClickedDestination(message.getFloor (), message.isClicked ());
            }
        }else if(arg instanceof ctFloorMessage) {
            ctFloorMessage message = (ctFloorMessage) arg;

            if(this instanceof ctUpStateMovement && message.getType () == ctFloorMessage.UP) {
                isClickedForwardDirection(message.getFloor (), message.isClickedUp ());
            }else if(this instanceof ctUpStateMovement && message.getType () == ctFloorMessage.DOWN) {
                isClickedReverseDirection(message.getFloor (), message.isClickedDown ());
            }else if(this instanceof ctDownStateMovement && message.getType () == ctFloorMessage.DOWN) {
                isClickedForwardDirection(message.getFloor (), message.isClickedDown ());
            }else if(this instanceof ctDownStateMovement && message.getType () == ctFloorMessage.UP) {
                isClickedReverseDirection(message.getFloor (), message.isClickedUp ());
            }
        }
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
        //엘리베이터의 open의 상태를 현재 open으로 설정.
        _elevator_model.setOpen(open);

        if(open) {
            latestArrival = location;
        }

        if(location != end &&open)
        {
            setNextArrival();
            timer.setNextArrivalTime(convertingTime(nextArrival));
        }

        //만약 끝이라면 notifyFinishOberser
        if(location == end && !open) //끝의 구분은 location이 top인 것과 문이 막 닫힌 것.
        {
            System.out.println(this + " :   상황 종료를 알림.");
            notifyFinish();
        }
    }



    public void isClickedForwardDirection(int floor, boolean isClicked) {

        //새로 들어온 목적지를 우선 배열에서 처리한다.
        if(this instanceof ctUpStateMovement) {
            wantToUp[floor] = isClicked;
        }else if(this instanceof ctDownStateMovement) {
            wantToDown[floor] = isClicked;
        }

        if(isClicked)
        {

            if(locationCompare(floor))  //우린 지나간 자리에서의 부름은 깔끔하게 무시한다.
            {
                //탑과 Arrival의 변화를 살펴본다.
                setEnd();

                if(setNextArrival()) //만약 더 가까운 곳에 종착지가 생겼다면.
                {
                    timer.setNextArrivalTime(convertingTime(floor));    //timer를 업데이트.
                }
            }

        }

        else if(!isClicked)
        {

            //취소는 일단 기본적으로 다 허용.
            //nextArrival만 아니라면.
            if(locationCompare(floor))
            {
                if(floor == end && floor != nextArrival) //top이지만 nextArrival은 아닌 경우
                {
                    setEnd();    //간단하게 bottom만 바꾸면 종료
                }

                else if(floor != end && floor == nextArrival) //top은 아니나 nextArrival.
                {
                    if(setNextArrival())    //우선 다음 도착지를 바꾸고
                    {
                        timer.setNextArrivalTime(convertingTime(nextArrival));  //다음 도착지까지 갈 시간을 converting해서 전달해줘야한다.
                    }
                }
            }
        }
    }

    public void isClickedReverseDirection(int floor, boolean isClicked) {

        //새로 들어온 목적지를 want to Up 배열에 넣는다.
        if(this instanceof ctUpStateMovement) {
            wantToDown[floor] = isClicked;
        }else if(this instanceof ctDownStateMovement) {
            wantToUp[floor] = isClicked;
        }
        if(isClicked)   //새로 선택된 경우.
        {

            if(locationCompare(floor))    //각자 알아서.
            {
                setEnd();    //새로 더 위에서 내려가고 싶어하는 입력이 들어온 경우를 처리
                if(setNextArrival())
                {
                    timer.setNextArrivalTime(convertingTime(nextArrival));
                }
            }
        }
        else if(!isClicked)
        {

            if(floor == end && floor != nextArrival) //일단 취소되었는데 .
            {
                setEnd();
            }
        }
    }

    public void isClickedDestination(int destination, boolean isClicked)
    {
        wantToGo[destination] = isClicked;

        if(isClicked)   //새로 눌린 경우
        {
            if(locationCompare(destination))  //당연히 지나온 건 무시
            {
                setEnd();
                if(setNextArrival()) //만약 더 가까운 곳에 종착지가 생겼다면.
                {
                    timer.setNextArrivalTime(convertingTime(destination));  //timer를 업데이트.
                }

            }
        }

        else if(!isClicked)
        {
            //취소에 대해 생각해보자
            if(locationCompare(destination))
            {
                if(destination == end && destination != nextArrival) //bottom이지만 nextArrival은 아닌 경우
                {
                    setEnd();    //간단하게 bottom만 바꾸면 종료
                }

                else if(destination != end && destination == nextArrival) //bottom은 아니나 nextArrival이었던거임
                {
                    if(setNextArrival())    //우선 다음 도착지를 바꾸고
                    {
                        timer.setNextArrivalTime(convertingTime(nextArrival));  //다음 도착지까지 갈 시간을 converting해서 전달해줘야한다.
                    }
                }
            }
        }
    }

    public abstract void locationUpdate();
    public abstract boolean setEnd ();
    public abstract boolean setNextArrival ();
    public abstract boolean locationCompare(int floor);
    public abstract boolean[] forwardDirection();
    public abstract boolean[] reverseDirection();


    //3. 스레드에 진행해야하는 시간을 알리기 위한 시간 치환 메소드.
    protected int convertingTime(int nextFloor)
    {
        int convertedTime = 0;
        if(this instanceof ctUpStateMovement) {
            convertedTime = (nextFloor-latestArrival)*ctSimulatorConstant.MoveSpeed
                    + timer.getDepartureTime();         //Down에선 반대로 되어야함!!
        }else if(this instanceof ctDownStateMovement) {
            convertedTime = (latestArrival-nextFloor)*ctSimulatorConstant.MoveSpeed
                    + timer.getDepartureTime();         //Down에선 반대로 되어야함!!
        }
        //System.out.println(this + " : 업 스테이트가 스레드에 도착시간으로 설정한 시간 : " + convertedTime);
        return convertedTime;
    }

}
