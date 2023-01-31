package FistGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;//16/9 RATIO
    private Thread thread; //only 1 thread
    private boolean running = false;
    static boolean paused = false;
    
    private Random r;
    private Handler handler;
    private HeadUpDisplay hud;
    private Spawn spawner;
    private Menu menu;
    
    public STATE gameState = STATE.Menu;
    
    public Game()
    {
        handler = new Handler();
        hud = new HeadUpDisplay();
        menu = new Menu(handler, this, hud);
        this.addKeyListener(new KeyInput(handler, this, hud));
        this.addMouseListener(menu);
        
        new Window(WIDTH, HEIGHT, "Running squares Game", this);
        
        spawner = new Spawn(handler, hud);
        
        r = new Random();
        
        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));   
        } else {
            for(int i=0; i<15; i++){
                handler.addObject(new MenuPart(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuPart, handler));
            }
        }      
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);//instance of our Game class
        thread.start();
        running=true;
    }
    
    public synchronized void stop()
    {
        try{
            thread.join();//stop thread, killing of a thread
            running=false;
        }catch(Exception e){//exception handling
            e.printStackTrace();
        }
    }
    
    @Override
    public void run()
    {//popular game loop
        this.requestFocus();//activate listener 
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        if(!paused){
        handler.tick();
        if(gameState == STATE.Game){
        hud.tick();
        spawner.tick();  
        
        if (HeadUpDisplay.HEALTH <=0){
            HeadUpDisplay.HEALTH = 100;
            gameState = STATE.End;
            handler.object.clear();
            for(int i=0; i<8; i++){
                handler.addObject(new MenuPart(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuPart, handler));
                }
            }            
        }

        } else if(gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        }

    }
    private void render(){
        BufferStrategy bs= this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g =bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        
        if(paused){
            g.setColor(Color.YELLOW);
            g.drawString("PAUSED", WIDTH/2 -40, 100);
        }
        
        if(gameState == STATE.Game){
           hud.render(g); 
        } else if (gameState == STATE.Menu || gameState == STATE.Help 
                || gameState == STATE.End) {
            menu.render(g);
        }
               
        g.dispose();//clear, close
        bs.show();
    }
    //boundries for the player
    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }
    public static void main(String args[])
    {
        new Game();
    }   
}
