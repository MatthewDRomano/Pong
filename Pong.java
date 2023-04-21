import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Pong
{   
    static JPanel screen;
    static JLabel leftScore, rightScore;
    static Integer LScore = 0, RScore = 0;
    static Paddle LeftPaddle, RightPaddle;
    static Ball ball;
    public static void main(String[] args)
    {
        JFrame gui = new JFrame()
        {
            {
                setTitle("Pong");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(858, 525);
                setResizable(false);
            }
        };

        gui = InstantiateGame(gui);
        createLine();

        ActionListener taskPerformer = new ActionListener() //trig may be wrong
        {
            public void actionPerformed(ActionEvent evt) 
            {//put all movement in methods in repsective classes: ex: ball.move / paddle.move
                if (LeftPaddle.getUpStatus() && LeftPaddle.getLocation().y > 0) LeftPaddle.setLocation(LeftPaddle.getLocation().x, LeftPaddle.getLocation().y-=5);
                if (LeftPaddle.getDownStatus() && LeftPaddle.getLocation().y + LeftPaddle.getHeight() < LeftPaddle.getParent().getHeight()) LeftPaddle.setLocation(LeftPaddle.getLocation().x, LeftPaddle.getLocation().y+=5);
                if (RightPaddle.getUpStatus() && RightPaddle.getLocation().y > 0) RightPaddle.setLocation(RightPaddle.getLocation().x, RightPaddle.getLocation().y-=5);
                if (RightPaddle.getDownStatus() && RightPaddle.getLocation().y + RightPaddle.getHeight() < RightPaddle.getParent().getHeight()) RightPaddle.setLocation(RightPaddle.getLocation().x, RightPaddle.getLocation().y+=5);


            ball.Move();
            checkIfDeflected();
            checkIfMissed();

            if (ball.getLocation().y <= 0 || ball.getLocation().y >= screen.getHeight())  
            { ball.setAngle(Math.PI + (Math.PI - ball.getAngle())); ball.setLocation(ball.getLocation().x, ball.getLocation().y + (int)ball.VerticalMovement()); }

            if (Win(LScore, RScore)) System.exit(0);//implement smthg
            }
        };
        Timer timer = new Timer(10 ,taskPerformer);
        //timer.setRepeats(true);
        timer.start();    
        
    }
    public static JFrame InstantiateGame(JFrame gui)
    {
        screen = new JPanel();

        gui.add(screen);
        gui.setVisible(true);

        LeftPaddle = new Paddle(10, (int)(screen.getHeight()/2-(65)));   
        RightPaddle = new Paddle(812, (int)(screen.getHeight()/2-(65))); 
        ball = new Ball();

        ball.setLocation((screen.getWidth()/2)-(ball.getWidth()/2), (screen.getHeight()/2) - (ball.getHeight()/2));

        gui.addKeyListener(new KeyListenerMod(LeftPaddle, RightPaddle));

        screen.setBackground(Color.black);
        screen.add(LeftPaddle);
        screen.add(RightPaddle);
        screen.add(ball);

        leftScore = new JLabel();
        rightScore = new JLabel();
        leftScore.setBounds(325,50,50,50);
        rightScore.setBounds(525,50,50,50);
        leftScore.setText(LScore.toString());
        rightScore.setText(RScore.toString());
        screen.add(leftScore);
        screen.add(rightScore);

        screen.setLayout(null);  

        return gui;
    }
    
    public static boolean Win(int L, int R) { return (L == 11 || R == 11); }
        
    public static void checkIfDeflected()
    {
        if (ball.getLocation().y + ball.getHeight() > LeftPaddle.getY() && ball.getLocation().y < LeftPaddle.getLocation().y + LeftPaddle.getHeight() 
        && (ball.getLocation().x > LeftPaddle.getLocation().x && ball.getLocation().x < LeftPaddle.getLocation().x + LeftPaddle.getWidth())) 
        { 
            ball.setAngle(LeftPaddle.sector((ball.getLocation().y+ball.getHeight()/2) - LeftPaddle.getLocation().y)); 
            ball.Bounce(); 
            ball.setLocation(ball.getLocation().x+5, ball.getLocation().y);
        }

        else if (ball.getLocation().y + ball.getHeight() > RightPaddle.getY() && ball.getLocation().y < RightPaddle.getLocation().y + RightPaddle.getHeight()
        && (ball.getLocation().x + ball.getWidth() > RightPaddle.getLocation().x && ball.getLocation().x + ball.getWidth() < RightPaddle.getLocation().x + RightPaddle.getWidth()))
        { 
            ball.setAngle(Math.PI - (RightPaddle.sector((ball.getLocation().y+ball.getHeight()/2) - RightPaddle.getLocation().y))); 
            ball.Bounce();     
            ball.setLocation(ball.getLocation().x-5, ball.getLocation().y);    
        }   
    }
    public static void checkIfMissed()
    {
        if (ball.getLocation().x + ball.getWidth() <= 0)
            rightScore.setText(++RScore + ""); 
        else if (ball.getLocation().x > screen.getWidth())
            leftScore.setText(++LScore + "");
        else return;

        ball.Reset();
        LeftPaddle.Reset();
        RightPaddle.Reset();
    }
    public static void createLine()
    {
        for (int i = 0; i < screen.getHeight()/20; i++)
        {
            JPanel line = new JPanel();
            line.setBounds(screen.getWidth()/2-2, i*20 + 8, 4, 10);
            screen.add(line);
        }
    }
}