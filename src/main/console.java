package main;

import entity.Entity;
import object.*;

import java.lang.reflect.InvocationTargetException;

public class console extends javax.swing.JFrame {
    GamePanel gp;
    public console(GamePanel gp) {
        this.gp = gp;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Console");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jButton1.setText("Go");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Command");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String[] command = jTextField1.getText().split(" ");
            switch (command[0]){
                case "tp":
                    int x = Integer.parseInt(command[1]);
                    int y = Integer.parseInt(command[2]);
                    gp.player.worldX = x * gp.tileSize;
                    gp.player.worldY = (y+1) * gp.tileSize;
                    jLabel1.setText("Teleport");
                    break;
                case "hp":
                    gp.player.life += Integer.parseInt(command[1]);
                    jLabel1.setText("Add life");
                    break;
                case "mhp":
                    gp.player.maxLife = Integer.parseInt(command[1]);
                    gp.player.life = gp.player.maxLife;
                    jLabel1.setText("Max life");
                    break;
                case "give":
                    giveItem(Integer.parseInt(command[1]));
                    break;
                case "map":
                    if(Integer.parseInt(command[1]) == 2){
                        gp.player.worldX = 8*gp.tileSize;
                        gp.player.worldY = 4*gp.tileSize;
                    }
                    gp.currentMap = Integer.parseInt(command[1]);
                    break;
            }
        }catch (Exception e){
            jLabel1.setText("Command Error");
        }


    }

    public void giveItem(int itemID) {
        Entity[] allItem = new Entity[100];
        allItem[0] = new OBJ_KEY(gp);
        allItem[1] = new OBJ_redPotion(gp);
        allItem[2] = new OBJ_HEART(gp);
        allItem[3] = new OBJ_Spear(gp);
        gp.player.inventory.add(allItem[itemID]);
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
}
