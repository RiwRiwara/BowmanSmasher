/*    */ package main;
/*    */ 
/*    */ import java.net.URL;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ import javax.sound.sampled.AudioSystem;
/*    */ import javax.sound.sampled.Clip;
/*    */ 
/*    */ 
/*    */ public class Sound
/*    */ {
/*    */   Clip clip;
/* 12 */   URL[] soundURL = new URL[30];
/*    */ 
/*    */   
/*    */   public Sound() {
/* 16 */     this.soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
/* 17 */     this.soundURL[1] = getClass().getResource("/sound/coin.wav");
/* 18 */     this.soundURL[2] = getClass().getResource("/sound/powerup.wav");
/* 19 */     this.soundURL[3] = getClass().getResource("/sound/unlock.wav");
/* 20 */     this.soundURL[4] = getClass().getResource("/sound/fanfare.wav");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFile(int i) {
/*    */     try {
/* 26 */       AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundURL[i]);
/* 27 */       this.clip = AudioSystem.getClip();
/* 28 */       this.clip.open(ais);
/*    */     }
/* 30 */     catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public void play() {
/* 35 */     this.clip.start();
/*    */   }
/*    */   
/*    */   public void loop() {
/* 39 */     this.clip.loop(-1);
/*    */   }
/*    */   
/*    */   public void stop() {
/* 43 */     this.clip.stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\Sound.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */