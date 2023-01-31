package FistGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
    
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
           
    }
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
    @Override
    public void tick() {
        x +=velX;
        y +=velY;
        
        x= Game.clamp((int)x, 0, Game.WIDTH - 32 - 16);
        y= Game.clamp((int)y, 0, Game.HEIGHT - 32 - 32 - 8);
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
        
        collision();
    }
    private void collision(){
        for (int i=0; i<handler.object.size(); i++){
        GameObject tempObject = handler.object.get(i);
        if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy 
                || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss
                || tempObject.getId() == ID.EnemyBossBullet ||
                tempObject.getId() == ID.CrazyEnemy || tempObject.getId() == ID.FastSlowEnemy){
            if(getBounds().intersects(tempObject.getBounds())){
            HeadUpDisplay.HEALTH -=2;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d= (Graphics2D) g;
        
        g.setColor(Color.green);//player green borders
        g2d.draw(getBounds()); 
        
        if(id==ID.Player) g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
        //g.fillOval((int)x, (int)y, 32, 32); //possibility to develop
    }   
}
