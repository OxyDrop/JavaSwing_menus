package menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ColorAction {
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		maFen fen = new maFen();
		fen.setOpacity(0.9f);
		fen.setVisible(true);
	}
}
class maFen extends JFrame{
	private MonAction actionRouge, actionJaune;
	private JMenuBar maBarre;
	private JMenu couleur;
	private JButton buttonRouge,buttonJaune;
	public maFen(){
		setTitle("Abstract Action");
		setSize(300,150);
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		//Instanciation
		actionRouge=new MonAction("ROUGE",Color.red);
		actionJaune=new MonAction("JAUNE",Color.yellow);
		maBarre=new JMenuBar();
		couleur=new JMenu("Couleur");
		buttonRouge = new JButton(actionRouge.getNom());
		buttonJaune = new JButton((String)actionJaune.getValue(Action.NAME));
		//Ajouts
		setJMenuBar(maBarre);
		maBarre.add(couleur);
		couleur.add(actionRouge);
		couleur.add(actionJaune);
		contenu.add(buttonRouge);
		contenu.add(buttonJaune);
		buttonRouge.addActionListener(actionRouge);
		buttonJaune.addActionListener(actionJaune);	
	}
}
class MonAction extends AbstractAction{
	private Color couleur;
	private String name;
	public MonAction(String nom, Color couleur){
		super(nom);
		name=nom;
		this.couleur=couleur;
	}
	public void actionPerformed(ActionEvent ev){
		System.out.println("chaine de commande :"+ev.getActionCommand());
	}
	public String getNom(){return name;}
}