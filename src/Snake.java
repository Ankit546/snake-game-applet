import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Snake {
    List<Point> snakePoints;
    //creating an arrayList then instatiating it in the Snake Constuctor!
    int xDir,yDir;
    boolean isMoving, elongate;
    //is the snake moving or not
    final int STARTSIZE=20, STARTX=150, STARTY=150;
    //when the applet is started this will be the start position of the snake
    
    
    public Snake(){
    snakePoints= new ArrayList<Point>();// instantiating the above list
    xDir=0;
    yDir=0;
    isMoving=false;
    elongate=false;
    //snakePoints.add(new Point(STARTX,STARTY));
    snakePoints.add(new Point(STARTX,STARTY));
    
    for(int i=1;i<STARTSIZE;i++){
        //to create the initial line of snake to the left at first then let user pick the direction later
        snakePoints.add(new Point(STARTX-i*4,STARTY));
    }
    }
    public void draw(Graphics g){
    
       g.setColor(Color.white);
       for(Point p: snakePoints){
           g.fillRect(p.getX(), p.getY(), 4, 4);//4 is the size of one unit of snake, 
           
       }

    }
    //actual movement of the snake 
    public void move(){
        
        if(isMoving){
        //this function will be called in the infinite loop of SnakeGame so that when we call it, the snake moves!
        Point temp=snakePoints.get(0);//to get current position of the snake
        
        Point last=snakePoints.get(snakePoints.size()-1);
        
        Point newStart=new Point(temp.getX()+xDir*4, temp.getY()+yDir*4);
        //this represents the new starting point for the snake
        //adding 4 or subtract 4 if the
       //now we got the start of the snake next we should know other points as well so as to move this in other directions!
        for(int i=snakePoints.size()-1;i>=1;i--){
        //we are doing this backwards
        //the point previous to index will become next index and so on
            snakePoints.set(i,snakePoints.get(i-1));
        }
     
        //upper loop will update the whole body, to set the header 
        snakePoints.set(0,newStart);
        
        if(elongate){
            
            snakePoints.add(last);
            elongate=false;
        }
        }
 }
 
    public boolean snakeCollision(){
    //collided with itself!
        int x=this.getHeadX();
        int y=this.getHeadY();
       
        for(int i=1;i<snakePoints.size();i++){
               
                    if(snakePoints.get(i).getX()==x && snakePoints.get(i).getY()== y)
                     return true;
        }
        return false;
       }
    

    
    public boolean isMoving(){
        return isMoving;//just to access these from another files! making a function is lot better than directly changing values!
    }
    
    public void setIsMoving(boolean b){
        isMoving=b;
        
    }
    public int getXDir(){
         return xDir;
    }
    public int getYDir(){
         return yDir;
    }
    public void setXDir(int x){
        xDir=x;
    }
    public void setYDir(int y){
        yDir=y;
    }
    //x position of snake head
    public int getHeadX(){
        return snakePoints.get(0).getX();
    }
    
    public int getHeadY(){
        return snakePoints.get(0).getY(); 
    }

    public void setElongate(boolean b){
        elongate=b;
    }
}
