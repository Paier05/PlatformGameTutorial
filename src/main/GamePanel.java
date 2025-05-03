package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import Inputs.keyboardInputs;
import Inputs.mouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel{
    
    private mouseInputs mouseinputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick = 0, aniIndex = 0, aniSpeed = 20; // speed will be faster if the aniSpeed is lower
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel(){
        mouseinputs = new mouseInputs(this);

        importImg();
        loadAnimation();

        setPanelSize();

        addKeyListener(new keyboardInputs(this));
        addMouseListener(mouseinputs);
        addMouseMotionListener(mouseinputs);

        setFocusable(true); // Allows GamePanel to receive key events
        requestFocusInWindow();// Requests focus when the panel is displayed
    }

    private void loadAnimation() {
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++){
            for(int i =0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/character/player_sprites.png");

        //always use try to make sure it always work or see whats the error
        //always remember to close the input whenever we used it
        try{
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1200, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {
       aniTick++;
       if(aniTick >= aniSpeed){
           aniTick = 0;
           aniIndex++;
           if(aniIndex >= GetSpriteAmount(playerAction)){
               aniIndex = 0;
            }
       }
    }

    private void setAnimation() {
        if(moving){
            playerAction = RUNNING;
        }else{
            playerAction = IDLE;
        }
    }

    private void updatePosition(){
        if(moving){
            switch(playerDirection){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        updateAnimationTick();

        setAnimation();
        
        updatePosition();

        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
    }
}
