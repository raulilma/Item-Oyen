import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class UI_GameOver extends JFrame
{
	private JButton jbRetry, jbHome;
	private int kills;
	
	private AudioPlayer loseMusic;
	
	public UI_GameOver(Game game)
	{
		 setTitle("Game Over!");
		 setSize(600,600);
		 JLabel background=new JLabel(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\GameOver.png"));
		 kills = game.kills;
		 JLabel totalKills = new JLabel("Total Kills: "+Integer.toString(kills));
		 totalKills.setForeground(Color.WHITE);
		 totalKills.setBounds(265, 120, 100, 100);
		 
		 try {
			 loseMusic = new AudioPlayer(7);
			 loseMusic.play();
		 }
		 catch (Exception e) {System.out.println(e.getMessage());}
		 
		 add(background);
		 background.setLayout(null);
		 jbRetry = new JButton("");
		 jbRetry.setBounds(250, 200, 100, 40);
		 jbRetry.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Retry.png"));
		 
		 jbHome = new JButton("");
		 jbHome.setBounds(250, 250, 100, 40);
		 jbHome.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Menu.png"));
		 this.getContentPane().add(background);
		 jbRetry.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) { 
				  //try { audioPlayer.stop();}
				  //catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
				  dispose();
				  Game game = new Game();
				  game.setVisible(true);
			  } 
		 });
		 
		 jbHome.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) { 
				  //try { audioPlayer.stop();}
				  //catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
				  dispose();
				  UI_Menu menu = new UI_Menu();
				  menu.setVisible(true);
			  } 
		 });
		 background.add(jbRetry);
		 background.add(jbHome);
		 background.add(totalKills);
	     setVisible(true);
		 setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 addWindowListener(new WindowAdapter() {
			 @Override
			 public void windowClosing(WindowEvent e) {
				 int choose = JOptionPane.showConfirmDialog(null,
				 "Do you really want to exit the application ?",
				"Confirm Close", JOptionPane.YES_NO_OPTION,
				 JOptionPane.INFORMATION_MESSAGE);
				 if (choose == JOptionPane.YES_OPTION) {
					 e.getWindow().dispose();
					 System.out.println("close");
				 } else setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			 }
		 });
	 }
}