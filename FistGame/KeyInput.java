package FistGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import FistGame.Game;
import java.util.Random;

public class KeyInput extends KeyAdapter {
    
    private Handler handler;
    private boolean[] keyDown= new boolean[4];
    
    Game game;
    //restart 
    private Random r = new Random();
    private HeadUpDisplay hud;
    
    public KeyInput(Handler handler, Game game, HeadUpDisplay hud){
        this.handler = handler;
        this.game = game;
        this.hud = hud;
        
        keyDown[0]=false;
        keyDown[1]=false;
        keyDown[2]=false;
        keyDown[3]=false;       
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {tempObject.setVelY(-5); keyDown[0]=true;}
                if(key ==KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {tempObject.setVelY(5); keyDown[1]=true;}
                if(key ==KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {tempObject.setVelX(-5); keyDown[2]=true;}
                if(key ==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {tempObject.setVelX(5); keyDown[3]=true;}
                if (key==KeyEvent.VK_ENTER);            
            }          
        }
        
        if(key == KeyEvent.VK_ESCAPE) System.exit(0);

        if (key == KeyEvent.VK_SPACE){
            if(game.gameState == STATE.Game){
            if(Game.paused) Game.paused = false;
            else Game.paused = true;        
            }
        }
        //restart
        if (key == KeyEvent.VK_ENTER){
            if((game.gameState == STATE.End)||(game.gameState==STATE.Menu)){
                game.gameState = STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.removeEnemys();
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));   
                }
            }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
    for (int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){

                if(key ==KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
                if(key ==KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false;
                if(key ==KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[2] = false;
                if(key ==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false;
                //vertical movement 
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
}
