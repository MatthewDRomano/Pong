import javax.swing.*;
import java.awt.*;

public class Ball extends JPanel 
{
    private final int dimension = 20;
    private double angle = 0;   
    private int xSpeed = 8;

    Ball() { 
        setSize(dimension,dimension); 
    }

    public double getAngle() { return angle; }
    public void setAngle(double _angle) { angle = _angle; }  
    public int HorizontalMovement() { return xSpeed; }
    public double VerticalMovement() { return -(Math.tan(angle)*xSpeed); }

    public void Bounce() { xSpeed *=-1; }
    public void Move() { setLocation(getLocation().x += xSpeed, getLocation().y += VerticalMovement()); }

    public void Reset()
    {
        setAngle(0);
        setLocation(getParent().getWidth()/2-dimension/2, getParent().getHeight()/2-dimension/2);
        //xSpeed randomized between set range?
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
    }
}