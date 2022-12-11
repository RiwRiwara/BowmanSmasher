package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_redPotion extends Entity {
    int value = 2;
    public static final String objName = "Red Potion";
    public OBJ_redPotion(GamePanel gp){
        super(gp);
        name = objName;
        down1 = setup("/res/objects/healthpotion.png");
        type = type_consumable;
        description = "[Red Potion]\nHeal.\n2 HP";
    }
    public void setDialogue (){
        dialogues[0][0] = "You heal 2 HP";

    }
    public boolean use(Entity entity){
        startDialogue(this, 0);
        entity.life += value;
        if(entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
        return true;
    }
}
