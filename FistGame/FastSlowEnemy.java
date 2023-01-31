package FistGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class FastSlowEnemy extends GameObject {

    private Handler handler;
    Random r = new Random();
    
    public FastSlowEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    
    this.handler = handler;          
    }
    
    @Override
    public Rectangle getBounds(){
    return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        

        velX +=(r.nextFloat());
        velY +=(r.nextFloat());
        
        
        
        if (y <= 0) velY = 0.5f;//change velocity
        if (x <= 0) velX = 0.5f;//change velocity
        
        
        if(x >= Game.WIDTH - 32){
            velX *=-1;
        }
        if(y >= Game.HEIGHT - 54)
            velY *= -1;//bounce from the bottom
    
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.ORANGE, 16, 16, 0.09f, handler));       
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)x, (int)y, 16, 16);
    }   
}