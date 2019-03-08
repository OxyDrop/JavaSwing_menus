package menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menu {
	public static void main(String[] args){
		MaFen fen = new MaFen();
		fen.setVisible(true);
	}
}
class MaFen extends JFrame implements ActionListener, ItemListener{
	private JMenuBar maBarre;
	private JMenu Couleur, Formes;
	private JRadioButtonMenuItem rouge, vert;
	private JCheckBoxMenuItem rectangle, ovale;
	MaFen(){
		setTitle("Exemple de menu");
		setSize(300,150);
		maBarre = new JMenuBar();
		setJMenuBar(maBarre);
		Couleur = new JMenu("Couleur");
		Formes = new JMenu("Dimensions");
		rouge = new JRadioButtonMenuItem("Rouge");
		vert = new JRadioButtonMenuItem("Vert");
		ovale = new JCheckBoxMenuItem("Ovale");
		rectangle = new JCheckBoxMenuItem("Rectangle");
		maBarre.add(Couleur);
		maBarre.add(Formes);
		Couleur.add(rouge);
		Couleur.add(vert);
		Formes.add(ovale);
		Formes.add(rectangle);
		rouge.addActionListener(this);
		vert.addActionListener(this);
		ovale.addActionListener(this);
		rectangle.addActionListener(this);
		rouge.addItemListener(this);
		vert.addItemListener(this);
		ovale.addItemListener(this);
		rectangle.addItemListener(this);
	}
	public void actionPerformed(ActionEvent ev){
		System.out.println("Action option "+ev.getActionCommand());
	}
	public void itemStateChanged(ItemEvent ev){
		
	}
}
