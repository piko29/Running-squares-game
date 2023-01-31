package FistGame;

import static FistGame.Game.HEIGHT;
import static FistGame.Game.WIDTH;
import java.util.Random;

//adding enemies after time
public class Spawn {
    
    private Handler handler;
    private HeadUpDisplay hud;
    private Random r = new Random();
    
    private int scoreKeep = 0;
    
    
    public Spawn(Handler handler, HeadUpDisplay hud){
        this.handler = handler;
        this.hud = hud;
    }
    
    public void tick(){
        scoreKeep++;
        
        if(scoreKeep >= 250){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel()+1);
            
            if(hud.getLevel() == 2) {
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 3){
            handler.addObject(new FastEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.FastEnemy, handler));
            handler.addObject(new CrazyEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.CrazyEnemy, handler));
            handler.addObject(new FastSlowEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.FastSlowEnemy, handler));
            } else if (hud.getLevel() == 4){
            handler.addObject(new SmartEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.SmartEnemy, handler));    
            handler.addObject(new FastEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.FastEnemy, handler));
            handler.addObject(new CrazyEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.CrazyEnemy, handler));
            } else if (hud.getLevel() == 6){
            handler.removeEnemys();
            handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -100, ID.EnemyBoss, handler, 1));
            } else if (hud.getLevel() == 8){
            handler.removeEnemys();
            handler.addObject(new CrazyEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.CrazyEnemy, handler));
            handler.addObject(new FastEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.FastEnemy, handler));
            handler.addObject(new FastEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.FastEnemy, handler));
            handler.addObject(new SmartEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.SmartEnemy, handler));    
            handler.addObject(new SmartEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.SmartEnemy, handler));    
            } else if(hud.getLevel() == 10){
            handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -100, ID.EnemyBoss, handler, 2));        
            handler.addObject(new CrazyEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.CrazyEnemy, handler));
            handler.addObject(new CrazyEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.CrazyEnemy, handler));
            handler.addObject(new FastSlowEnemy(r.nextInt(WIDTH - 60), r.nextInt(HEIGHT - 60), ID.FastSlowEnemy, handler));
            }
        }
    }
}
