package main;

import entity.NPC_Dad;
import monster.Zombie;
import object.OBJ_BOX;
import object.OBJ_DOOR;
import object.OBJ_KEY;
import object.OBJ_WARP;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_KEY(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 37 * gp.tileSize;

        gp.obj[1] = new OBJ_BOX(gp);
        gp.obj[1].worldX = 20 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_DOOR(gp);
        gp.obj[2].worldX = 28 * gp.tileSize;
        gp.obj[2].worldY = 47 * gp.tileSize;

        gp.obj[3] = new OBJ_WARP(gp);
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 40 * gp.tileSize;


    }
    public void setNPC(){
        gp.npc[0] = new NPC_Dad(gp);
        gp.npc[0].worldX = gp.tileSize * 5;
        gp.npc[0].worldY = gp.tileSize * 40;
    }
    public void setMonster() {
        gp.monster[0] = new Zombie(gp);
        gp.monster[0].worldX = gp.tileSize * 43;
        gp.monster[0].worldY = gp.tileSize * 44;

        gp.monster[1] = new Zombie(gp);
        gp.monster[1].worldX = gp.tileSize * 39;
        gp.monster[1].worldY = gp.tileSize * 42;

        gp.monster[2] = new Zombie(gp);
        gp.monster[2].worldX = gp.tileSize * 40;
        gp.monster[2].worldY = gp.tileSize * 42;

        gp.monster[3] = new Zombie(gp);
        gp.monster[3].worldX = gp.tileSize * 42;
        gp.monster[3].worldY = gp.tileSize * 42;

        gp.monster[4] = new Zombie(gp);
        gp.monster[4].worldX = gp.tileSize * 43;
        gp.monster[4].worldY = gp.tileSize * 43;





    }
}
