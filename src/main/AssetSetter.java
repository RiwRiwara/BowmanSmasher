package main;

import entity.Zombie;
import object.OBJ_BOX;
import object.OBJ_DOOR;
import object.OBJ_KEY;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_KEY();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 37 * gp.tileSize;

        gp.obj[1] = new OBJ_BOX();
        gp.obj[1].worldX = 20 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_DOOR();
        gp.obj[2].worldX = 28 * gp.tileSize;
        gp.obj[2].worldY = 47 * gp.tileSize;


    }
    public void setMob() {
        gp.mob[0] = new Zombie(gp);
        gp.mob[0].worldX = 24 * gp.tileSize;
        gp.mob[0].worldY = 38 * gp.tileSize;
    }
}
