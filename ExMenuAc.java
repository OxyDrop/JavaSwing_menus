package menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ExMenuAc {
	public static void main(String[] args){
		FenMenu fen = new FenMenu();
		fen.setVisible(true);
	}
}
class FenMenu extends JFrame implements ActionListener{
	static public final String[] nomCouleurs={"rouge","vert","noir","bleu","blanc"};
	static public final Color[] couleurs = {Color.red, Color.green, Color.black, Color.blue,Color.white};
	static public final String[] nomIcones={"rouge.gif","vert.gif","noir.gif","bleu.gif","blanc.gif"};
	private JMenuBar barreMenu;
	private JMenu couleur, dimensions, formes;
	private JMenuItem largeur, hauteur;
	private JCheckBoxMenuItem rectangle, ovale;
	private JPopupMenu couleurSurg;
	private ActionCouleur[] actions; 
	private JToolBar barreCouleurs;
	private Panneau pan;
	public FenMenu(){
		setTitle("Figures avec Menus et barre d'outil");
		setSize(450,200);
		Container contenu = getContentPane();
		//INITIALISATION 
		barreMenu = new JMenuBar();
		couleur = new JMenu("Couleurs"); 
		dimensions = new JMenu("Dimensions"); 
		formes = new JMenu("Formes");
		rectangle = new JCheckBoxMenuItem("rectangle"); 
		ovale = new JCheckBoxMenuItem("ovale");
		largeur = new JMenuItem("Largeur"); 
		hauteur = new JMenuItem("Hauteur");
		couleurSurg = new JPopupMenu();
		barreCouleurs = new JToolBar();
		int nbCouleurs = nomCouleurs.length;
		actions = new ActionCouleur[nbCouleurs];
		pan = new Panneau();
		pan.setBackground(Color.cyan);
		//AJOUTS
		for(int i = 0; i<nbCouleurs;i++){
			actions[i] = new ActionCouleur(nomCouleurs[i],couleurs[i],nomIcones[i],pan);
			//Chaque case contient un objet ActionCouleur contenant un nom de couleur, une couleur, une icone
		}
		for(int i=0;i<nomCouleurs.length;i++){
			couleur.add(actions[i]); //Ajout de JMenuItems au menu couleur
			couleurSurg.add(actions[i]); //Ajout de couleurs(JMenuItems) au menu surgissant
			JButton boutonCourant = barreCouleurs.add(actions[i]); //Ajout d'un bouton à la barre d'outil
			boutonCourant.setText(null); //sans texte
			boutonCourant.setToolTipText((String)actions[i].getValue(Action.SHORT_DESCRIPTION)); //Avec bulle d'aide

		}
		contenu.add(barreCouleurs,"North");
		contenu.add(pan);
		setJMenuBar(barreMenu); //Ajout barre de menu
		couleur.setMnemonic('C'); //Raccourcis clavier
		formes.setMnemonic('F');
		dimensions.setMnemonic('D');
		barreMenu.add(couleur);
		barreMenu.add(formes);
		barreMenu.add(dimensions);
		formes.add(rectangle);
		formes.add(ovale);
		dimensions.add(largeur);
		dimensions.add(hauteur);
		rectangle.addActionListener(this);
		ovale.addActionListener(this);
		largeur.addActionListener(this);
		hauteur.addActionListener(this);
		addMouseListener(new MouseAdapter(){ //Lors d'un clic de souris, une pop-up est generée
			public void mouseReleased(MouseEvent e){
				if(e.isPopupTrigger()) couleurSurg.show(e.getComponent(),e.getX(),e.getY());
			}
		});
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(0);
			}
		});
	}	
	public void actionPerformed(ActionEvent ev){
		Object source = ev.getSource();
		if(source==largeur){
			String ch = JOptionPane.showInputDialog(this,"Largeur"); //Ouverture d'une boite de dialogue avec champ de texte
			pan.setLargeur(Integer.parseInt(ch)); //copie de l'entrée 
		}
		if(source==hauteur){
			String ch = JOptionPane.showInputDialog(this,"Hauteur");
			pan.setHauteur(Integer.parseInt(ch));
		}
		if(source==ovale) pan.setOvale(ovale.isSelected()); //Option selectionnée
		if(source==rectangle) pan.setRectangle(rectangle.isSelected());
		pan.repaint(); //Redessiner, appel à paintComponent
	}
}
class Panneau extends JPanel{
	private boolean rectangle = false, ovale = false;
	private int largeur=50,hauteur=50;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(ovale) g.drawOval(10,10,10+largeur,10+hauteur);
		if(rectangle) g.drawRect(10,10,10+largeur,10+hauteur);
	}
	public void setRectangle(boolean b){rectangle=b;}
	public void setOvale(boolean b){ovale=b;}
	public void setLargeur(int l){largeur=l;}
	public void setHauteur(int h){hauteur=h;}
	
}
class ActionCouleur extends AbstractAction{
	private Color couleur;
	private Panneau pan;
	static ActionCouleur actionInactive; //Action inactive courante
	public ActionCouleur(String nom, Color couleur, String nomIcone, Panneau pan){
		putValue(Action.NAME,nom);
		putValue(Action.SMALL_ICON,new ImageIcon(getClass().getResource(nomIcone))); //Instruction à retenir
		putValue(Action.SHORT_DESCRIPTION,"Fond "+nom);
		this.couleur=couleur;
		this.pan=pan;
	}
	public void actionPerformed(ActionEvent ev){
		pan.setBackground(couleur);
		pan.repaint();
		setEnabled(false); //Chaque fois qu'une couleur est selectionnée, l'action correspondante est desactivée
		if(actionInactive!=null) actionInactive.setEnabled(true); //L'action précedente est de nouveau selectionnable
		actionInactive=this; // l'action précedente est enregistrée
	}
}
