package main;

import entity.NPC_Dad;
import monster.Zombie;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){

        int i  = 0;
        gp.obj[i] = new OBJ_WARP(gp, 44, 41);
        gp.obj[i].worldX = 4 * gp.tileSize;
        gp.obj[i].worldY = 38 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_DOOR(gp);
        gp.obj[i].worldX = 28  * gp.tileSize;
        gp.obj[i].worldY = 47 * gp.tileSize;

    }
    public void setItem(){
        int i  = 0;
        gp.item[i] = new OBJ_KEY(gp);
        gp.item[i].worldX = 23 * gp.tileSize;
        gp.item[i].worldY = 37 * gp.tileSize;
        i++;
        gp.item[i] = new OBJ_Spear(gp);
        gp.item[i].worldX = 18 * gp.tileSize;
        gp.item[i].worldY = 43 * gp.tileSize;
    }
    public void setNPC(){
        int i  = 0;
        gp.npc[i] = new NPC_Dad(gp);
        gp.npc[i].worldX = gp.tileSize * 5;
        gp.npc[i].worldY = gp.tileSize * 40;
        i++;
    }
    public void setMonster() {
        int i  = 0;
        gp.monster[i] = new Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 43;
        gp.monster[i].worldY = gp.tileSize * 44;
        i++;

        gp.monster[i] = new Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 39;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 40;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 42;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 43;
        gp.monster[i].worldY = gp.tileSize * 43;
        i++;





    }
}
