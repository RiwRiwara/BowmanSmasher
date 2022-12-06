package main;

import entity.Entity;
import entity.NPC_Dad;
import monster.Skeleton;
import monster.Slime;
import monster.Zombie;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){

        int i  = 0;
        gp.obj[gp.currentMap][i] = new OBJ_WARP(gp, 44, 41);
        gp.obj[gp.currentMap][i].worldX = 4 * gp.tileSize;
        gp.obj[gp.currentMap][i].worldY = 38 * gp.tileSize;
        i++;

        gp.obj[gp.currentMap][i] = new OBJ_DOOR(gp);
        gp.obj[gp.currentMap][i].worldX = 28  * gp.tileSize;
        gp.obj[gp.currentMap][i].worldY = 47 * gp.tileSize;
        i++;

        gp.obj[gp.currentMap][i] = new OBJ_BOX(gp);
        gp.obj[gp.currentMap][i].worldX = 3 * gp.tileSize;
        gp.obj[gp.currentMap][i].worldY = 37 * gp.tileSize;

    }
    public void setItem(){
        int i  = 0;
        gp.item[gp.currentMap][i] = new OBJ_KEY(gp);
        gp.item[gp.currentMap][i].worldX = 23 * gp.tileSize;
        gp.item[gp.currentMap][i].worldY = 37 * gp.tileSize;
        i++;
        gp.item[gp.currentMap][i] = new OBJ_Spear(gp);
        gp.item[gp.currentMap][i].worldX = 18 * gp.tileSize;
        gp.item[gp.currentMap][i].worldY = 43 * gp.tileSize;
        i++;
        gp.item[gp.currentMap][i] = new OBJ_redPotion(gp);
        gp.item[gp.currentMap][i].worldX = 39 * gp.tileSize;
        gp.item[gp.currentMap][i].worldY = 43 * gp.tileSize;


    }
    public void setNPC(){
        int i = 0;
        Entity dad = new NPC_Dad(gp);
        dad.worldX =  gp.tileSize * 5;
        dad.worldY = gp.tileSize * 40;
        gp.npc[gp.currentMap][i] = dad;

    }
    public void setMonster() {
        int i = 0;
        for (i = 0; i < 6; i++) {
            gp.monster[gp.currentMap][i] = new Zombie(gp, 36+i, 42);
        }
        for (i = 6; i < 15; i++) {
            gp.monster[gp.currentMap][i] = new Skeleton(gp, 33+i, 32+i%4);
        }

        gp.monster[gp.currentMap][i] = new Slime(gp, 4, 40);

    }
}
