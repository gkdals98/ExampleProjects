package com.cm.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cm.constants.ctSimulatorConstant;
import com.cm.elevator.ctElevatorController;
import com.cm.elevator.ctElevatorModel;
import com.cm.elevator.ctElevatorView;
import com.cm.elevator.operator.movement.ctDownStateMovement;
import com.cm.elevator.operator.movement.ctMovement;
import com.cm.elevator.operator.movement.ctStopStateMovement;
import com.cm.elevator.operator.movement.ctUpStateMovement;
import com.cm.floor.ctFloorController;
import com.cm.floor.ctFloorModel;
import com.cm.floor.ctFloorView;


/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : main.ctElevatorMainFrame
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  : Hold the Mainframe of ElevatorProject
 * </ul>
 * </p>
 */
public class ctElevatorMainFrame extends JFrame implements Observer{


    private ctElevatorModel _elevator_model;
    private ctElevatorController elevatorController;
    private ctElevatorView elevatorView;

    private ctFloorModel[] floorModels;

    /**
     * default constructor
     */
    public ctElevatorMainFrame () {
        super(ctSimulatorConstant.ProjectName);
        setSize(new Dimension(900, 600));
        setLocation(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        defaultSetting();

        //최종적으로 메인 프레임을 활성화
        setVisible(true);
    }


    public void defaultSetting()
    {
        JPanel floorHolder;

        //엘리베이터와 엘리베이터 뷰의 설정.
        ctMovement defaultMove = new ctStopStateMovement(this);
        _elevator_model = new ctElevatorModel(defaultMove);       //우선 시작시에 기본 Movement를 넘겨줌.
        elevatorController = new ctElevatorController(_elevator_model);
        elevatorView = new ctElevatorView(elevatorController);

        _elevator_model.addObserver ( elevatorView );

        floorHolder = new JPanel(new GridLayout(1,ctSimulatorConstant.FloorLength));
        floorHolder.setPreferredSize(new Dimension(800,150));
        floorHolder.setBackground(new Color(140, 140, 170));

        floorModels = new ctFloorModel[ctSimulatorConstant.FloorLength];

        for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
        {
            floorModels[floor] = new ctFloorModel(floor);

            ctFloorController floorController = new ctFloorController(floorModels[floor]);
            ctFloorView floorView = new ctFloorView(floorController, floorController.floorConvertor(floor), floor);

            floorModels[floor].addObserver(floorView);
            floorModels[floor].addObserver(defaultMove);

            floorHolder.add(floorView);
        }

        _elevator_model.addObserver(defaultMove);
        defaultMove.setElevatorReady();
        _elevator_model.setCurrentMove(defaultMove);

        add(elevatorView, BorderLayout.CENTER);
        add(floorHolder, BorderLayout.SOUTH);
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
        if(o instanceof ctMovement)
        {
            System.out.println("\n" + this + " :    방향 전환을 알림");
            //우선 기존 스테이트의 옵저버 상태를 전부 제거한다.
            ctMovement m = _elevator_model.getCurrentMove();

            int nextMove = m.getNextDirection ();

            _elevator_model.deleteObserver(m);
            for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
            {
                floorModels[floor].deleteObserver ( m );
            }
            m.deleteObserver ( this );

            //다음으로 상황에 맞는 새 MoveState를 생성
            if(nextMove == ctSimulatorConstant.UP)
            {
                m = new ctUpStateMovement(this);
            }

            else if(nextMove == ctSimulatorConstant.DOWN)
            {
                m = new ctDownStateMovement(this);
            }

            else if(nextMove == ctSimulatorConstant.STOP)
            {
                m = new ctStopStateMovement(this);
            }

            //마지막으로 새로운 MoveState를 등록한다.
            _elevator_model.addObserver(m);

            for(int floor = 0; floor < ctSimulatorConstant.FloorLength; floor++)
            {
                floorModels[floor].addObserver (m);
            }

            _elevator_model.setCurrentMove(m);

            m.setElevatorReady();   //일종의 퍼사드 패턴.
        }
    }


    //현재 있는 엘리베이터를 반환한다.
    public ctElevatorModel getElevator() {
        return _elevator_model;
    }

    public ctFloorModel[] getFloorModels() {
        return floorModels;
    }
}

