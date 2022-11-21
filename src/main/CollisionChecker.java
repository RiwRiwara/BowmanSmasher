// TODO: 21/11/2565 Fix  
package main;

import entity.Entity;

public class CollisionChecker
{
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }


    public void checkTile(Entity entity) {
        int tileNum1, tileNum2, entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        this.gp.getClass(); int entityLeftCol = entityLeftWorldX / 48;
        this.gp.getClass(); int entityRightCol = entityRightWorldX / 48;
        this.gp.getClass(); int entityTopRow = entityTopWorldY / 48;
        this.gp.getClass(); int entityBottomRow = entityBottomWorldY / 48;

        String str;

        switch ((str = entity.direction).hashCode()) { case 3739: if (!str.equals("up"))
            break;
            this.gp.getClass(); entityTopRow = (entityTopWorldY - entity.speed) / 48;
            tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
                entity.collisionOn = true;  break;
            case 3089570:
                if (!str.equals("down"))
                    break;
                this.gp.getClass(); entityBottomRow = (entityBottomWorldY + entity.speed) / 48;
                tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
                    entity.collisionOn = true;  break;
            case 3317767:
                if (!str.equals("left"))
                    break;
                this.gp.getClass(); entityLeftCol = (entityLeftWorldX - entity.speed) / 48;
                tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
                    entity.collisionOn = true;  break;
            case 108511772:
                if (!str.equals("right"))
                    break;
                this.gp.getClass(); entityRightCol = (entityRightWorldX + entity.speed) / 48;
                tileNum1 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision) {
                    entity.collisionOn = true;
                }
                break; }

    }

}
