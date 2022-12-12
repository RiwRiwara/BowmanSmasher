package main;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    public int curXTo, curYTo, curMapTo;
    public String from = "";
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
       int col = 0;
       int row = 0;
       while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
           eventRect[map][col][row] = new EventRect();
           eventRect[map][col][row].x = 23;
           eventRect[map][col][row].y = 23;
           eventRect[map][col][row].width = 2;
           eventRect[map][col][row].height = 2;
           eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
           eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
           col++;
           if(col == gp.maxWorldCol) {
               col = 0;
               row++;

               if(row == gp.maxWorldRow) {
                   row = 0;
                   map++;
               }
           }
       }
    }

    public void  checkEvent() {
        //Exit home
        if(hit(2,6,9, "any")){
            from = "teleport";
            curMapTo = 1;
            curXTo = 7;
            curYTo = 32;
            gp.ui.currentDialogue = "Exit your home?";
            gp.playSE(2);
            gp.gameState = gp.choiceState;
        }
        //Enter home
        if(hit(1,7,31, "any")){
            from = "teleport";
            curMapTo = 2;
            curXTo = 6;
            curYTo = 8;
            gp.ui.currentDialogue = "Enter your home?";
            gp.playSE(2);
            gp.gameState = gp.choiceState;

        }

        //teleport to Cave
        if(hit(1,32,9, "any")){
            mapWarp(0, 5, 37);
        }
    }


    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void damagePit(int map, int col,int row,int gameState){
        eventRect[map][col][row].eventDone = true;
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Fall into pit";
        gp.player.life -=1;
    }

    public void mapWarp(int toMap, int x, int y) {
        gp.currentMap = toMap;
        gp.player.worldX = gp.tileSize * x;
        gp.player.worldY = gp.tileSize * y;
    }

}
