package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;


public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[200];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/cave02.txt", 0);
        loadMap("/res/maps/forest1.txt", 1);
    }



    public void getTileImage() {
        setUp(0, "/res/tiles/cave02/black.png", true);

        setUp(1, "/res/tiles/cave02/floor_conner1.png", false);
        setUp(2, "/res/tiles/cave02/floor_conner2.png", false);
        setUp(3, "/res/tiles/cave02/floor_conner3.png", false);
        setUp(4, "/res/tiles/cave02/floor_conner4.png", false);
        setUp(5, "/res/tiles/cave02/floor_top.png", false);
        setUp(6, "/res/tiles/cave02/floor_left.png", false);
        setUp(7, "/res/tiles/cave02/floor_bot.png", false);
        setUp(8, "/res/tiles/cave02/floor_right.png", false);
        setUp(9, "/res/tiles/cave02/floor.png", false);
        setUp(10, "/res/tiles/cave02/spiral1.png", false);
        setUp(11, "/res/tiles/cave02/spiral2.png", false);
        setUp(12, "/res/tiles/cave02/circle1.png", false);
        setUp(13, "/res/tiles/cave02/square1.png", false);
        setUp(14, "/res/tiles/cave02/square2.png", false);
        setUp(15, "/res/tiles/cave02/circle2.png", false);
        setUp(16, "/res/tiles/cave02/hole.png", true);
        setUp(17, "/res/tiles/cave02/grass.png", false);
        setUp(18, "/res/tiles/cave02/square3.png", false);
        setUp(19, "/res/tiles/cave02/grey.png", true);
        setUp(20, "/res/tiles/cave02/cave1.png", true);
        setUp(21, "/res/tiles/cave02/cave2.png", true);
        setUp(22, "/res/tiles/cave02/cave3.png", true);
        setUp(23, "/res/tiles/cave02/cave4.png", true);
        setUp(24, "/res/tiles/cave02/wall_top.png", true);
        setUp(25, "/res/tiles/cave02/wall_left.png", true);
        setUp(26, "/res/tiles/cave02/wall_right.png", true);
        setUp(27, "/res/tiles/cave02/wall_bot1.png", true);
        setUp(28, "/res/tiles/cave02/wall_bot2.png", true);
        setUp(29, "/res/tiles/cave02/wall_conner1.png", true);
        setUp(30, "/res/tiles/cave02/wall_conner2.png", true);
        setUp(31, "/res/tiles/cave02/wall_conner3.png", true);
        setUp(32, "/res/tiles/cave02/wall_conner4.png", true);
        setUp(33, "/res/tiles/cave02/wall1.png", true);
        setUp(34, "/res/tiles/cave02/wall2.png", true);
        setUp(35, "/res/tiles/cave02/wall3.png", true);
        setUp(36, "/res/tiles/cave02/wall4.png", true);
        setUp(37, "/res/tiles/cave02/cavetile1.png", true);
        setUp(38, "/res/tiles/cave02/cavetile2.png", true);
        setUp(39, "/res/tiles/cave02/cavetile3.png", true);
        setUp(40, "/res/tiles/cave02/cavetile4.png", true);
        setUp(41, "/res/tiles/cave02/cavetile5.png", true);
        setUp(42, "/res/tiles/cave02/cavetile6.png", true);
        setUp(43, "/res/tiles/cave02/cavetile7.png", true);
        setUp(44, "/res/tiles/cave02/cavetile8.png", true);
        setUp(45, "/res/tiles/cave02/cavetile9.png", true);
        setUp(46, "/res/tiles/cave02/cavetile10.png", true);
        setUp(47, "/res/tiles/cave02/cavetile11.png", true);
        setUp(48, "/res/tiles/cave02/cavetile12.png", true);

        //forest
        setUp(49, "/res/tiles/forest1/grass1.png", false);
        setUp(50, "/res/tiles/forest1/grass2.png", false);
        setUp(51, "/res/tiles/forest1/grass3.png", false);
        setUp(52, "/res/tiles/forest1/tree1.png", true);
        setUp(53, "/res/tiles/forest1/tree2.png", true);
        setUp(54, "/res/tiles/forest1/tree3.png", false);
        setUp(55, "/res/tiles/forest1/walk.png", false);
        setUp(56, "/res/tiles/forest1/floor_conner1.png", false);
        setUp(57, "/res/tiles/forest1/floor_conner2.png", false);
        setUp(58, "/res/tiles/forest1/floor_conner3.png", false);
        setUp(59, "/res/tiles/forest1/floor_conner4.png", false);
        setUp(60, "/res/tiles/forest1/floor_top.png", false);
        setUp(61, "/res/tiles/forest1/floor_left.png", false);
        setUp(62, "/res/tiles/forest1/floor_right.png", false);
        setUp(63, "/res/tiles/forest1/floor_down.png", false);
        setUp(64, "/res/tiles/forest1/floor.png", false);
        setUp(65, "/res/tiles/forest1/stair.png", false);
        setUp(66, "/res/tiles/forest1/green_top.png", true);
        setUp(67, "/res/tiles/forest1/green_left.png", true);
        setUp(68, "/res/tiles/forest1/green_right.png", true);
        setUp(69, "/res/tiles/forest1/green_down.png", true);
        setUp(70, "/res/tiles/forest1/green_conner1.png", true);
        setUp(71, "/res/tiles/forest1/green_conner2.png", true);
        setUp(72, "/res/tiles/forest1/green_conner3.png", true);
        setUp(73, "/res/tiles/forest1/green_conner4.png", true);
        setUp(74, "/res/tiles/forest1/water1.png", true);
        setUp(75, "/res/tiles/forest1/water2.png", true);
        setUp(76, "/res/tiles/forest1/water3.png", true);

    }
    public void setUp(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < 50 && row < 50) {

                String line = br.readLine();

                 while (col < 50) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    this.mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == 50) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception exception) {}
    }



    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

         while (worldCol < 50 && worldRow < 50) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == 50) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
