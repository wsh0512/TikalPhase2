package tikal_dev;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI 
{
	Tile[][] board;
	Tile[] boardColumn;
	TileData[][] _data;
	
	JFrame TF;
	JPanel GB;
	Menu _menu;
	Move _move;
	
	int x[]=new int[40];
	int y[]=new int[40];
	int current;
	
	public GUI(Menu MU , TileData[][] data , Move move)
	{
		board = new Tile[8][];
		_data = data;
		_menu = MU;
		_move = move;
		Draw();
		
	}
	
	public void Draw()
	{
		// create main frame and allow a full exit on close

		TF = new JFrame();
		TF.setResizable(false);
		TF.setSize(1100, 800);
		TF.setLayout(null);
		TF.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// Board JPanel();
		GB = new JPanel();
		GB.setSize(800, 600);	
		GB.setBackground(java.awt.Color.BLACK);
		GB.setLayout(null);
		TF.add(GB);
		GB.setBounds(0, 0, 800, 800);
		
		// Add Menu Object
		TF.add(_menu);
		_menu.setBounds(800, 100, 300, 600);
		
		// Adding columns in t
		for (int x = 0; x < 8; x++) 
		{
			TileColumn TH = new TileColumn();
			
			if(x%2 == 0)
			{
				TH.setLayout(new BoxLayout(TH, BoxLayout.Y_AXIS));
				TH.setBounds((x * 100) - (x * 10), 100, 100, 600);
				boardColumn = new Tile[5];
				for(int y = 0; y < 5; y++)
				{
					
					JPanel temp = new JPanel();
					temp.setSize(100,100);
					temp.setOpaque(false);
					TH.add(temp);
					
					Tile Cur = new Tile(_data[x][y]);
					Cur.setMinimumSize(new Dimension(100, 100));
					boardColumn[y] = Cur;
					JPanel SPA = (JPanel)TH.getComponent(y);
					SPA.add(boardColumn[y]);
					boardColumn[y].addMouseListener(new java.awt.event.MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							HandleClicks(e);
						}
					});
				}
				
				board[x] = boardColumn;
				GB.add(TH);//adds the tile holder to the GUI
				
			}
			else
			{
				TH.setSize(800, 100);
				TH.setLayout(new BoxLayout(TH, BoxLayout.Y_AXIS));
				TH.setBounds((x * 100) - (x * 10), 50, 100, 700);
				boardColumn = new Tile[6];
				for(int y = 0; y < 6; y++)
				{
					JPanel temp = new JPanel();
					temp.setSize(100,100);
					temp.setOpaque(false);
					TH.add(temp);
					
					Tile Cur = new Tile(_data[x][y]);
					Cur.setMinimumSize(new Dimension(100, 100));
					boardColumn[y] = Cur;
					JPanel SPA = (JPanel)TH.getComponent(y);
					SPA.add(boardColumn[y]);
					boardColumn[y].addMouseListener(new java.awt.event.MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							HandleClicks(e);
						}
					});
				}
				
				board[x] = boardColumn;
				GB.add(TH);//adds the tile holder to the GUI
	
			}
			
			
		}
		TF.setVisible(true);
	}
	public void HandleClicks(java.awt.event.MouseEvent evt) //handles what happens when a tile is clicked
	{	
		Tile T = (Tile) evt.getComponent();
		
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
			                if(T.tileData.LocX==0 || T.tileData.LocX==7 || T.tileData.LocY==0 || T.tileData.LocY==4)
			                {
			                    x[0]=T.tileData.LocX;
			                    y[0]=T.tileData.LocY;
			                    
			                    
			                    TileData d = _move.getNexttoPlace().getTileData();
			                    d.SetX(T.tileData.LocX);
			                    d.SetY(T.tileData.LocY);
			                    T.setTileData(d);
			                    
			                    current=1;
			                    _move.setFirstTile(T);
			                    GB.repaint();
			                    _menu.Clear();
			                    _move.setCpActionPoints(_move.getCpActionPoints()-1);
			                    _move.setTilesThisTurn(_move.getTilesThisTurn()+1);
			                    _menu.RefreshStats();
			                    _move.reset();
			                
			                }
			                else{
			                    JOptionPane.showMessageDialog(null, "You have to click on a edge.");
			                }
			            }

						else{
							for(int i =0;i<current;i++){
								
								if((Math.abs(x[i]-T.tileData.LocX))==0 && (Math.abs(y[i]-T.tileData.LocY))==0){
									JOptionPane.showMessageDialog(null, "You can not place here.");
									break;
								}
								else if(((Math.abs(x[i]-T.tileData.LocX))<=1) && ((Math.abs(y[i]-T.tileData.LocY))<=1)){
									
									if (canPlaceTile(x[i], y[i], T)){
										x[current]=T.tileData.LocX;
										y[current]=T.tileData.LocY;
										current++;
										
										TileData d = _move.getNexttoPlace().getTileData();
					                    d.SetX(T.tileData.LocX);
					                    d.SetY(T.tileData.LocY);
					                    T.setTileData(d);
					                    
					                    
									    /*Board[T.LocX][T.LocY]= t;
					                    JPanel jp = THA.get(T.LocX);
					                    JPanel Jref = (JPanel)jp.getComponent(T.LocY);
					                    Jref.remove(0);
					                    Jref.add(t);*/
					                    GB.repaint();
					                    _menu.Clear();
					                    _move.setCpActionPoints(_move.getCpActionPoints()-1);
					                    _move.setTilesThisTurn(_move.getTilesThisTurn()+1);
					                    _menu.RefreshStats();
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
							if((_move.getCurrentPlayer()==_menu._playerOne && _move.getTileClick1().tileData.GetExplorers(0)==0)||(_move.getCurrentPlayer()==_menu._playerTwo && _move.getTileClick1().tileData.GetExplorers(1)==0)){
								JOptionPane.showMessageDialog(null, "No explorers to move.");
								_move.reset();
								_menu.RefreshStats();
							}
							else
							{
								_menu.UpdateInstructions(3);
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
									if((_move.getTileClick1().tileData.GetExplorers(0)>0)&&(_move.getCurrentPlayer().getActionPoints()>=cost)){
										 MoveExplorer(cost);
										 
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Not enough Action Points.");
										_move.reset();
										_menu.RefreshStats();
									}
								}
								else if (_move.getCurrentPlayer().getName()=="P2"){
									//checks that the player has enough action points to make the move and that they have an explorer
									if((_move.getTileClick1().tileData.GetExplorers(1)>0)&&(_move.getCurrentPlayer().getActionPoints()>=cost)){
										 MoveExplorer(cost);
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Not enough Action Points.");
										_move.reset();
										_menu.RefreshStats();
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
								_menu.RefreshStats();
							}
						}
						
					break;
					
					//the tile is selected as the destination for a pyramid upgrade
					case 3: // PlacePyramid canPlace
						//checks if there is a pyramid on the selected tile or not
						if(T.tileData.PM == null) {
							JOptionPane.showMessageDialog(null, "There is no pyramid.");;
							break;
						}
						//checks the value of the pyramid already there
						switch(T.tileData.PM.getValue()){
		    				case 0:
		    					//checks the number of remaining pyramids of the required level
		    					if(_move.getPyrimidNumber(0) > 0)
		    					{
		    						//checks that the player making the placement has the most explorers on the tile
			    					if(_move.getCurrentPlayer().equals(_menu._playerOne) && T.tileData.GetExplorers(1) > T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(1);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(0, _move.getPyrimidNumber(0)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    						
			    					}
			    					else if (_move.getCurrentPlayer().equals(_menu._playerTwo) && T.tileData.GetExplorers(1) < T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(1);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(0, _move.getPyrimidNumber(0)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
		    					}
		    					else JOptionPane.showMessageDialog(null, "Not enough Level 1 Pyramids");
		    					
		    					_menu.RefreshStats();
		    					break;
		    				case 1:
		    					if(_move.getPyrimidNumber(1) > 0 )
		    					{
			    					if(_move.getCurrentPlayer().equals(_menu._playerOne) && T.tileData.GetExplorers(1) > T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(2);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(1, _move.getPyrimidNumber(1)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else if (_move.getCurrentPlayer().equals(_menu._playerTwo) && T.tileData.GetExplorers(1) < T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(2);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(1, _move.getPyrimidNumber(1)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
		    					}
		    					else JOptionPane.showMessageDialog(null, "Not enough Level 2 Pyramids");
		    					
		    					_menu.RefreshStats();
		    					break;
		    				case 2:
		    					if(_move.getPyrimidNumber(2) > 0)
		    					{
			    					if(_move.getCurrentPlayer().equals(_menu._playerOne) && T.tileData.GetExplorers(1) > T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(3);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(2, _move.getPyrimidNumber(2)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else if (_move.getCurrentPlayer().equals(_menu._playerTwo) && T.tileData.GetExplorers(1) < T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(3);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(2, _move.getPyrimidNumber(2)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
		    					}
		    					else JOptionPane.showMessageDialog(null, "Not enough Level 3 Pyramids");
		    					
		    					_menu.RefreshStats();
		    					break;
		    				case 3:
		    					if(_move.getPyrimidNumber(3) > 0)
		    					{
			    					if(_move.getCurrentPlayer().equals(_menu._playerOne) && T.tileData.GetExplorers(1) > T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(4);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(3, _move.getPyrimidNumber(3)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else if (_move.getCurrentPlayer().equals(_menu._playerTwo) && T.tileData.GetExplorers(1) < T.tileData.GetExplorers(2)){
			    						T.tileData.PM.setValue(4);
			    						_move.setCpActionPoints(_move.getCpActionPoints()-1);
			    						_move.setPyrimidNumber(3, _move.getPyrimidNumber(3)-1);
			    						_move.setPyramidsThisTurn(_move.getPyramidsThisTurn()+1);
			    					}
			    					else JOptionPane.showMessageDialog(null, "Not enough Explores here.");
		    					}
		    					else JOptionPane.showMessageDialog(null, "Not enough Level 4 Pyramids");
		    					
		    					_menu.RefreshStats();
		    					break;
		    				default:
		    					_menu.RefreshStats();
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
				
				Tile CH = board[x][y];
				
				int X1= t.tileData.LocX;
				int Y1= t.tileData.LocY;
				
				int X2= CH.tileData.LocX;
				int Y2= CH.tileData.LocY;
				
				if(X1==X2){
					if(Y1<Y2){
						//Check Tile 1 Down Path(i=3), Tile 2 Up Path (i=0)
						if(CH.tileData._paths[0]>0){
							//allow move if first tile has path, then add total cost
							canPlace=true;
						}
					}
					else if(Y1>Y2){
						//Check Tile 1 Up Path (i=0), Tile 2 Down Path (i=3)
						if(CH.tileData._paths[3]>0){
							//allow move if first tile has path, then add total cost
							canPlace=true;
						}
					}
				}
				else if(X1<X2){
					if( ((X1%2==0)&&(Y1>Y2)) || ((X1%2==1)&&(Y1==Y2)) ){
						//Check Tile 1 Up-Right Path (i=1), Tile 2 Down-Left Path(i=4)
						if(CH.tileData._paths[4]>0){
							//allow move if first tile has path, then add total cost
							canPlace=true;
						}
						
					}
					else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)) ){
						//Check Tile 1 Down-Right Path (i=2), Tile 2 Up-Left Path (i=5)
						if(CH.tileData._paths[5]>0){
							//allow move if first tile has path, then add total cost
							canPlace=true;
						}
					}
				}
				else if(X1>X2){
					if( ((X1%2==0)&&(Y1>Y2)) || ((X1%2==1)&&(Y1==Y2)) ){
						//Check Tile 1 Up-Left Path (i=5), Tile 2 Down-Right Path (i=2)
						if(CH.tileData._paths[2]>0){
							//allow move if first tile has path, then add total cost
							canPlace=true;
						}
					}
					else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)) ){
						//Check Tile 1 Down-Left Path (i=4) , Tile 2 Up-Right Path (i=1)
						if(CH.tileData._paths[1]>0){
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
						
				
				int X1= T1.tileData.LocX;
				int Y1= T1.tileData.LocY;
				
				int X2= T2.tileData.LocX;
				int Y2= T2.tileData.LocY;

				if(X1==X2){
					if(Y1<Y2){
						//Check Tile 1 Down Path(i=3), Tile 2 Up Path (i=0)
						if(T1.tileData._paths[3]>0){
							//allow move if first tile has path, then add total cost
							cost= T1.tileData._paths[3] + T2.tileData._paths[0];
						}
					}
					else if(Y1>Y2){
						//Check Tile 1 Up Path (i=0), Tile 2 Down Path (i=3)
						if(T1.tileData._paths[0]>0){
							//allow move if first tile has path, then add total cost
							cost= T1.tileData._paths[0] + T2.tileData._paths[3];
						}
					}
				}
				else if(X1<X2){
					if( ((X1%2==0)&&(Y1>Y2)&&(Y1-Y2==1)) || ((X1%2==1)&&(Y1==Y2)) ){
						//Check Tile 1 Up-Right Path (i=1), Tile 2 Down-Left Path(i=4)
						if(T1.tileData._paths[1]>0){
							//allow move if first tile has path, then add total cost
							cost= T1.tileData._paths[1] + T2.tileData._paths[4];
						}
						
					}
					else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)&&(Y2-Y1==1)) ){
						//Check Tile 1 Down-Right Path (i=2), Tile 2 Up-Left Path (i=5)
						if(T1.tileData._paths[2]>0){
							//allow move if first tile has path, then add total cost
							cost= T1.tileData._paths[2] + T2.tileData._paths[5];
						}
					}
				}
				else if(X1>X2){
					if( ((X1%2==0)&&(Y1>Y2)&&(Y1-Y2==1)) || ((X1%2==1)&&(Y1==Y2)) ){
						//Check Tile 1 Up-Left Path (i=5), Tile 2 Down-Right Path (i=2)
						if(T1.tileData._paths[5]>0){
							//allow move if first tile has path, then add total cost
							cost= T1.tileData._paths[5] + T2.tileData._paths[2];
						}
					}
					else if ( ((X1%2==0)&&(Y1==Y2)) || ((X1%2==1)&&(Y1<Y2)&&(Y2-Y1==1)) ){
						//Check Tile 1 Down-Left Path (i=4) , Tile 2 Up-Right Path (i=1)
						if(T1.tileData._paths[4]>0){
							//allow move if first tile has path, then add total cost
							cost= T1.tileData._paths[4] + T2.tileData._paths[1];
						}
					}
				}

				return cost;
			}
			
			//method that makes the explorer move
			public void MoveExplorer(int cost){
				
				if (_move.getCurrentPlayer().getName()=="P1"){
					_move.getTileClick1().tileData.SetExplorer( 1 , (_move.getTileClick1().tileData.GetExplorers(1)-1));
					_move.getTileClick1().tileData.SetExplorer( 1 , (_move.getTileClick1().tileData.GetExplorers(1)+1));
					_move.getCurrentPlayer().setActionPoints(_move.getCurrentPlayer().getActionPoints()-cost);
					_move.reset();
					_menu.RefreshStats();
				}
				else if(_move.getCurrentPlayer().getName()=="P2"){
					_move.getTileClick1().tileData.SetExplorer( 1 , (_move.getTileClick1().tileData.GetExplorers(1)-1));
					_move.getTileClick1().tileData.SetExplorer( 1 , (_move.getTileClick1().tileData.GetExplorers(1)+1));
					_move.getCurrentPlayer().setActionPoints(_move.getCurrentPlayer().getActionPoints()-cost);
					_move.reset();
					_menu.RefreshStats();
				}
				else{
					JOptionPane.showMessageDialog(null, "Unknown player defined.");
				}
				
				
			}
}
