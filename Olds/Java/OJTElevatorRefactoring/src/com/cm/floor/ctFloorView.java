package com.cm.floor;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.cm.constants.ctSimulatorConstant;
import com.cm.constants.ctUIConstant;

/**
 * <p>
 * <ul>
 * <li> Project Name : NIMS for OJTElevatorRefactoring
 * <li> Type Name    : floor.ctFloorView
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctFloorView extends JPanel implements Observer {



    //1. Gui를 구성할 요소들.
    private JLabel floorText;
    private JButton up;
    private JButton down;
    private JPanel buttonHolder;


    //2. Model, Controller
    public ctFloorController _floor_Controller;
    private volatile boolean isClick = false;

    //3. 생성자에선 모델과 컨트롤러를 가져온다
    public ctFloorView(ctFloorController _floor_Controller, String floor, int floorint)
    {
        //모델과 컨트롤러를 우선 저장.
        this._floor_Controller = _floor_Controller;
        createView(floor, floorint);
    }


    //4. 뷰를 생성하는 메소드.

    public void createView(String floor, int floorint){
        //자신의 레이아웃을 설정.
        setLayout(new GridLayout(2,1));

        //컴포넌트들의 초기화
        floorText = new JLabel(floor);
        floorText.setOpaque(true);
        floorText.setBackground(ctUIConstant.LABEL_BASE);
        floorText.setFont(new Font("Arial", Font.BOLD, 25));
        floorText.setHorizontalAlignment(JLabel.CENTER);
        floorText.setBorder(ctUIConstant.LABEL_BORDER);

        if(floorint != ctSimulatorConstant.FloorLength-1)
        {
            up = new JButton("▲");
            up.setFont(new Font("Arial", Font.BOLD, 20));
            up.setBackground(ctUIConstant.BUTTON_BASE);
            up.setBorder(ctUIConstant.BUTTON_BORDER);
            up.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    _floor_Controller.isClickedUp();
                }
            });
        }
        else if(floorint == ctSimulatorConstant.FloorLength-1)
        {
            up = new JButton("-");
            up.setFont(new Font("Arial", Font.BOLD, 20));
            up.setBackground(ctUIConstant.CANT_CLICK_BASE);
            up.setBorder(ctUIConstant.CANT_CLICK_BORDER);
            up.setForeground ( ctUIConstant.CANT_CLICK_FONT );
        }


        if(floorint != 0) {
            down = new JButton("▼");
            down.setBackground(ctUIConstant.BUTTON_BASE);
            down.setFont(new Font("Arial", Font.BOLD, 20));
            down.setBorder(ctUIConstant.BUTTON_BORDER);
            down.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    _floor_Controller.isClickedDown();
                }
            });
        }
        else if(floorint == 0)
        {
            down = new JButton("-");
            down.setFont(new Font("Arial", Font.BOLD, 20));
            down.setBackground(ctUIConstant.CANT_CLICK_BASE);
            down.setBorder(ctUIConstant.CANT_CLICK_BORDER);
            down.setForeground ( ctUIConstant.CANT_CLICK_FONT );
        }


        //최종 배치
        buttonHolder = new JPanel(new GridLayout(1,2));
        buttonHolder.setBackground(ctUIConstant.LABEL_BASE);
        buttonHolder.add(up);
        buttonHolder.add(down);

        add(floorText);
        add(buttonHolder);
    }


    //초기 뷰 구성을 위해 자기 자신의 뷰를 반환하는 메소드
    public JPanel getView() {
        return this;
    }


    @Override
    public void update ( Observable o, Object arg ) {
        if(arg instanceof ctFloorMessage)
        {
            ctFloorMessage message = (ctFloorMessage) arg;
            if(message.getType () == ctFloorMessage.UP) {
                updateIsClickedUp(message.getFloor (), message.isClickedUp ());
            }else if(message.getType () == ctFloorMessage.DOWN) {
                updateIsClickedDown(message.getFloor (), message.isClickedDown ());
            }
        }
    }



    //여기서 스레드가 돌아야 한다.
    //뷰는 자체판단이 아닌 업데이트로 자신의 색을 갱신시키기 떄문.
    public void updateIsClickedDown(int floor, boolean isClicked)
    {
        System.out.println ( floor );
        if(floor != 0) {
            isClick = isClicked;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if(isClick)
                    {
                        down.setBackground(ctUIConstant.CLICKED_BASE);
                        down.setForeground(ctUIConstant.CLICKED_FONT);
                        down.setBorder(ctUIConstant.CLICKED_BORDER);
                    }
                    else if(!isClick)
                    {
                        down.setBackground(ctUIConstant.BUTTON_BASE);
                        down.setForeground(ctUIConstant.BUTTON_FONT);
                        down.setBorder(ctUIConstant.BUTTON_BORDER);
                    }
                }
            });
        }

    }

    public void updateIsClickedUp(int floor, boolean isClicked)
    {
        if(floor != ctSimulatorConstant.FloorLength-1) {
            isClick = isClicked;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if(isClick)
                    {
                        up.setBackground(ctUIConstant.CLICKED_BASE);
                        up.setForeground(ctUIConstant.CLICKED_FONT);
                        up.setBorder(ctUIConstant.CLICKED_BORDER);
                    }
                    else if(!isClick)
                    {
                        up.setBackground(ctUIConstant.BUTTON_BASE);
                        up.setForeground(ctUIConstant.BUTTON_FONT);
                        up.setBorder(ctUIConstant.BUTTON_BORDER);
                    }
                }
            });
        }
    }
}