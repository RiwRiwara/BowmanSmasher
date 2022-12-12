package main;

import entity.Entity;
import entity.NPC_Dad;
import monster.Goblin;
import monster.Skeleton;
import monster.Slime;
import monster.Zombie;
import object.*;

import tile_interactive.IT_House;
import tile_interactive.InteractiveTile;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        setObjMapHome();
        setObjMapForest();
        setObjMapCave();
    }
    public void setItem(){
        setItemMapHome();
        setItemMapForest();
        setItemMapCave();

    }
    public void setNPC(){
        Entity dad = new NPC_Dad(gp);
        dad.worldX =  gp.tileSize * 3;
        dad.worldY = gp.tileSize * 3;
        gp.npc[2][0] = dad;

    }
    public void setMonster() {
        setMonsterHome();
        setMonsterForest();
        setMonsterCave();
    }

    public void setInteractiveTile() {
        gp.iTile[1][0] = new IT_House(gp);
        gp.iTile[1][0].worldX = 7 * gp.tileSize;
        gp.iTile[1][0].worldY = 31 * gp.tileSize;
    }

    public void setObjMapCave() {
        int i  = 0;
        gp.obj[0][i] = new OBJ_DOOR(gp);
        gp.obj[0][i].worldX = 28  * gp.tileSize;
        gp.obj[0][i].worldY = 47 * gp.tileSize;
        i++;

    }
    public void setObjMapHome() {
        gp.obj[2][0] = new OBJ_destGreen(gp);
        gp.obj[2][0].worldX = 6 * gp.tileSize;
        gp.obj[2][0].worldY = 9 * gp.tileSize;

    }
    public void setObjMapForest() {
        int i = 0;
        gp.obj[1][i] = new OBJ_destGreen(gp);
        gp.obj[1][i].worldX = 32 * gp.tileSize;
        gp.obj[1][i].worldY = 9 * gp.tileSize;
        i++;


    }

    public void setItemMapHome() {

    }
    public void setItemMapCave() {
        int i = 0;
        gp.item[0][i] = new OBJ_KEY(gp);
        gp.item[0][i].worldX = 23 * gp.tileSize;
        gp.item[0][i].worldY = 40 * gp.tileSize;
        i++;
        gp.item[0][i] = new OBJ_redPotion(gp);
        gp.item[0][i].worldX = 39 * gp.tileSize;
        gp.item[0][i].worldY = 43 * gp.tileSize;
    }
    public void setItemMapForest() {

    }

    public void setMonsterHome() {

    }
    public void setMonsterForest() {

    }
    public void setMonsterCave() {
        int i = 0;
        for (i = 0; i < 4; i++) {
            gp.monster[0][i] = new Zombie(gp, 36+i, 42);
        }
        for (i = 6; i < 8; i++) {
            gp.monster[0][i] = new Skeleton(gp, 33+i, 32+i%4);
        }
        gp.monster[0][i] = new Slime(gp, 4, 40);
        i++;
        gp.monster[0][i] = new Goblin(gp, 22, 40);
    }


}
