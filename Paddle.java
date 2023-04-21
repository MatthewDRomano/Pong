import javax.swing.*;
import java.awt.*;
public class Paddle extends JPanel
{
    private final int height = 130, width = 20;
    private boolean movingUp, movingDown;
    private Point defaultLocation;

    Paddle(int x, int y)
    {    
        setBackground(Color.white);
        setSize(width,height);  
        defaultLocation = new Point(x, y);  
        Reset();    
    }
    public void setUpStatus(Boolean status) { movingUp = status; }
    public Boolean getUpStatus() { return movingUp; }
    public void setDownStatus(Boolean status) { movingDown = status; }
    public Boolean getDownStatus() { return movingDown; }

    public double sector(double spotHit)//spothit = ball.getLocation.y - paddle.getLocation.y
    {
        if (spotHit < height*(1.25/6)) return Math.PI/4; // 0 - 1.25/6
        else if (spotHit < height*(2.5/6)) return Math.PI/6; // 1.25/6 - 2.5/6
        else if (spotHit < height*(3.5/6)) return 0; // 2.5/6 - 3.5/6
        else if (spotHit < height*(4.75/6)) return -Math.PI/6; // 3.5/6 - 4.75/6
        return -Math.PI/4; //5/6 = 6/5
    }
    public void Reset() 
    { 
        setLocation(defaultLocation); 
        movingDown = false;
        movingUp = false;
    }      
}