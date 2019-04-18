package com.cm.floor;

import com.cm.constants.ctSimulatorConstant;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : floor.ctFloorController
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctFloorController {


    private ctFloorModel _floor_Model;


    public ctFloorController(ctFloorModel _floor_Model)
    {
        this._floor_Model = _floor_Model;
    }

    public void isClickedUp()
    {
        if(_floor_Model.isClickedUp())
        {
            _floor_Model.setClickedUp(false);
        }
        else if(!_floor_Model.isClickedUp())
        {
            _floor_Model.setClickedUp(true);
        }
    }

    public void isClickedDown()
    {
        if(_floor_Model.isClickedDown())
        {
            _floor_Model.setClickedDown(false);
        }
        else if(!_floor_Model.isClickedDown())
        {
            _floor_Model.setClickedDown(true);
        }
    }


    //String값의 floor를 얻기 위함.
    public String floorConvertor(int floor)
    {
        String currentFloor = "";

        if(floor < ctSimulatorConstant.UnderGround)
        {
            currentFloor = "B" + (ctSimulatorConstant.UnderGround - floor);
        }

        else if(floor >= ctSimulatorConstant.UnderGround)
        {
            currentFloor = "F" + (floor-ctSimulatorConstant.UnderGround + 1);
        }
        return currentFloor;
    }
}
