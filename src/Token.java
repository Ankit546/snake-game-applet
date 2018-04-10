
import java.awt.Color;
import java.awt.Graphics;

public class Token {
private int x,y,score;
private Snake snake;
    
    public Token(Snake s){
        x=(int)(Math.random()*395);//making sure its on the applet!
        y=(int)(Math.random()*395);
        snake =s;
    }
    
    public void changePosition(){
        x=(int)(Math.random()*395);
        y=(int)(Math.random()*395);
        
    
    }
    
    public int getScore(){
        return score;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x,y,6,6);
    
    }

  public boolean snakeCollision(){
    int snakeX=snake.getHeadX()+2;
    int snakeY=snake.getHeadY()+2;
    
    if(snakeX >= x-1 && snakeX<=(x+7) ){
        if(snakeY>=y-1 && snakeY <=(y+7)){
            changePosition();
            score++;
            snake.setElongate(true);
            return true;
        }
       }
    return false;
    }
}