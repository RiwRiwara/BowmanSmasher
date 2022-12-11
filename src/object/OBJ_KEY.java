package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_KEY extends Entity{
    GamePanel gp;
    public static final String objName = "Key";
    public OBJ_KEY(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = objName;
        down1 = setup("/res/objects/Key.png");
        description = "["+name+"]\nA Ancient key\n you can found everywhere.";
        setDialogue();
    }
    public void setDialogue (){
        dialogues[0][0] = "Nothing";
        dialogues[1][0] = "Open door!";
    }
    public boolean use(Entity entity){
        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "Door");
        if(objIndex != 999) {
            startDialogue(this, 1);
            gp.playSE(2);
            gp.obj[gp.currentMap][objIndex] = null;
            gp.playSE(2);
            return true;
        }else{
            startDialogue(this, 0);
            return false;
        }
    }

}
