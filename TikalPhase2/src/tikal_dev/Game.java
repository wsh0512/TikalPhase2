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
	

	public Game() 
	{
		// Creates a new 2d storage array for tile data [x][y]
		BoardData = new TileData[8][];
		InitBoardData();
		_move = new Move();
		_player1 = new Player("P1");
		_player2 = new Player("P2");
		MU = new Menu(_player1, _player2, _move);
		_GUI = new GUI(MU , BoardData , _move);
		_move.setCurrentPlayer(_player1);
	}

	public void InitBoardData() 
	{

		// Adding columns in t
		for (int x = 0; x < 8; x++) 
		{
			if(x%2 == 0)
			{
				TileData[] col = new TileData[5];
				for(int y = 0; y < 5; y++)
				{
					int[] none = new int[] {0,0,0,0,0,0};
					TileData unplaced = new TileData(x , y , none , false , true);
					col[y] = unplaced;
				}
				BoardData[x] = col;
			}
			else
			{
				TileData[] col = new TileData[6];
				for(int y = 0; y < 6; y++)
				{
					int[] none = new int[] {0,0,0,0,0,0};
					TileData unplaced = new TileData(x , y , none , false , true);
					col[y] = unplaced;
				}
				BoardData[x] = col;
			}

		}
	}

	
		
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	}
}
