package tikal_dev;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * 
 * @authors
 * Yuhui Wu
 * William Henry
 * Jessica Dorismond
 * ChunWei Chang
 * Vicky Zheng
 */

public class Game {

	TileData[][] BoardData;
	Move _move;
	Menu MU;
	GUI _GUI;
	Player _player1;
	Player _player2;
	
	JFrame TF;
	JPanel MN;
	ArrayList<JPanel> THA = new ArrayList<JPanel>();
	Player[] _player;

	public Game(String args[]) 
	{
		// Creates a new 2d storage array for tile data [x][y]
		BoardData = new TileData[8][5];
		InitBoardData();
		_move = new Move();
		_player = new Player[args.length];
		for(int i = 0; i < args.length; i++){
			_player[i] = new Player(args[i],i);
		}
		_move.setCurrentPlayer(_player[0]);
		MU = new Menu(_player, _move, BoardData);
		_GUI = new GUI(MU , BoardData , _move, _player);
		}

	public void InitBoardData() 
	{

		// Adding columns in t
		for (int x = 0; x < 8; x++) 
		{
			
			for(int y = 0; y < 5; y++)
			{
				int[] none = new int[] {0,0,0,0,0,0};
				TileData unplaced = new TileData(x , y , none , false , true);
				BoardData[x][y] = unplaced;
			}

		}
	}

	
		
	public static void main(final String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game(args);
			}
		});
	}
}
