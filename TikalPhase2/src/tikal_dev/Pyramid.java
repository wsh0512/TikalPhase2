package tikal_dev;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pyramid extends JPanel{
	
	
	int x;
	int y;
	int currentValue;
	JLabel IMG;

	public Pyramid(int X, int Y, int V){
		x=0;
		y=0;
		currentValue=V;
	
		IMG = new JLabel();
		IMG.setText("");
		IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid.png")));
		
		this.setSize(50, 50);
		this.add(IMG);
		this.setOpaque(false);
		this.setSize(IMG.getIcon().getIconHeight(),IMG.getIcon().getIconWidth());
		this.setBounds(0, 0, 45, 45);
	
		JLabel Value = new JLabel();
		Value.setText(String.valueOf(V));
		//this.add(Value);
		Value.setBounds(15, 15, 20, 20);
		
	}
	
	
	public void setValue(int i){
		// Sets value of the current pyramid and updates the image accordingly
		
		switch(i){
		
		case 1:
			IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_1.png")));
			break;
		case 2:
			IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_2.png")));
			break;
		case 3:
			IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_3.png")));
			break;
		case 4:
			IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_4.png")));
			break;
		default:
			JOptionPane.showMessageDialog(null, "Cant set a pyramid of this value");
			break;
		}
		
		currentValue=i;
	}
	
	public int getValue(){
		return currentValue;
	}
}