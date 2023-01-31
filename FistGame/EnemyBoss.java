package FistGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class EnemyBoss extends GameObject {
    
    private Handler handler;
    Random r = new Random();
    
    private int timer = 50;
    private int timer2 = 50;
    private int timer3 = 300;
    
    public int bossCycle;
    
    public EnemyBoss(int x, int y, ID id, Handler handler, int bossCycle) {
    super(x, y, id);
    
    this.handler = handler;
    this.bossCycle = bossCycle;
    
    velX=0;
    velY=2;
           
    }
    @Override
    public Rectangle getBounds(){
    return new Rectangle((int)x, (int)y, 96, 96);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if (timer <= 0){
            velY = 0;//move down and stop when timer 0
            timer2--;//and start counting on timer 2
        }
        else timer--;
        
        if (timer2 <=0)
        {
            if(velX==0) velX=2; //if timer = 0 start move left right
            
            if(velX>0)
            velX +=0.01f;//Boss acceleration
            else if(velX<0)
            velX -=0.01f;
            
            velX = Game.clamp(velX, -10, 10);
            
            int spawn = r.nextInt(10);//bullets
            if (spawn==0) handler.addObject(new EnemyBossBullet((int)x+48, (int)y+48, ID.EnemyBossBullet, handler));
        
            timer3--;//going back to the top 
            System.out.println(timer3);
            System.out.println(bossCycle);
        }
        
        if((timer3 <= 0)&&(bossCycle == 1))
        {   
            System.out.println(timer3);
            System.out.println(bossCycle);
            velY = -4;         
        } else if ((timer3 <= 0)&&(bossCycle == 2)) velY=0;
       
        if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
  
        handler.addObject(new Trail((int)x,(int)y, ID.Trail, Color.RED, 96, 96, 0.05f, handler));     
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 96, 96);
    }   
}
