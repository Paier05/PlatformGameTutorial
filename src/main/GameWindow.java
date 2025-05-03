package main;

import javax.swing.JFrame; //library for creating a window

public class GameWindow {
    
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        //let it clode when we close it
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //putting panel into it
        jframe.add(gamePanel);

        //set it into middle
        jframe.setLocationRelativeTo(null);

        //make the window cannot be resize
        jframe.setResizable(false);

        //fit the size of the window to the component
        jframe.pack();
        
        //let the frame to be seen, to prevent black screen
        jframe.setVisible(true);
    }
}
