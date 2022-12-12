package main;

import javax.swing.JFrame;
import java.awt.*;

public class Main
{
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setTitle("Bowman Smasher");
//        window.setUndecorated(true);
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/res/player/boman_down1.png")));
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}