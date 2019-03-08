package menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Surgissant {
	public static void main(String[] args){
		Mafen fen = new Mafen();
		fen.setVisible(true);
	}
}
class Mafen extends JFrame implements ActionListener{
	private JPopupMenu couleur;
	private JMenuItem rouge, vert;
	public Mafen(){
		setTitle("Exemple de menu surgissant");
		setSize(300,150);
		couleur = new JPopupMenu();
		rouge = new JMenuItem("rouge");
		vert = new JMenuItem("vert");
		couleur.add(rouge);
		couleur.add(vert);
		vert.addActionListener(this);
		rouge.addActionListener(this);
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent ev){
				if(ev.isPopupTrigger()) couleur.show(ev.getComponent(),ev.getX(),ev.getY());
			}
		});
	}
	public void actionPerformed(ActionEvent ev){
		System.out.println("Action option "+ev.getActionCommand());
	}
}
