package menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ToolBar {
	public static void main(String[] args){
		ToolBarFen fen = new ToolBarFen();
		fen.setVisible(true);
	}
}
class ToolBarFen extends JFrame implements ActionListener{
	private JButton redBut, greenBut;
	private JToolBar maToolBar;
	private JPanel pan;
	public ToolBarFen(){
		setTitle("My Toolbar");setSize(300,150);
		maToolBar = new JToolBar();
		redBut = new JButton("Rouge");
		greenBut = new JButton("Vert");
		pan = new JPanel();
		pan.setBackground(Color.white);
		maToolBar.add(redBut);
		maToolBar.add(greenBut);
		getContentPane().add(pan);
		getContentPane().add(maToolBar);
		redBut.addActionListener(this);
		greenBut.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ev){
		System.out.println(ev.getActionCommand());
		if(ev.getSource()==redBut) pan.getGraphics().setColor(Color.red);
		if(ev.getSource()==greenBut) pan.getGraphics().setColor(Color.green);
	}
}
