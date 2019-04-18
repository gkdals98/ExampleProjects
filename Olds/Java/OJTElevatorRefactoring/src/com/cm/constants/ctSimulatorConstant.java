package com.cm.constants;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : constants.ctSimulatorModel
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  : this data class provides Base informations to other class's
 * </ul>
 * </p>
 */
public class ctSimulatorConstant {

    public static final int OnGround = 5;
    public static final int UnderGround = 1;

    public static final int FloorLength = OnGround + UnderGround;
    public static final String ProjectName = "Elevator Project";

    public static final int STOP = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;

    public static final int MoveSpeed = 2000;
    public static final int OpenCloseSpeed = 1000;
}
