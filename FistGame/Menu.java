package FistGame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HeadUpDisplay hud;//pasek z góry
    
    public Menu (Handler handler,Game game, HeadUpDisplay hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        //play button
        if(game.gameState == STATE.Menu){
        if(mouseOver(mx, my, 200, 100, 200, 64)){
            game.gameState = STATE.Game;
            handler.removeEnemys();//czyscimy wrogow z ekr. głównego
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));   
            }
        
        //help button
        if(mouseOver(mx, my, 200, 200, 200, 64)){
            game.gameState = STATE.Help;
            }
        //quit button
        if(mouseOver(mx, my, 200, 300, 200, 64)){
            System.exit(0);
            }
        }
        //back button for help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 200, 340, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }
        //try again
        if(game.gameState == STATE.End){
            if(mouseOver(mx, my, 200, 300, 200, 64)){
            game.gameState = STATE.Game;
            hud.setLevel(1);
            hud.setScore(0);
            handler.removeEnemys();//czyscimy wrogow z ekr. głównego
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));   
            }
        }        
    }
    
    public void mouseReleased(MouseEvent e){      
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my> y && my < y+ height){
                return true;
            } else return false;
        }else return false;
    }
    
    public void tick(){       
    }
    
    public void render(Graphics g){
        if(game.gameState == STATE.Menu){
        Font fnt = new Font("arial", 1,50);
        Font fnt1 = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.MAGENTA);
        g.drawString("Menu", 235, 80);
        
        g.setFont(fnt1);
        g.drawRect(200, 100, 200, 64);
        g.drawString("Play", 270, 140);
        
        g.setColor(Color.MAGENTA);
        g.drawRect(200, 200, 200, 64);
        g.drawString("Help", 270, 240);
        
        g.setColor(Color.MAGENTA);
        g.drawRect(200, 300, 200, 64);
        g.drawString("Quit", 270, 340);
        }
        else if (game.gameState == STATE.Help){
        Font fnt = new Font("arial", 1,50);
        Font fnt1 = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.MAGENTA);
        g.drawString("Help", 250, 80);
        
        g.setFont(fnt1);
        g.drawString("Use WASD or arrows to move player", 50, 200);
        g.drawString("and escape from enemies.", 120, 240);
        g.drawString("ENTER -Start/Restart.", 150, 280);
        g.drawString("Space - pause.", 180,320);
        
        
        g.drawRect(200, 340, 200, 64);
        g.drawString("Back", 270, 380);
        }
        else if (game.gameState == STATE.End){
        Font fnt = new Font("arial", 1,50);
        Font fnt1 = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.MAGENTA);
        g.drawString("Game Over", 200, 80);
        
        g.setFont(fnt1);
        g.drawString("You lost", 230, 180);
        g.drawString("Your score " +hud.getScore(), 180, 240);
        
        g.drawRect(200, 300, 200, 64);
        g.drawString("Try Again", 220, 340);
        }       
    }    
}
