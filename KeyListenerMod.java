import java.awt.event.*;

public class KeyListenerMod implements KeyListener
{   
    private Paddle L, R;

    public KeyListenerMod(Paddle L, Paddle R)
    {
        this.L = L;
        this.R = R;
    }
    public void keyPressed(KeyEvent e)
    {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    R.setUpStatus(true);
                    break;
                case KeyEvent.VK_DOWN:
                    R.setDownStatus(true);
                    break;
                case KeyEvent.VK_W:
                    L.setUpStatus(true);
                    break;
                case KeyEvent.VK_S:
                    L.setDownStatus(true);
                    break;
            }        
    }
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            R.setUpStatus(false);
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            R.setDownStatus(false);
        else if (e.getKeyCode() == KeyEvent.VK_W)
            L.setUpStatus(false);
        else if (e.getKeyCode() == KeyEvent.VK_S)
            L.setDownStatus(false);
    }
    public void keyTyped(KeyEvent e)
    {
        
    }
}