package tikal_dev;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel
{
	Player _playerOne;
	Player _playerTwo;
	Move _move;
	
	JPanel stats;
	JPanel actions;
	JPanel preview;
	JButton tile;
	JButton pyramid;
	JButton explorer;
	JButton move;
	JLabel tileText;
	JLabel pyramidText;
	JLabel explorerText;
	JLabel moveText;
	JLabel tileCost;
	JLabel pyramidCost;
	JLabel explorerCost;
	JLabel moveCost;
	JLabel playerName;
	JLabel playerPointsText;
	JLabel playerPoints;
	JLabel playerExplorersText;
	JLabel playerExplorers;
	JLabel currentlyExploringText;
	JLabel currentlyExploring;
	JLabel instructions;
	JPanel tileImg;
	JButton clockwise;
	JButton counter;
	JPanel turnEnd;
	JButton next;
	JButton end;
	Player current;
	Tile[][] _board;
	ArrayList<Tile> gameTilesArr;
	Tile[] gameTiles;
	Tile nextToPlace;
	
	public Menu(Player playerOne , Player playerTwo , Move move, Tile[][] Board)
	{
		_board = Board;
		_playerOne = playerOne;
		_playerTwo = playerTwo;
		current = _playerOne;
		_move = move;
		_move.setCurrentPlayer(current);
		
		setup();
		CreateTiles();
	}
	
	
	
	
	public void setup() 
	{
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(300,600);
		
		//Setting up the 'Player Stats" Panel
			//this is where the stats for the current player are displayed
		stats = new JPanel();
		this.add(stats);
		stats.setMinimumSize(new Dimension (300,200));
		stats.setLayout(null);
		
		playerName = new JLabel(_move.getCurrentPlayer().getName());
		playerName.setHorizontalAlignment( SwingConstants.CENTER );
		playerName.setFont(new Font("Serif", Font.BOLD, 18));
		stats.add(playerName);
		playerName.setBounds(0 , 0 , 300 , 50);
		
		playerPointsText = new JLabel("Action Points");
		playerPointsText.setHorizontalAlignment( SwingConstants.CENTER );
		playerPointsText.setFont(new Font("Serif", Font.BOLD, 16));
		stats.add(playerPointsText);
		playerPointsText.setBounds(0 , 50 , 150 , 25);
		
		playerPoints = new JLabel(String.valueOf(_move.getCpActionPoints()));
		playerPoints.setHorizontalAlignment( SwingConstants.CENTER );
		playerPoints.setFont(new Font("Serif", Font.BOLD, 24));
		stats.add(playerPoints);
		playerPoints.setBounds(200 , 50 , 100, 25);
		
		playerExplorersText = new JLabel("Available Explorers");
		playerExplorersText.setHorizontalAlignment( SwingConstants.CENTER );
		playerExplorersText.setFont(new Font("Serif", Font.BOLD, 16));
		stats.add(playerExplorersText);
		playerExplorersText.setBounds(0 , 100 , 150 , 25);
		
		playerExplorers = new JLabel(String.valueOf(_move.getAvailableExplorer()));
		playerExplorers.setHorizontalAlignment( SwingConstants.CENTER );
		playerExplorers.setFont(new Font("Serif", Font.BOLD, 24));
		stats.add(playerExplorers);
		playerExplorers.setBounds(200 , 100 , 100 , 25 );
		
		currentlyExploringText = new JLabel("Currently Exploring");
		currentlyExploringText.setHorizontalAlignment( SwingConstants.CENTER );
		currentlyExploringText.setFont(new Font("Serif", Font.BOLD, 16));
		stats.add(currentlyExploringText);
		currentlyExploringText.setBounds(0 , 150 , 150 , 25);
		
		currentlyExploring = new JLabel(String.valueOf(10-_move.getAvailableExplorer()));
		currentlyExploring.setHorizontalAlignment( SwingConstants.CENTER );
		currentlyExploring.setFont(new Font("Serif", Font.BOLD, 24));
		stats.add(currentlyExploring);
		currentlyExploring.setBounds(200 , 150 , 100 , 25 );
		
		instructions = new JLabel("");
		instructions.setHorizontalAlignment( SwingConstants.CENTER );
		instructions.setFont(new Font("Serif", Font.BOLD, 14));
		stats.add(instructions);
		instructions.setBounds(0 , 200 , 300 , 50);
		//End of 'Player Stats' Panel
		
		//Setting up 'Actions' Panel
			//this is where the player can spend their action points for the turn
		actions = new JPanel();
		this.add(actions);
		actions.setMaximumSize(new Dimension(300,100));
		actions.setLayout(new GridLayout(2,2));
		
		//Setting up the 'Place a Tile' Button
			//Cosmetics
		tile = new JButton();
		tile.setLayout(new BorderLayout());
		tileText = new JLabel("Place a Tile");
		tileText.setFont(new Font("Serif", Font.BOLD, 16));
		tileText.setHorizontalAlignment( SwingConstants.CENTER );
		tile.add(tileText , BorderLayout.CENTER);
		tileCost = new JLabel("(1AP)");
		tileCost.setHorizontalAlignment( SwingConstants.CENTER );
		tile.add(tileCost, BorderLayout.SOUTH);
		actions.add(tile);
			//Functionality
		tile.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
        		PlaceTileClick(e);
        	}
        });
		//End of 'Place a tile' Button
		
		//Setting up the 'Place a Pyramid' Button
			//Cosmetics
		pyramid = new JButton();
		pyramid.setLayout(new BorderLayout());
		pyramidText = new JLabel("Place a Pyramid");
		pyramidText.setFont(new Font("Serif", Font.BOLD, 16));
		pyramidText.setHorizontalAlignment( SwingConstants.CENTER );
		pyramid.add(pyramidText , BorderLayout.CENTER);
		pyramidCost = new JLabel("(1AP)");
		pyramidCost.setHorizontalAlignment( SwingConstants.CENTER );
		pyramid.add(pyramidCost, BorderLayout.SOUTH);
		actions.add(pyramid);
			//Functionality
		pyramid.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
				PlacePyramidClick(e);
			}
		});
		//End of 'Place a Pyramid' Button
		
		//Setting up the 'Place an Explorer Button
			//Cosmetics
		explorer = new JButton();
		explorer.setLayout(new BorderLayout());
		explorerText = new JLabel("Place an Explorer");
		explorerText.setFont(new Font("Serif", Font.BOLD, 14));
		explorerText.setHorizontalAlignment( SwingConstants.CENTER );
		explorer.add(explorerText , BorderLayout.CENTER);
		explorerCost = new JLabel("(1AP)");
		explorerCost.setHorizontalAlignment( SwingConstants.CENTER );
		explorer.add(explorerCost, BorderLayout.SOUTH);
		actions.add(explorer);
			//Functionality
		explorer.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
				PlaceExplorerClick(e);
			}
		});
		//End of 'Place an Explorer' Button
		
		//Setting up the 'Move an Explorer' Button
			//Cosmetics
		move = new JButton();
		move.setLayout(new BorderLayout());
		moveText = new JLabel("Move an Explorer");
		moveText.setFont(new Font("Serif", Font.BOLD, 14));
		moveText.setHorizontalAlignment( SwingConstants.CENTER );
		move.add(moveText , BorderLayout.CENTER);
		moveCost = new JLabel("(1AP)");
		moveCost.setHorizontalAlignment( SwingConstants.CENTER );
		move.add(moveCost, BorderLayout.SOUTH);
		actions.add(move);
			//Functionality
		move.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
				MoveExplorerClick(e);
			}
		});
		//End of 'Move an Explorer' Button
		//End of 'Actions' Panel
		
		//Setting up the 'Preview' Panel
			//this is where the next Tile to place will be shown and manipulated before placement
		preview = new JPanel();
		this.add(preview);
		preview.setSize(300,100);
		preview.setLayout(null);
		
		//Setting up the Preview image
		tileImg = new JPanel();
		tileImg.setSize(100,100);
		preview.add(tileImg);
		tileImg.setBounds(100 , 0, 100, 100);
		
		//Setting up the 'Rotate' Buttons
			//Clockwise Rotation
			//Cosmetics
		clockwise = new JButton();
		clockwise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Clockwise.png"))); 
		preview.add(clockwise);
		clockwise.setSize(40,40);
		clockwise.setBounds(30, 40 , 40 , 40);
			//Functionality
		clockwise.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
				ClockwiseClick(e);
			}
		});
		
			//Counterclockwise Rotation
			//Cosmetics
		counter = new JButton();
		counter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Counterclockwise.png")));
		preview.add(counter);
		counter.setSize(40,40);
		counter.setBounds(230, 40 , 40 , 40);
			//Functionality
		counter.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
				CounterClick(e);
			}
		});
		
		//Adds the 'Next Turn' Button to the preview Panel
		next = new JButton("End Turn");
		preview.add(next);
		next.setBounds(0 , 125 , 150 , 100);
		next.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void  mousePressed(MouseEvent e) {
				NextClick(e);
			}
		});
		
		//Add an end game button to end the game if no more tiles can be placed but not all 40 have been placed
		end = new JButton("End Game");
		preview.add(end);
		end.setBounds(150 , 125 , 150 , 100);
		end.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				EndClick(e);
			}
		});
		//End of the 'Preview' panel
		
	}
	
	//These are methods that handle each what happens when each menu button is clicked
	public void PlaceTileClick(MouseEvent e)
	{
		if(_move.getTilesThisTurn() < 1)//checks if a tile has already been placed this turn
		{
			if(_move.getCpActionPoints() > 0)//checks if player has enough action points
			{
				
			
				if(_move.getCurrentTilePlaced() < 40)//checks if there are tiles remaining to be placed
				{
					if(nextToPlace == null)//checks to see if a tile has already been drawn but not placed
					{
						//pulls a tile from the tile[] and sets if in the preview for rotation and placement
						//and updates move to the placing a tile state
						UpdateInstructions(1);
						nextToPlace = gameTiles[_move.getCurrentTilePlaced()];
						tileImg.add(nextToPlace);
						nextToPlace.RotateClockwise();
						tileImg.repaint();
						_move.setNexttoPlace(nextToPlace);
						_move.setMoves(1);
						//System.out.println("Placed a tile!");
						_move.setCurrentTilePlaced(_move.getCurrentTilePlaced()+1);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You already have a tile");
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No More Tiles!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You need more Action Points!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Max tiles placed this turn");
		}
				
				
	
	}
	
	public void PlacePyramidClick(MouseEvent e)
	{
		if(nextToPlace == null)//checks to make sure you don't have a tile pending placement
		{
			if(_move.getPyramidsThisTurn() < 2)//checks how many pyramids already placed this turn
			{
				if(_move.getCpActionPoints() > 0)//checks action points
				{
					//updates move to placing a pyramid state
					UpdateInstructions(1);
					_move.setMoves(3);
					//System.out.println("Placed a Pyramid!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You need more Action Points!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Max pyramids placed this turn");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must place your tile first");
		}
		
		
	}
	
	public void PlaceExplorerClick(MouseEvent e) 
	{
		if(nextToPlace == null)//checks to make sure there is no tile pending placement
		{
			if(_move.getCpActionPoints() > 0)//checks action points
			{
				if(_move.getFirstTile() != null)//checks to see if there is a tile to place the explorer on
				{
					if(_move.getAvailableExplorer() >0)//checks to see if player has an available explorer to place
					{
						
						_move.setAvailableExplorer(_move.getAvailableExplorer() - 1);
						_move.setCpActionPoints(_move.getCpActionPoints()-1);
						this.RefreshStats();
						//System.out.println("Placed an Explorer!");
						//if current player is Player 1, increment number of explorers for him on First Tile
						if (_move.getCurrentPlayer().getName()=="P1")
						{
							_move.getFirstTile().setP1(_move.getFirstTile().getP1()+1);
						}
						//if current player is Player 2, increment number of explorers for him on First Tile
						else if (_move.getCurrentPlayer().getName()=="P2")
						{
							_move.getFirstTile().setP2(_move.getFirstTile().getP2()+1);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No More Explorers!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "A Tile must be placed first!"); //tile is null
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You need more Action Points!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must place your Tile first!");
		}
	}
	
	public void MoveExplorerClick(MouseEvent e) 
	{
		if(nextToPlace == null)//checks to see if a tile is pending placement
		{
			//updates move to the moveing an explorer state
			UpdateInstructions(2);
			_move.setMoves(2);
			//System.out.println("Moved and Explorer!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must place your tile first");
		}
	}
	
	public void ClockwiseClick(MouseEvent e) 
	{
		if(nextToPlace != null)//checks to make sure there is a tile pending placement
		{
			//rotates the tile that is pending placement 
			nextToPlace.RotateClockwise();
			tileImg.repaint();
			_move.setNexttoPlace(nextToPlace);
			//System.out.println("Rotated clockwise!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No preview to rotate, Click 'Place a Tile' first.");
		}
	}
	
	public void CounterClick(MouseEvent e) 
	{
		if(nextToPlace != null)//checks to make sure there is a tile pendinig placement
		{
			nextToPlace.RotateCounterClockwise();
			tileImg.repaint();
			_move.setNexttoPlace(nextToPlace);
			//System.out.println("Rotated counterclockwise!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No preview to rotate, Click 'Place a Tile' first.");
		}
	}
	
	public void NextClick(MouseEvent e) 
	{
		if(nextToPlace == null)//checks if there is a tile pending placement
		{
			if(_move.getCurrentTilePlaced() == 40)//checks if all tiles have been placed
			{
				//ends the game and calculates score
				score();
			}
			else if(current == _playerOne)//checks if the current player is player one
			{
				//changes the player
				current = _playerTwo;
				_move.setCpActionPoints(8);
				_move.setTilesThisTurn(0);
				_move.setPyramidsThisTurn(0);
			}
			else
			{
				//changes the player
				current = _playerOne;
				_move.setCpActionPoints(8);
				_move.setTilesThisTurn(0);
				_move.setPyramidsThisTurn(0);
			}
			_move.setCurrentPlayer(current);
			playerName.setText(_move.getCurrentPlayer().getName());
			_move.setMoves(0);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must place your Tile first!");
		}
		this.RefreshStats();
	}
	
	public void EndClick(MouseEvent e)
	{
		//ends the game and calculates score
		score();
	}
	//end of button handling methods
	
	//methods that refresh the menu portion of the GUI
	public void Clear()
	{
		//clears the preview after the tile is placed
		nextToPlace = null;
		tileImg.repaint();
	}
	
	public void RefreshStats()
	{
		//updates the stats menu with current stats
		//should be called after each action taken
		UpdateInstructions(0);
		playerPoints.setText(String.valueOf(_move.getCpActionPoints()));
		playerExplorers.setText(String.valueOf(_move.getAvailableExplorer()));
		currentlyExploring.setText(String.valueOf(10-_move.getAvailableExplorer()));
		stats.repaint();
	}
	
	public void UpdateInstructions(int i)
	{
		//updates the instructions based on what is clicked
		int instructionState = i;
		
		switch(instructionState)
		{
			case 0:
				instructions.setText("");
				break;
			case 1:
				instructions.setText("Click where you would like to place");
				break;
			case 2:
				instructions.setText("Click where you want to move from");
				break;
			case 3:
				instructions.setText("Click where you want to move to");
				
		
		}
	}
	

	//Method that creates the  terrain tiles for the game
		public void CreateTiles()
		{
			gameTiles = new Tile[40];
			gameTilesArr = new ArrayList<Tile>();
			Tile Cur;
			
			for(int i = 0; i < 40; i++)
			{
				if(i < 8)
				{
					//creates 8 tiles with pyramid bases
					Cur = new Tile(0 , 0 , RandomTilePaths(), true);
					Cur.SetNonEmpty();
					gameTilesArr.add(Cur);
				}
				else
				{
					//creates the rest of the tiles without pyramid bases
					Cur = new Tile(0 , 0 , RandomTilePaths(), false);
					Cur.SetNonEmpty();
					gameTilesArr.add(Cur);
				}
			}
			
			//shuffles the array so the tiles are drawn in random order
			Collections.shuffle(gameTilesArr);
			
			for(int w = 0; w < 40; w++)
			{
				gameTiles[w] = gameTilesArr.get(w);
			}
		}
		
		public int[] RandomTilePaths()
		{
			Random rng = new Random();
			//creates an array of 6 ints to determine the number of paths
			int[] paths = new int[6];
			ArrayList<Integer> pathsList = new ArrayList<Integer>();
			pathsList.add(rng.nextInt(2)+1);
			for(int j = 0; j < 3; j++)
			{
				pathsList.add(0);
			}
			for(int k = 0; k < 2; k++)
			{
				//adds 3 random numbers (0-2) to determine the remaining sides and how many paths they have
				pathsList.add(rng.nextInt(3));
			}
			
			Collections.shuffle(pathsList);
			
			for(int q = 0; q < 6; q++)
			{
				paths[q] = pathsList.get(q);
			}
			
			return paths;
		}
		//method that calculates scores at game end
		public void score(){
			for(int x =0;x < 8; x++ )
				for(int y = 0;y < 5; y++){
					Tile temp = _board[x][y];
					if(temp.P1E > temp.P2E){//checks who has more explorers on a tile
						if(temp.PM == null)//checks if there is a pyramid on the tile being scored
							_playerOne.setScore(_playerOne.getScore()+1);
						else if(temp.PM.getValue() >0)//checks the value the pyramid on the tile bing scored
							_playerOne.setScore(_playerOne.getScore()+temp.PM.getValue());
						else 
							_playerOne.setScore(_playerOne.getScore()+1);
					}
					else if(temp.P2E > temp.P1E){//checks who has more explorers on a tile
						if(temp.PM == null)//checks if there is a pyramid on the tile bing scored
							_playerTwo.setScore(_playerTwo.getScore()+1);
						else if(temp.PM.getValue() >0)//checks the value of the pyramid on the tile being scored
							_playerTwo.setScore(_playerTwo.getScore()+temp.PM.getValue());
						else 
							_playerTwo.setScore(_playerTwo.getScore()+1);
					}
					else; 
				}
			JOptionPane.showMessageDialog(null, "Player 1 score: "+ _playerOne.getScore()+" Player 2 score: " + _playerTwo.getScore());
		}


}
