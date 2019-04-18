package com.cm.constants;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : constants.ctUIModel
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  : this Data class provides some UITheme to UI Classs's
 * </ul>
 * </p>
 */
public class ctUIConstant {

    public static final Color EMPHASIZEDLABEL_BASE = new Color(250, 250, 240);

    public static final Color LABEL_BASE = new Color(240, 240, 240);
    public static final Color LABEL_FONT = new Color(0, 0, 0);
    public static final Border LABEL_BORDER = BorderFactory.createLineBorder(new Color(210,210,210));

    public static final Color BUTTON_BASE = new Color(225, 225, 225);
    public static final Color BUTTON_FONT = new Color(0, 0, 0);
    public static final Border BUTTON_BORDER = BorderFactory.createRaisedBevelBorder();

    public static final Color CLICKED_BASE = new Color(190, 190, 190);
    public static final Color CLICKED_FONT = new Color(255, 0, 0);
    public static final Border CLICKED_BORDER = BorderFactory.createLineBorder(new Color(200, 0, 0));


    public static final Color CANT_CLICK_BASE = new Color(160, 160, 160);
    public static final Color CANT_CLICK_FONT = new Color(225, 225, 225);
    public static final Border CANT_CLICK_BORDER = BorderFactory.createLineBorder ( new Color(225, 225, 255) );

}
