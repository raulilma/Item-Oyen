import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import javax.sound.sampled.*;
  
public class UI_Menu extends JFrame
{
	private JButton jbExit, jbPlay, jbCredits, mute;
	private boolean muteStat = false;
	private AudioPlayer audioPlayer;
	public UI_Menu()
	{
		try
		{
			 audioPlayer = new AudioPlayer(2);
			 audioPlayer.play();
			 setTitle("Item Oyen Main Menu");
			 setSize(600,600);
			 JLabel background=new JLabel(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\logoGame.png"));
			 add(background);
			 background.setLayout(null);
			 jbPlay = new JButton("");
			 jbCredits = new JButton("");
			 jbExit = new JButton("");
			 mute = new JButton();

			 jbPlay.setBounds(470, 360, 100, 40);
			 jbCredits.setBounds(470, 410, 100, 40);
			 jbExit.setBounds(470, 460, 100, 40);
			 mute.setBounds(500, 510, 40, 40);
			 
			 jbPlay.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Play.png"));
			 jbCredits.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\CreditsButton.png"));
			 jbExit.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Exit.png"));
			 mute.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Mute.png"));
			 this.getContentPane().add(background);
			 jbPlay.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  try { audioPlayer.stop();}
					  catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
					  dispose();
					  Game game = new Game();
					  game.setVisible(true);
				  } 
			 });
			 jbCredits.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  try { audioPlayer.stop();}
					  catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
					  dispose();
					  UI_Credits credit = new UI_Credits();
					  credit.setVisible(true);
				  } 
			 });
			 jbExit.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  int choose = JOptionPane.showConfirmDialog(null,
							"Do you really want to exit Item Oyen?",
							"Confirm Close", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					  if (choose == JOptionPane.YES_OPTION) {
							try { audioPlayer.stop();}
							catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
							dispose();
							System.out.println("Closing Item Oyen");
					  } else setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				  } 
			 });
			 mute.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
						if (!muteStat) 
						{
							try { audioPlayer.pause();}
							catch (Exception e2) {System.out.print("Error in stopping the audio!");}
							mute.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Unmute.png"));
							muteStat = true;
							System.out.println("Non-Activate Audio");
						}
						else
						{
							try { audioPlayer.resumeAudio();}
							catch (Exception e2) {System.out.print(e2.getMessage());}
							mute.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Mute.png"));
							muteStat = false;
							System.out.println("Activate Audio");
						}
				  }
			 });
			 background.add(jbPlay);
			 background.add(jbCredits);
		     background.add(jbExit);
		     background.add(mute);
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
						 try { audioPlayer.stop();}
						 catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
						 e.getWindow().dispose();
						 System.out.println("Closing Item Oyen");
					 } else setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				 }
			 });
		}
		catch (Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	 }
}