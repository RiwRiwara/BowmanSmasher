package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_redPotion extends Entity {
    int value = 2;
    public OBJ_redPotion(GamePanel gp){
        super(gp);
        name = "Red Potion";
        down1 = setup("/res/objects/healthpotion.png");
        type = type_consumable;
        description = "[Red Potion]\nHeal.\n2 HP";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You heal 2 HP";
        entity.life += value;
        if(entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
    }
}
