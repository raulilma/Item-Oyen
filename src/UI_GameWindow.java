import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UI_GameWindow {
	private JButton mainMenu, jbPause, backToGame, mute;
	private JFrame frame;
	private AudioPlayer audioPlayer;
	
	private boolean muteStat = false;

	public UI_GameWindow(int lebar, int tinggi, String judul, Game game)
	{
		try
		{
			audioPlayer = new AudioPlayer(1);
			audioPlayer.play();
			frame = new JFrame(judul);
			frame.setPreferredSize(new Dimension(lebar, tinggi));
			frame.setMaximumSize(new Dimension(lebar, tinggi));
			frame.setMinimumSize(new Dimension(lebar, tinggi));

			mainMenu = new JButton();
			jbPause = new JButton();
			backToGame = new JButton();
			mute = new JButton();
			
			mainMenu.setBounds(440, 250, 100, 40);
			jbPause.setBounds(870, 10, 100, 40);
			mute.setBounds(470, 350, 40, 40);
			backToGame.setBounds(440, 300, 100, 40);

			backToGame.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Back.png"));
			mainMenu.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Menu.png"));
			jbPause.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\pause.png"));
			mute.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Mute.png"));
			
			mainMenu.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  int choose = JOptionPane.showConfirmDialog(null,
								 "Leave the game and go to the main menu?",
								 "Yes", JOptionPane.YES_NO_OPTION,
								 JOptionPane.INFORMATION_MESSAGE);
					  if (choose == JOptionPane.YES_OPTION) {
						  try {audioPlayer.stop();}
						  catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
						  frame.dispose();
						  UI_Menu menu = new UI_Menu();
						  menu.setVisible(true);
					  } else frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				  } 
			 });
			
			jbPause.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  mainMenu.setVisible(true);
					  backToGame.setVisible(true);
					  mute.setVisible(true);
					  game.pause();
				  } 
			 });
			
			backToGame.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  mainMenu.setVisible(false);
					  backToGame.setVisible(false);
					  mute.setVisible(false);
					  game.start();
				  } 
			 });
			mute.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
						if (!muteStat)
						{
							try {audioPlayer.stop();}
							catch (Exception e2) {System.out.print("Error in stopping the audio!");}
							mute.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Unmute.png"));
							muteStat = true;
							System.out.println("Non-Activate Audio");
						}
						else
						{
							try {audioPlayer = new AudioPlayer(1); audioPlayer.play();}
							catch (Exception e2) {System.out.print(e2.getMessage());}
							mute.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Mute.png"));
							muteStat = false;
							System.out.println("Activate Audio");
						}
				  }
			});
			
			mainMenu.setVisible(false);
			backToGame.setVisible(false);
			mute.setVisible(false);
			frame.add(mainMenu);
			frame.add(backToGame);
			frame.add(jbPause);
			frame.add(mute);
			frame.add(game);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			frame.addWindowListener(new WindowAdapter() {
				 @Override
				 public void windowClosing(WindowEvent e) {
					 int choose = JOptionPane.showConfirmDialog(null,
					 "Do you really want to exit Item Oyen?",
					 "Confirm Close", JOptionPane.YES_NO_OPTION,
					 JOptionPane.INFORMATION_MESSAGE);
					 if (choose == JOptionPane.YES_OPTION) {
						 try { audioPlayer.stop();}
						 catch (Exception e2) {System.out.print("Error in stopping the bgm!");}
						 e.getWindow().dispose();
						 System.out.println("Closing Item Oyen");
					 } else frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				 }
			 });
		}
		catch (Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	public void dispose()
	{
		try {audioPlayer.stop();}
		catch (Exception e) {System.out.print("Error in stopping the bgm!");}
		frame.setVisible(false);
	}
}
