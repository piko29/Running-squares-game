package FistGame;
//elements handler

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
   
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void tick(){
        for(int i=0; i<object.size(); i++){
            GameObject tempObject = object.get(i);//adding elements
            
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i=0; i<object.size(); i++){
            GameObject tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeEnemys(){
    //    object.clear();//clears everything in the window
        for(int i=0; i<object.size(); i++){
            GameObject tempObject = object.get(i);           
            if(tempObject.getId() != ID.Player){
                removeObject(tempObject);
            i--;
            }
        }
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
