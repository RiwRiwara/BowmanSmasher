package main;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
;
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

       int col = 0;
       int row = 0;
       while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
           eventRect[col][row] = new EventRect();
           eventRect[col][row].x = 23;
           eventRect[col][row].y = 23;
           eventRect[col][row].width = 2;
           eventRect[col][row].height = 2;
           eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
           eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
           col++;
           if(col == gp.maxWorldCol) {
               col = 0;
               row++;
           }
       }
    }

    public void  checkEvent() {

    }


    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row])) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void damagePit(int col,int row,int gameState){
        eventRect[col][row].eventDone = true;
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Fall into pit";
        gp.player.life -=1;

    }

    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleporting...";
        gp.player.worldX = gp.tileSize * 4;
        gp.player.worldY = gp.tileSize * 40;

    }
}
