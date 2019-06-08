/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dsnake2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author new
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener {
    
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    
    private Boolean left = false;
    private Boolean right = false;
    private Boolean up = false;
    private Boolean down = false;
   
    
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    
    private int lengthofsnake = 3;
    private int score = 0;
    
    private int [] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250,
            275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600
            , 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    
    private int [] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250,
            275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600
            , 625};
    
    private ImageIcon enemyimage;
    
    Random random = new Random();
    
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    
    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeimage;
    
    private int moves = 0;
    
    private ImageIcon titleimage;
    
    public Gameplay() 
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) 
    {
        if(moves == 0) {
            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;
            
            snakeylength[2] = 200;
            snakeylength[1] = 200;
            snakeylength[0] = 200;
        }
        
        int int_score = Login.db_query();
        
        // Create leader board or Dash board
        
        // draw the image border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        
        // draw the titleimage
        titleimage = new ImageIcon("snaketitle.jpg");
        titleimage.paintIcon(this, g, 25, 11);
        
        // draw the gameplay border
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        
        // draw the background for gameplay
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        
        // draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("montserrat bold", Font.PLAIN, 14));
        g.drawString("Scores: "+score, 780, 30);
        
        // draw the username
        String user = Login.get_user();
        g.setColor(Color.WHITE);
        g.setFont(new Font("montserrat", Font.PLAIN, 16));
        g.drawString("Welcome, "+user, 50, 35);
        
        // draw the high_score
        g.setColor(Color.WHITE);
        g.setFont(new Font("montserrat bold", Font.PLAIN, 14));
        g.drawString("High Score: "+int_score, 50, 55);
        
        // draw the lengthofsnake
        if(lengthofsnake < 40){
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("montserrat bold", Font.PLAIN, 14));
            g.drawString("Length: "+lengthofsnake, 780, 50);
            
        }else if(lengthofsnake >= 40){
            
            g.setColor(Color.RED);
            g.setFont(new Font("montserrat bold", Font.PLAIN, 14));
            g.drawString("Length: "+lengthofsnake, 780, 50);
            
        }
        
        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        
        for(int a=0; a<lengthofsnake; a++) {
            
            if (a == 0 && right) {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if (a == 0 && left) {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if (a == 0 && down) {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if (a == 0 && up) {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a!=0) {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
        }
        
        enemyimage = new ImageIcon("enemy.png");
        
        if(snakexlength[0] == enemyxpos[xpos] && snakeylength[0] == enemyypos[ypos]) {
            
            score++;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
            
        }
        
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        
        
        if(score > int_score){
            g.setColor(Color.GREEN);
            g.setFont(new Font("montserrat bold", Font.PLAIN, 14));
            g.drawString("New High Score: "+score, 620, 30);
        }
        
        for(int b = 1; b<lengthofsnake; b++) {
            if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
                right = false;
                left = false;
                down = false;
                up = false;

                g.setColor(Color.YELLOW);
                g.setFont(new Font("comic sans ms", Font.PLAIN, 50));
                g.drawString("GAME OVER", 300, 300);

                g.setFont(new Font("comic sans ms", Font.PLAIN, 24));
                g.drawString("Space to RESTART", 340, 350);
    
                try{
                    
                
                    if(score >= int_score){

                        int new_high_score = score;

                        Login.db_update(new_high_score);

                    }
                    
                }catch(Exception e){
                    
                    JOptionPane.showMessageDialog(null, e);
                    
                }
                
                
           }
        }
        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.start();
        if(right) {
            for(int r = lengthofsnake - 1; r>=0; r--) {
                snakeylength[r + 1] = snakeylength[r];
            }
            
            for (int r = lengthofsnake; r>=0; r--) {
                if(r==0)
                    snakexlength[r] = snakexlength[r] + 25;
                else
                    snakexlength[r] = snakexlength[r - 1];
                
                if(snakexlength[r] > 850)
                    snakexlength[r] = 25;
            }
            repaint();
        }
        if(left) {
            for(int r = lengthofsnake -1; r>=0; r--) {
                snakeylength[r+1] = snakeylength[r];
            }
            
            for (int r = lengthofsnake; r>=0; r--) {
                if(r==0)
                    snakexlength[r] = snakexlength[r] - 25;
                else
                    snakexlength[r] = snakexlength[r-1];
                
                if(snakexlength[r] < 25)
                    snakexlength[r] = 850;
            }
            repaint();
        }
        if(down) {
            for(int r = lengthofsnake -1; r>=0; r--) {
                snakexlength[r+1] = snakexlength[r];
            }
            
            for (int r = lengthofsnake; r>=0; r--) {
                if(r==0)
                    snakeylength[r] = snakeylength[r] + 25; // moves the head
                else
                    snakeylength[r] = snakeylength[r-1];
                
                if(snakeylength[r] > 625)
                    snakeylength[r] = 75;
            }
            repaint();
        }
        if(up) {
            for(int r = lengthofsnake -1; r>=0; r--) {
                snakexlength[r+1] = snakexlength[r];
            }
            
            for (int r = lengthofsnake; r>=0; r--) {
                if(r==0)
                    snakeylength[r] = snakeylength[r] - 25;
                else
                    snakeylength[r] = snakeylength[r-1];
                
                if(snakeylength[r] < 75)
                    snakeylength[r] = 625;
            }
            repaint();
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(ke.getKeyCode() == KeyEvent.VK_ALT) {
            right = false;
            left = false;
            down = false;
            up = false;
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_NUM_LOCK) {
            lengthofsnake = 3;
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            lengthofsnake = 3;
            score = 0;
            repaint();
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves ++;
            right = true;
            if(!left)
                right = true;
            else {
                left = true;
                right = false;
            }
            up = false;
            down = false;
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
            moves ++;
            left = true;
            if(!right)
                left = true;
            else {
                right = true;
                left = false;
            }
            up = false;
            down = false;
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
            moves ++;
            down = true;
            if(!up)
                down = true;
            else {
                up = true;
                down = false;
            }
            right = false;
            left = false;
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_UP) {
            moves ++;
            up = true;
            if(!down)
                up = true;
            else {
                down = true;
                up = false;
            }
            right = false;
            left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
