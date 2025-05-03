package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import static utilz.Constants.Directions.*;

public class keyboardInputs implements KeyListener{
    private GamePanel gamePanel;

    public keyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
        }
    }

    //getting the keyboard input
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                //System.out.println("W");
                gamePanel.setDirection(UP);
                break;
            case KeyEvent.VK_A:
                //System.out.println("A");
                gamePanel.setDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                //System.out.println("A");
                gamePanel.setDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                //System.out.println("D");
                gamePanel.setDirection(RIGHT);
                break;
        }
    }
    
}
