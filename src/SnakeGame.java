import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeGame extends Applet implements Runnable, KeyListener{
 //class that will control everything happening inside the applet class!
    
    Graphics gfx;
    Image img;
    //create thread
    Thread thread;
    //instatiation inside init
    Snake snake;
    boolean gameOver;
    Token token;
    public void init(){
        //starting point of applet
        this.resize(400,400);
        img=createImage(400,400);
        gfx=img.getGraphics();
        this.addKeyListener(this);
        thread=new Thread(this);
        
        thread.start();
        snake=new Snake();
        //here initialization is wrong for snake, thats why showing NullPointerException
        gameOver=false;
        token=new Token(snake);

 
    }
    
    public void paint(Graphics g){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0,0,400,400);
        if(!gameOver){
        snake.draw(gfx);
        token.draw(gfx);
        //gfx are off screen graphics and g is on screen graphics
        }
        else{
            gfx.setColor(Color.red);
            gfx.drawString("GAME OVER!", 180, 150 );
            gfx.drawString("Score: "+token.getScore(),180,170);
        }
        g.drawImage(img,0,0,null);
        
        //drawImage should be at the last line
    }
    public void update(Graphics g){
        paint(g);
    }
    
    public void repaint(Graphics g){
        paint(g);
    }


    public void run() {
        for(;;){
            if(snake!=null)
                if(!gameOver){
                //this snake.move() can throw a NullPointerException, so we will have to handle this
                //go to Debug>New BreakPoint>Breakpoint Type>Exception, in exception class field type java.lang.NullPointerException
                //to add a breakpoint simply click on the line numbers on the left index of this window!
                
                 snake.move();
                this.checkGameOver();
                token.snakeCollision();//if the snake collides with token then run this method!
                }
            //drawing the snake over and over so that it gives illusion of moving!
                this.repaint();
            try {
                
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                
            }
        }
    
    }
    public void checkGameOver(){
            
        //actual training of snake
        if(snake.getHeadX()<0 || snake.getHeadX()>396){
            gameOver=true;
        }
        if(snake.getHeadY()<0 || snake.getHeadY()>396){
            gameOver=true;
        }
        if(snake.snakeCollision()){
            gameOver=true; 
        
        }
        
    }

    public void keyTyped(KeyEvent e) {       
    }

    public void keyPressed(KeyEvent e) {
        if(!snake.isMoving()){
            //initially facing right
            if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN || 
                    e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT){
                
                    snake.setIsMoving(true);
            }
        
        }
    //snake should always be moving in left right or other directions it cant stop
        if(e.getKeyCode()==KeyEvent.VK_UP){
            if(snake.getYDir()!=1){
                //for going down its 1
                //if they are already going down you cant go straight up to avoid running in themselves
                snake.setYDir(-1);
                snake.setXDir(0);
                //no going diagonal!
            }
             
        
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(snake.getYDir()!=-1){
                snake.setYDir(1);
                snake.setXDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
        if(snake.getXDir()!=1)
            //if snake goinf to right,1 then cant do anything if not do the following!
        {
                snake.setXDir(-1);
                snake.setYDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        if(snake.getXDir()!=-1){
                snake.setXDir(1);
                snake.setYDir(0);
            }
        }
    }

    public void keyReleased(KeyEvent ke) {
    
    }

    
}
