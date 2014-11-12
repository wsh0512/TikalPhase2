package tikal_dev;

//The move object communicates betwen the menu and game in helping deside what needs to be done for different actions
public class Move {

	private Player _CurrentPlayer;
	private int _Moves;
	private Tile _FirstTile;
	private Tile _TileClick1;
	private Tile _TileClick2;
	//private Tile _NexttoPlace;
	private int _CurrentTilePlaced;// number of CurrentTilePlaced
	private int[] _PyramidNumber;
	private int _TilesThisTurn;
	private int _PyramidsThisTurn;
	
	public Move(){
		_CurrentPlayer = null;
		_Moves = 0;
		_FirstTile = null;
		_TileClick1 = null;
		_TileClick2 = null;
		//_NexttoPlace = null;
		_CurrentTilePlaced = 0;
		_PyramidsThisTurn = 0;
		_TilesThisTurn = 0;
		_PyramidNumber = new int[4];
		_PyramidNumber[0] = 8;
		_PyramidNumber[1] = 6;
		_PyramidNumber[2] = 4;
		_PyramidNumber[3] = 2;
	}
	//set and get ActionPoints
	public int getCpActionPoints(){
		return _CurrentPlayer.getActionPoints();
	}
	
	public void setCpActionPoints(int AP){
		_CurrentPlayer.setActionPoints(AP);
	}
	
	//set and get move_states
	public void setMoves(int val){
		_Moves = val;
	}

	public int getMoves(){
		return _Moves;
	}
	
	//set and get CurrentPlayer
	public void setCurrentPlayer(Player cp){
		//if(_CurrentPlayer != null){
		//	this.setCpActionPoints(this.getCpActionPoints()+10);
		//}
		_CurrentPlayer = cp;
	}
	public Player getCurrentPlayer(){
		return _CurrentPlayer;
	}
	
	//set and get FirstTile
	public void setFirstTile(Tile ft){
		if(_FirstTile == null) _FirstTile = ft;
	}
	public Tile getFirstTile(){
		return _FirstTile;
	}
	
	//set and get TileClick1
	public void setTileClick1(Tile tc1){
		_TileClick1 = tc1;
	}
	public Tile getTileClick1(){
		return _TileClick1;
	}
	
	//set and get TileClick2
	public void setTileClick2(Tile tc2){
		_TileClick2 = tc2;
	}
	public Tile getTileClick2(){
		return _TileClick2;
	}
	
	//set and get NexttoPlace
	public void setNexttoPlace(Tile ntp){
		_CurrentPlayer.setNextTile(ntp);;
	}
	public Tile getNexttoPlace(){
		return _CurrentPlayer.getNextTile();
	}
	
	//set and get number of CurrentTilePlaced
	public void setCurrentTilePlaced(int val){
		_CurrentTilePlaced = val;
	}
	public int getCurrentTilePlaced(){
		return _CurrentTilePlaced;
	}
	
	//set and get PyrimidNumber to keep track of allowed number of different level pyramids
	public void setPyrimidNumber(int index, int val){
		_PyramidNumber[index] = val;
	}
	
	public int getPyrimidNumber(int index){
		return _PyramidNumber[index];
	}
	
	//set and get AvailableExplorer
	public int getAvailableExplorer(){
		return _CurrentPlayer.getAvailableExplores();
	}
	public void setAvailableExplorer(int val){
		_CurrentPlayer.setAvailableExplores(val);
	}
	
	public void setPyramidsThisTurn(int i){
		_PyramidsThisTurn = i;
		
	}
	
	public int getPyramidsThisTurn(){
		return _PyramidsThisTurn;
	}
	public void setTilesThisTurn(int i){
		_TilesThisTurn = i;
		
	}
	
	public int getTilesThisTurn(){
		return _TilesThisTurn;
	}
	
	public void reset(){
		_TileClick1 = null;
		_TileClick2 = null;
		_Moves = 0;
	}
}
