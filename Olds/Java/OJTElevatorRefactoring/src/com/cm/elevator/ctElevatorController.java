package com.cm.elevator;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : elevator.ctElevatorController
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctElevatorController {

    private ctElevatorModel _elevator_Model;
    /**
     * constructor <br>
     * description:
     *
     * @param elevatorModel
     */
    public ctElevatorController (ctElevatorModel _elevator_model) {
        // implementation
        this._elevator_Model = _elevator_model;
    }


    public void isClickedDestinatinButton(int destination)
    {
        _elevator_Model.setFloorSelect(destination);
    }
}
