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


public class UI_Credits extends JFrame
{
	private JButton jbBack;
	public UI_Credits()
	{
		 setTitle("Item Oyen Credits");
		 setSize(600,600);
		 JLabel background=new JLabel(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Credits.png"));
		 add(background);
		 background.setLayout(null);
		 jbBack = new JButton("");
		 jbBack.setBounds(470, 15, 100, 40);
		 jbBack.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\Item Oyen\\res\\Back.png"));
		 this.getContentPane().add(background);
		 jbBack.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  dispose();
				  UI_Menu menu = new UI_Menu();
				  menu.setVisible(true);
			  } 
		 });
		 background.add(jbBack);
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