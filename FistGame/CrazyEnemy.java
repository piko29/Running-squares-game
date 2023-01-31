package FistGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class CrazyEnemy extends GameObject {

    private Handler handler;
    Random r = new Random();
    
    public CrazyEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    
    this.handler = handler;
    
    velX = 5;
    velY = 5;        
    }
    
    @Override
    public Rectangle getBounds(){
    return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    //bouncing from bottom
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        velY +=0.3f;
        if (y <= 0) velY = 0.5f;//set velocity to bottom

        if(x <= 0 || x >= Game.WIDTH - 32){
            velX *=-1;
        }
        if(y >= Game.HEIGHT - 54) //change velocity direction
            velY *= -1;
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.PINK, 16, 16, 0.05f, handler));     
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect((int)x, (int)y, 16, 16);
    } 
}