package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;




public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        this.tile = new Tile[100];
        gp.getClass(); gp.getClass(); this.mapTileNum = new int[50][50];

        getTileImage();
        loadMap("/maps/cave01.txt");
    }



    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/void.png")));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_top_1.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_4.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_corner_left_top.png")));
            tile[3].collision = true;


            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_left.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_corner_right_top.png")));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_right.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/floor_left.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_2.png")));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/box.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/door.png")));


            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/floor_1.png")));


            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/floor_1.png")));


            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/floor_1.png")));


            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/floor_bot.png")));


            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/floor_right.png")));


            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cave01/wall_right.png")));
            tile[16].collision = true;

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            this.gp.getClass(); this.gp.getClass(); while (col < 50 && row < 50) {

                String line = br.readLine();

                this.gp.getClass(); while (col < 50) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    this.mapTileNum[col][row] = num;
                    col++;
                }
                this.gp.getClass(); if (col == 50) {
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

        this.gp.getClass(); this.gp.getClass(); while (worldCol < 50 && worldRow < 50) {

            int tileNum = this.mapTileNum[worldCol][worldRow];

            this.gp.getClass(); int worldX = worldCol * 48;
            this.gp.getClass(); int worldY = worldRow * 48;
            int screenX = worldX - this.gp.player.worldX + this.gp.player.screenX;
            int screenY = worldY - this.gp.player.worldY + this.gp.player.screenY;

            this.gp.getClass();
            this.gp.getClass();
            this.gp.getClass();
            this.gp.getClass(); if (worldX + 48 > this.gp.player.worldX - this.gp.player.screenX && worldX - 48 < this.gp.player.worldX + this.gp.player.screenX && worldY + 48 > this.gp.player.worldY - this.gp.player.screenY && worldY - 48 < this.gp.player.worldY + this.gp.player.screenY) {

                this.gp.getClass(); this.gp.getClass(); g2.drawImage((this.tile[tileNum]).image, screenX, screenY, 48, 48, null);
            }

            worldCol++;

            this.gp.getClass(); if (worldCol == 50) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
