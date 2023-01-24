import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
import javax.sound.sampled.*;
import javax.swing.*;

public class App implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JLabel counterLabel;
    private JButton startButton;
    private JButton resetButton;
    private Font font = new Font("Ink Free", Font.BOLD, 10) ;
    private int seconds = 0;
    private int minutes = 25;
    private String ddseconds, ddminutes;
    private DecimalFormat ddformat = new DecimalFormat("00");
    private boolean isStarted = false;
    private Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e){
            seconds--;
            ddseconds = ddformat.format(seconds);
            ddminutes = ddformat.format(minutes);
            counterLabel.setText(ddminutes + ":" + ddseconds);

            if(seconds==-1){
                minutes--;
                seconds=59;
                ddseconds = ddformat.format(seconds);
                ddminutes = ddformat.format(minutes);
                counterLabel.setText(ddminutes + ":" + ddseconds);
            }

            if(minutes==0 && seconds==0){
                timer.stop();
                startButton.setVisible(false);
            }
        }
    });

    public App(){
        frame = new JFrame("Pomodoro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(205,92,92));;
        frame.setLayout(null);

        title = new JLabel("POMODORO");
        title.setBounds(115, 10,300, 100);
        title.setForeground(Color.white);
        title.setFont(new Font("Ink Free", Font.BOLD, 50));

        counterLabel = new JLabel("25:00");
        counterLabel.setBounds(150, 30, 200, 200);
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setForeground(Color.WHITE);
        counterLabel.setFont(new Font("Ink Free", Font.BOLD, 30));

        startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setBounds(200, 300, 100, 30);

        resetButton = new JButton("Restart");
        resetButton.setFont(font);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);
        resetButton.setBounds(200, 340, 100, 30);
        
        frame.add(resetButton);
        frame.add(counterLabel);
        frame.add(title);
        frame.add(startButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            if(isStarted == false){
                isStarted = true;
                startButton.setText("Stop");
                timer.start();
            }
            else {
            isStarted = false;
            startButton.setText("Start");
            timer.stop();
            }
        }
        if(e.getSource() == resetButton) {
            isStarted = false;
            startButton.setText("Start");
            restart();
        }

    }
    public void restart(){
        timer.stop();
        startButton.setVisible(true);
        seconds = 0;
        minutes = 25;
        ddseconds = ddformat.format(seconds);
        ddminutes = ddformat.format(minutes);
        counterLabel.setText(ddminutes + ":" + ddseconds);
    }
}

