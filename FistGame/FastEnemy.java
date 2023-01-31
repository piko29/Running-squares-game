package FistGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class FastEnemy extends GameObject {

    private Handler handler;
    
    public FastEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    
    this.handler = handler;
    
    velX=2;
    velY=9;
           
    }
    @Override
    public Rectangle getBounds(){
    return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        if(y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.CYAN, 16, 16, 0.05f, handler));       
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y, 16, 16);
    }   
}