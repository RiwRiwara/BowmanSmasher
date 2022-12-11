package entity;

import main.GamePanel;
import object.*;

public class EntityGenerator {
    GamePanel gp;
    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName) {
        Entity obj = null;
        switch (itemName){
            case OBJ_Bowman.objName: obj = new OBJ_Bowman(gp); break;
            case OBJ_Spear.objName: obj = new OBJ_Spear(gp); break;
            case OBJ_DOOR.objName: obj = new OBJ_DOOR(gp); break;
            case OBJ_KEY.objName: obj = new OBJ_KEY(gp); break;
            case OBJ_redPotion.objName: obj = new OBJ_redPotion(gp); break;
        }
        return obj;
    }
}
