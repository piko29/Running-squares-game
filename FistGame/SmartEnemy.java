package FistGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject playerObject;
    
    public SmartEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    
    this.handler = handler;
    
    for (int i = 0; i< handler.object.size(); i++){
        if(handler.object.get(i).getId() == ID.Player) {
            playerObject = handler.object.get(i);
        }
    }
           
    }
    @Override
    public Rectangle getBounds(){
    return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        float diffX = x - playerObject.getX() - 8;//difference from the center of the player
        float diffY = y - playerObject.getY() - 8;
        float distance = (float) Math.sqrt((x-playerObject.getX())*(x-playerObject.getX())
        +(y-playerObject.getY())*(y-playerObject.getY()));
        
        velX = (float)((-1.0/distance)*diffX);
        velY = (float) ((-1.0/distance)*diffY);
        
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        if(y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.green, 16, 16, 0.05f, handler));      
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, 16, 16);
    }   
}
