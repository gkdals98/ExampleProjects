package com.cm.elevator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
 * <li> Type Name    : elevator.ctElevatorView
 * <li> Created Time : 2017. 12. 1.
 * <li> Version      : 1.00
 * <li> Author       : hmcho(Cho, Hang Min)
 * <li> Description  :
 * </ul>
 * </p>
 */
public class ctElevatorView extends JPanel implements Observer{


    private ctElevatorController elevatorControllerInterface;

    private JLabel layerLabel;
    private JLabel upDownLabel;
    private JLabel openLabel;

    private JButton[] layerButtons;

    private volatile boolean volclicked;
    private volatile int voldestination;
    private volatile boolean volisopen;
    private volatile String vollocation;
    private volatile int volUpDown;



    public ctElevatorView(ctElevatorController elevatorControllerInterface)
    {
        this.elevatorControllerInterface = elevatorControllerInterface;
        volclicked = false;
        volisopen = false;
        vollocation = "";
        voldestination = 0;
        volUpDown = ctSimulatorConstant.STOP;

        createView();
    }

    private void createView()
    {
        setLayout(new BorderLayout(0,0));
        createCenter();
        createEast();
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
                updateDestinationButton(message.getFloor (), message.isClicked ());
            }
            else if(message.getType() == ctElevatorMessage.OPENCLOSE) {
                updateOpenClose(message.isOpenClose ());
            }
            else if(message.getType () == ctElevatorMessage.LOCATION) {
                updateLocation(message.getLocation ());
            }
            else if(message.getType () == ctElevatorMessage.UPDOWN) {
                updateUpDownState(message.getUpDown ());
            }
        }
    }


    public void updateDestinationButton(int destination, boolean isClicked)
    {
        volclicked = isClicked;
        voldestination = destination;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(volclicked)
                {
                    layerButtons[voldestination].setBackground(ctUIConstant.CLICKED_BASE);
                    layerButtons[voldestination].setForeground(ctUIConstant.CLICKED_FONT);
                    layerButtons[voldestination].setBorder(ctUIConstant.CLICKED_BORDER);
                }
                else if(!volclicked)
                {
                    layerButtons[voldestination].setBackground(ctUIConstant.BUTTON_BASE);
                    layerButtons[voldestination].setForeground(ctUIConstant.BUTTON_FONT);
                    layerButtons[voldestination].setBorder(ctUIConstant.BUTTON_BORDER);
                }
            }
        });
    }

    public void updateOpenClose(boolean open)
    {
        volisopen = open;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(volisopen)
                {
                    openLabel.setText("Open");
                }
                else if(!volisopen)
                {
                    openLabel.setText("Close");
                }
            }
        });
    }

    public void updateLocation(int location)
    {
        if(location < ctSimulatorConstant.UnderGround)
        {
            vollocation = "B" + (ctSimulatorConstant.UnderGround - location);
        }

        else if(location >= ctSimulatorConstant.UnderGround)
        {
            vollocation = "F" + (location-ctSimulatorConstant.UnderGround + 1);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                layerLabel.setText(vollocation);
            }
        });
    }

    public void updateUpDownState(int upDown) {
        volUpDown = upDown;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(volUpDown == ctSimulatorConstant.UP)
                {
                    upDownLabel.setText("▲");
                }
                else if(volUpDown == ctSimulatorConstant.STOP)
                {
                    upDownLabel.setText("-");
                }
                else if(volUpDown == ctSimulatorConstant.DOWN)
                {
                    upDownLabel.setText("▼");
                }
            }
        });
    }



    //!--------------------------------뷰 생성 -----------------------------------!//

    public void createCenter() {

        //센터 판낼 설정 및 기타 컴포넌트 초기화
        JPanel noticePanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        noticePanel.setLayout(gridBag);
        noticePanel.setBackground(ctUIConstant.LABEL_BASE);


        JPanel labelHolder = new JPanel();
        layerLabel = makeLabel("F1", 40);
        upDownLabel = makeLabel("-", 40);
        openLabel = makeLabel("Close", 25);

        labelHolder.setLayout(new GridLayout(1,3));
        labelHolder.add(layerLabel);
        labelHolder.add(upDownLabel);
        labelHolder.add(openLabel);

        noticePanel.add(labelHolder,new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(120, 100, 120, 100), 0, 0 ));

        //최종적으로 메인프레임에 적제.
        add(noticePanel, BorderLayout.CENTER);
    }




    public void createEast() {
        //그리드 레이아웃에 현재 존재하는 층수 (지상층 + 지하층) 만큼의 칸을 확보한다.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(ctSimulatorConstant.FloorLength,1,0,0));
        buttonPanel.setBackground(ctUIConstant.LABEL_BASE);

        //버튼 배열도 현재 존재하는 층수(지상층 + 지하층) 만큼 확보해야 한다.
        layerButtons = new JButton[ctSimulatorConstant.FloorLength];

        //배열을 움직이기 위한 커서.
        int cursor = ctSimulatorConstant.FloorLength-1;

        //지상 - 높은 층수부터 차래로 버튼 배열에 버튼을 집어넣고, 커멘드를 설정한다.
        for(int i = ctSimulatorConstant.OnGround; i >0; i--)
        {
            System.out.println("cursor:" +cursor);
            layerButtons[cursor] = makeDestinationButton("F", i);

            //컨트롤러에게 액션 커멘드를 전달할 액션리스너 부착.
            layerButtons[cursor].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    elevatorControllerInterface.isClickedDestinatinButton(
                            Integer.parseInt(arg0.getActionCommand())+ctSimulatorConstant.UnderGround-1);
                    System.out.println(this + " :   엘리베이터 뷰의" + arg0.getActionCommand()+"층이 눌림");
                }
            });;

            buttonPanel.add(layerButtons[cursor]);
            cursor--;
        }

        //지하 - 높은 층수부터 차래로 버튼 배열에 버튼을 집어넣고, 커멘드를 설정한다.
        for(int i = 1; i <= ctSimulatorConstant.UnderGround; i++)
        {
            System.out.println("cursor:" +cursor);
            layerButtons[cursor] = makeDestinationButton("B", i);

            //컨트롤러에게 액션 커멘드를 전달할 액션리스너 부착.
            layerButtons[cursor].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    elevatorControllerInterface.isClickedDestinatinButton(
                            ctSimulatorConstant.UnderGround - Integer.parseInt(arg0.getActionCommand()));
                    System.out.println(this + " :   엘리베이터 뷰의 " + arg0.getActionCommand()+"층이 눌림");
                }

            });;

            buttonPanel.add(layerButtons[cursor]);
            cursor--;
        }

        //메인 컨테이너에 최종 적제
        buttonPanel.setPreferredSize(new Dimension(200, 0));
        add(buttonPanel, BorderLayout.EAST);
    }






    //!----------------------------------뷰 생성용반복메소드-----------------------------!!//

    public JLabel makeLabel(String value, int font) {
        JLabel label = new JLabel(value);
        label.setFont(new Font("Arial", Font.BOLD, font));
        label.setOpaque(true);
        label.setForeground(ctUIConstant.LABEL_FONT);
        label.setBackground(ctUIConstant.EMPHASIZEDLABEL_BASE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(ctUIConstant.LABEL_BORDER);

        return label;
    }

    public JButton makeDestinationButton(String text, int floor) {
        JButton buttons = new JButton(text + floor);
        buttons.setFont(new Font("Arial", Font.BOLD, 20));
        buttons.setBackground(ctUIConstant.BUTTON_BASE);
        buttons.setActionCommand(""+floor);
        buttons.setBorder(ctUIConstant.BUTTON_BORDER);

        return buttons;
    }

    public void gridBagSetting(GridBagConstraints c, int x, int y, int width, int height) {
        c.gridx=x; // 시작위치 x
        c.gridy=y; // 시작위치 y
        c.gridwidth=width; // 컨테이너 너비
        c.gridheight=height;  // 컨테이너 높이
    }
}
