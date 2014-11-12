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
 * Joseph Hanson
 * William Henry
 * Jessica Dorismond
 * ChunWei Chang
 * Vicky Zheng
 */

public class Game {

	Tile[][] Board;
	Move _move;
	Menu MU;
	Player _player1;
	Player _player2;
	
	JFrame TF;
	JPanel MN;
	ArrayList<JPanel> THA = new ArrayList<JPanel>();
	
	
	int x[]=new int[40];
	int y[]=new int[40];
	int current;

	public Game() {
		// Creates a new 2d storage array for tiles [x][y]
		Board = new Tile[8][5];
		_move = new Move();
		_player1 = new Player("P1");
		_player2 = new Player("P2");
		_move.setCurrentPlayer(_player1);
		InitGraphics();
	}

	public void InitGraphics() {
		// create main frame and allow a full exit on close

		TF = new JFrame();
		TF.setResizable(false);
		TF.setSize(1100, 600);
		TF.setLayout(null);
		TF.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// Board JPanel();
		MN = new JPanel();
		MN.setSize(800, 600);
		MN.setBackground(java.awt.Color.BLACK);
		MN.setLayout(null);
		TF.add(MN);
		MN.setBounds(0, 0, 800, 600);

		// Add Menu Object
		MU = new Menu(_player1, _player2, _move, Board);
		TF.add(MU);
		MU.setBounds(800, 0, 300, 600);

		// Adding columns in t
		for (int x = 0; x < 8; x++) {
			TileColumn TH = new TileColumn();
			TH.setLayout(new GridLayout(5,1));
			for(int i = 0; i < 5; i++)
			{
				JPanel temp = new JPanel();
				temp.setSize(100,100);
				temp.setOpaque(false);
				TH.add(temp);
			}
			THA.add(TH);//keeps track of each tile holder for changing the tiles later on placement
			MN.add(TH);//adds the tile holder to the GUI

			if (x % 2 == 0) {//used to create the shifted look of the board
				TH.setBounds((x * 100) - (x * 10), 0, 100, 500);
			} else {
				TH.setBounds((x * 100) - (x * 10), 50, 100, 500);
			}

			for (int y = 0; y < 5; y++) {
				int[] blank = new int[] { 0, 0, 0, 0, 0, 0 };

				Tile Cur = new Tile(x, y, blank, false); 
				Board[x][y] = Cur;
				JPanel SPA = (JPanel)TH.getComponent(y);
				SPA.add(Board[x][y]);
				Board[x][y].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						HandleClicks(e);
					}
				});

			}
		}
		TF.setVisible(true);

	}

	public void HandleClicks(java.awt.event.MouseEvent evt) {//handles what happens when a tile is clicked
		Tile T = (Tile) evt.getComponent();
		//System.out.println(T.LocX + ", " + T.LocY + " tile clicked.");

		int state = _move.getMoves();
		
		//checks what state move is in to determine what action is being taken
		switch (state) {
		
			//tile is clicked without an action being selected
			case 0: // prompt
				JOptionPane.showMessageDialog(null, "Please select an action.");
			break;
			
			//tile is selected as the placement area for a new tile
			case 1: // placeTile (canPlaceTile if(can) Place
				
				//checks if this is the first tile being placed
				if(_move.getFirstTile()==null){
					//makes sure the selected tile is on the border
	                if(T.LocX==0 || T.LocX==7 || T.LocY==0 || T.LocY==4){
	                    x[0]=T.LocX;
	                    y[0]=T.LocY;
	                    
	                
	                    Tile t = _move.getNexttoPlace();
	                    t.setX(T.LocX);
	                    t.setY(T.LocY);
	                    t.addMouseListener(new java.awt.event.MouseAdapter() {
	    					public void mousePressed(MouseEvent e) {
	    						HandleClicks(e);
	    					}
	    				});
	                    current=1;
	                    _move.setFirstTile(t);
	                    Board[T.LocX][T.LocY]= t;
	                    JPanel jp = THA.get(T.LocX);
	                    JPanel Jref = (JPanel)jp.getComponent(T.LocY);
	                    Jref.remove(0);
	                    Jref.add(t);
	                    jp.repaint();
	                    MU.Clear();
	                    _move.setCpActionPoints(_move.getCpActionPoints()-1);
	                    _move.setTilesThisTurn(_move.getTilesThisTurn()+1);
	                    MU.RefreshStats();
	                    _move.reset();
	                
	                }
	                else{
	                    JOptionPane.showMessageDialog(null, "You have to click on a edge.");
	                }
	            }

				else{
					for(int i =0;i<current;i++){
						
						if((Math.abs(x[i]-T.LocX))==0 && (Math.abs(y[i]-T.LocY))==0){
							JOptionPane.showMessageDialog(null, "You can not place here.");
							break;
						}
						else if(((Math.abs(x[i]-T.LocX))<=1) && ((Math.abs(y[i]-T.LocY))<=1)){
							
							if (canPlaceTile(x[i], y[i], T)){
								x[current]=T.LocX;
								y[current]=T.LocY;
								current++;
								Tile t = _move.getNexttoPlace();
								t.setX(T.LocX);
								t.setY(T.LocY);
								t.addMouseListener(new java.awt.event.MouseAdapter() {
									public void mousePressed(MouseEvent e) {
										HandleClicks(e);
									}
								});
							    Board[T.LocX][T.LocY]= t;
			                    JPanel jp = THA.get(T.LocX);
			                    JPanel Jref = (JPanel)jp.getComponent(T.LocY);
			                    Jref.remove(0);
			                    Jref.add(t);
			                    jp.repaint();
			                    MU.Clear();
			                    _move.setCpActionPoints(_move.getCpActionPoints()-1);
			                    _move.setTilesThisTurn(_move.getTilesThisTurn()+1);
			                    MU.RefreshStats();
			                    _move.reset();
			                    break;
							}
						}
						if(current-1==i){
							JOptionPane.showMessageDialog(null, "Yo you can not place here.");
						}
					}
				}
				
				break;

			//Tile is selected as part of an explorers movement
			case 2: // MoveExplorer canMove, move
				
				//checks if this is the first or second tile in the move
				if (_move.getTileClick1()==null){
					_move.setTileClick1(T);
					
					//checks if the player has an explorer on the selected tile to move
					if((_move.getCurrentPlayer()==_player1 && _move.getTileClick1().getP1()==0)||(_move.getCurrentPlayer()==_player2 && _move.getTileClick1().getP2()==0)){
						JOptionPane.showMessageDialog(null, "No explorers to move.");
						_move.reset();
						MU.RefreshStats();
					}
					else
					{
						MU.UpdateInstructions(3);
					}
					//System.out.println("Got first tile at " + T.LocX + "," + T.LocY);
					
				}
				else{
					//System.out.println("Got second tile at " + T.LocX + "," + T.LocY);
					_move.setTileClick2(T);
					int cost = canMoveExplorer();
					//makes sure there is a path from the first to the second tile
					if (cost>0){
						//checks which player is doing the move
						if (_move.getCurrentPlayer().getName()=="P1"){
							//checks that the player has enough action points to make the move and that they have an explorer
							if((_move.getTileClick1().getP1()>0)&&(_move.getCurrentPlayer().getActionPoints()>=cost)){
								 MoveExplorer(cost);
								 
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Not enough Action Points.");
								_move.reset();
								MU.RefreshStats();
							}
						}
						else if (_move.getCurrentPlayer().getName()=="P2"){
							//checks that the player has enough action points to make the move and that they have an explorer
							if((_move.getTileClick1().getP2()>0)&&(_move.getCurrentPlayer().getActionPoints()>=cost)){
								 MoveExplorer(cost);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Not enough Action Points.");
								_move.reset();
								MU.RefreshStats();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Unknown player defined.");
							_move.reset();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Cannot move this explorer.");
						_move.reset();
						MU.RefreshStats();
					}
				}
				
			break;
			
			//the tile is selected as the destination for a pyramid upgrade
			case 3: // PlacePyramid canPlace
				//checks if there is a pyramid on the selected tile or not
				if(T.PM == null) {
					JOptionPane.showMessageDialog(null, "There is no pyramid.");;
					break;
				}
				//checks the value of the pyramid already there
				switch(T.PM.getValue()){
    				case 0:
    					//checks the number of remaining pyramids of the required level
    					if(_move.getPyrimidNumber(0) > 0)
    					{
    						//checks that the player making the placement has the most explorers on the tile
	    					if(_move.getCurrentPlayer().equals(_player1) && T.P1E > T.P2E){
	    						T.PM.setValue(1);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(0, _move.getPyrimidNumber(0)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    						
	    					}
	    					else if (_move.getCurrentPlayer().equals(_player2) && T.P1E < T.P2E){
	    						T.PM.setValue(1);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(0, _move.getPyrimidNumber(0)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
    					}
    					else JOptionPane.showMessageDialog(null, "Not enough Level 1 Pyramids");
    					
    					MU.RefreshStats();
    					break;
    				case 1:
    					if(_move.getPyrimidNumber(1) > 0 )
    					{
	    					if(_move.getCurrentPlayer().equals(_player1) && T.P1E > T.P2E){
	    						T.PM.setValue(2);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(1, _move.getPyrimidNumber(1)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else if (_move.getCurrentPlayer().equals(_player2) && T.P1E < T.P2E){
	    						T.PM.setValue(2);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(1, _move.getPyrimidNumber(1)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
    					}
    					else JOptionPane.showMessageDialog(null, "Not enough Level 2 Pyramids");
    					
    					MU.RefreshStats();
    					break;
    				case 2:
    					if(_move.getPyrimidNumber(2) > 0)
    					{
	    					if(_move.getCurrentPlayer().equals(_player1) && T.P1E > T.P2E){
	    						T.PM.setValue(3);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(2, _move.getPyrimidNumber(2)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else if (_move.getCurrentPlayer().equals(_player2) && T.P1E < T.P2E){
	    						T.PM.setValue(3);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(2, _move.getPyrimidNumber(2)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
    					}
    					else JOptionPane.showMessageDialog(null, "Not enough Level 3 Pyramids");
    					
    					MU.RefreshStats();
    					break;
    				case 3:
    					if(_move.getPyrimidNumber(3) > 0)
    					{
	    					if(_move.getCurrentPlayer().equals(_player1) && T.P1E > T.P2E){
	    						T.PM.setValue(4);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(3, _move.getPyrimidNumber(3)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else if (_move.getCurrentPlayer().equals(_player2) && T.P1E < T.P2E){
	    						T.PM.setValue(4);
	    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
	    						_move.setPyrimidNumber(3, _move.getPyrimidNumber(3)-1);
	    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
	    					}
	    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
    					}
    					else JOptionPane.showMessageDialog(null, "Not enough Level 4 Pyramids");
    					
    					MU.RefreshStats();
    					break;
    				default:
    					MU.RefreshStats();
    					JOptionPane.showMessageDialog(null, "There is no Pyramid here."); //tile is null
    					
    					break;
				}
				_move.reset();
				break;
		}
	}
	
	//logic used to check if a tile placement is allowed at the selected location
	public boolean canPlaceTile(int x, int y, Tile t){
		boolean canPlace=false;
		
		Tile CH = Board[x][y];
		
		int X1= t.LocX;
		int Y1= t.LocY;
		
		int X2= CH.LocX;
		int Y2= CH.LocY;
		
		if(X1==X2){
			if(Y1<Y2){
				//Check Tile 1 Down Path(i=3), Tile 2 Up Path (i=0)
				if(CH.paths[0]>0){
					//allow move if first tile has path, then add total cost
					canPlace=true;
				}
			}
			else if(Y1>Y2){
				//Check Tile 1 Up Path (i=0), Tile 2 Down Path (i=3)
				if(CH.paths[3]>0){
					//allow move if first tile has path, then add total cost
					canPlace=true;
				}
			}
		}
		else if(X1<X2){
			if( ((X1%2==0)&&(Y1>Y2)) || ((X1%2==1)&&(Y1==Y2)) ){
				//Check Tile 1 Up-Right Path (i=1), Tile 2 Down-Left Path(i=4)
				if(CH.paths[4]>0){
					//allow move if first tile has path, then add total cost
					canPlace=true;
				}
				
			}
			else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)) ){
				//Check Tile 1 Down-Right Path (i=2), Tile 2 Up-Left Path (i=5)
				if(CH.paths[5]>0){
					//allow move if first tile has path, then add total cost
					canPlace=true;
				}
			}
		}
		else if(X1>X2){
			if( ((X1%2==0)&&(Y1>Y2)) || ((X1%2==1)&&(Y1==Y2)) ){
				//Check Tile 1 Up-Left Path (i=5), Tile 2 Down-Right Path (i=2)
				if(CH.paths[2]>0){
					//allow move if first tile has path, then add total cost
					canPlace=true;
				}
			}
			else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)) ){
				//Check Tile 1 Down-Left Path (i=4) , Tile 2 Up-Right Path (i=1)
				if(CH.paths[1]>0){
					//allow move if first tile has path, then add total cost
					canPlace=true;
				}
			}
		}

		return canPlace;
	}
	
	public int canMoveExplorer(){
		//This function returns the cost of a move of an explorer for the current player
		//If the value returned is 0, you cannot move an explorer. 
		
		int cost= 0;
		
		Tile T1= _move.getTileClick1();
		Tile T2= _move.getTileClick2();
				
		
		int X1= T1.LocX;
		int Y1= T1.LocY;
		
		int X2= T2.LocX;
		int Y2= T2.LocY;

		if(X1==X2){
			if(Y1<Y2){
				//Check Tile 1 Down Path(i=3), Tile 2 Up Path (i=0)
				if(T1.paths[3]>0){
					//allow move if first tile has path, then add total cost
					cost= T1.paths[3] + T2.paths[0];
				}
			}
			else if(Y1>Y2){
				//Check Tile 1 Up Path (i=0), Tile 2 Down Path (i=3)
				if(T1.paths[0]>0){
					//allow move if first tile has path, then add total cost
					cost= T1.paths[0] + T2.paths[3];
				}
			}
		}
		else if(X1<X2){
			if( ((X1%2==0)&&(Y1>Y2)&&(Y1-Y2==1)) || ((X1%2==1)&&(Y1==Y2)) ){
				//Check Tile 1 Up-Right Path (i=1), Tile 2 Down-Left Path(i=4)
				if(T1.paths[1]>0){
					//allow move if first tile has path, then add total cost
					cost= T1.paths[1] + T2.paths[4];
				}
				
			}
			else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)&&(Y2-Y1==1)) ){
				//Check Tile 1 Down-Right Path (i=2), Tile 2 Up-Left Path (i=5)
				if(T1.paths[2]>0){
					//allow move if first tile has path, then add total cost
					cost= T1.paths[2] + T2.paths[5];
				}
			}
		}
		else if(X1>X2){
			if( ((X1%2==0)&&(Y1>Y2)&&(Y1-Y2==1)) || ((X1%2==1)&&(Y1==Y2)) ){
				//Check Tile 1 Up-Left Path (i=5), Tile 2 Down-Right Path (i=2)
				if(T1.paths[5]>0){
					//allow move if first tile has path, then add total cost
					cost= T1.paths[5] + T2.paths[2];
				}
			}
			else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)&&(Y2-Y1==1)) ){
				//Check Tile 1 Down-Left Path (i=4) , Tile 2 Up-Right Path (i=1)
				if(T1.paths[4]>0){
					//allow move if first tile has path, then add total cost
					cost= T1.paths[4] + T2.paths[1];
				}
			}
		}

		return cost;
	}
	
	//method that makes the explorer move
	public void MoveExplorer(int cost){
		
		if (_move.getCurrentPlayer().getName()=="P1"){
			_move.getTileClick1().setP1(_move.getTileClick1().getP1()-1);
			_move.getTileClick2().setP1(_move.getTileClick2().getP1()+1);
			_move.getCurrentPlayer().setActionPoints(_move.getCurrentPlayer().getActionPoints()-cost);
			_move.reset();
			MU.RefreshStats();
		}
		else if(_move.getCurrentPlayer().getName()=="P2"){
			_move.getTileClick1().setP2(_move.getTileClick1().getP2()-1);
			_move.getTileClick2().setP2(_move.getTileClick2().getP2()+1);
			_move.getCurrentPlayer().setActionPoints(_move.getCurrentPlayer().getActionPoints()-cost);
			_move.reset();
			MU.RefreshStats();
		}
		else{
			JOptionPane.showMessageDialog(null, "Unknown player defined.");
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
