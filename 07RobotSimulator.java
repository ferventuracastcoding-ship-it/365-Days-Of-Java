import javax.swing.*;
import java.awt.*;

public class RobotSimulator extends JFrame {

    JLabel robot;
    JLabel status;

    int x = 150;
    int y = 150;
    int energy = 100;

    public RobotSimulator(){

        setTitle("Robot Simulator");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        robot = new JLabel("🤖");
        robot.setFont(new Font("Arial",Font.PLAIN,50));
        robot.setBounds(x,y,80,80);


        status = new JLabel("Energy: 100%");
        status.setBounds(20,20,200,30);


        JButton up = new JButton("↑");
        JButton down = new JButton("↓");
        JButton left = new JButton("←");
        JButton right = new JButton("→");


        up.setBounds(200,350,60,40);
        down.setBounds(200,420,60,40);
        left.setBounds(130,385,60,40);
        right.setBounds(270,385,60,40);


        add(robot);
        add(status);
        add(up);
        add(down);
        add(left);
        add(right);


        up.addActionListener(e -> move(0,-20));
        down.addActionListener(e -> move(0,20));
        left.addActionListener(e -> move(-20,0));
        right.addActionListener(e -> move(20,0));


        setVisible(true);
    }


    void move(int dx,int dy){

        if(energy <= 0){
            status.setText("Robot battery empty!");
            return;
        }

        x += dx;
        y += dy;

        robot.setBounds(x,y,80,80);

        energy -= 5;

        status.setText("Energy: " + energy + "%");
    }


    public static void main(String[] args){
        new RobotSimulator();
    }
}
