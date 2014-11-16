package tikal_dev;

import java.util.ArrayList;

public class TileData {
	
    //Pyramid Initialized to Null
    Pyramid PM = null;
    boolean _PMV;
    
    //Location of the Tile
    int LocX,LocY;
    
    //Size of path in each six directions;
    int[] _paths;
    
    
    //Values of Player Explorers on this tile
    int P1E;
    int P2E;
    
    int[] explorers;
    
    boolean _empty;
    
	public TileData(int x, int y, int[] paths, boolean PMV, boolean empty)
	{
		LocX = x;
        LocY = y;
        _paths = paths;
        _PMV = PMV;
        _empty = empty;
        P1E = 0;
        P2E = 0;
        explorers = new int[2];
	}
	
	public int getPathValue(int i)
	{
		return _paths[i];
	}
	
	public int GetX()
	{
		return LocX;
	}
	
	public void SetX(int x)
	{
		LocX = x;
	}
	
	public int GetY()
	{
		return LocY;
	}
	
	public void SetY(int y)
	{
		LocY = y;
	}
	
	public void SetExplorer(int playerIndex, int e)
	{
		explorers[playerIndex] = e;
	}
	
	public int GetExplorers(int e)
	{
		return explorers[e];
		
	}
	
	//Rotates the wall array counter clockwise and updates the visuals
	public void RotateClockwise()
	{
		int temp = _paths[5];
		_paths[5] = _paths[4];
		_paths[4] = _paths[3];
		_paths[3] = _paths[2];
		_paths[2] = _paths[1];
		_paths[1] = _paths[0];
		_paths[0]=temp;
		//UpdatePaths();
		
	}
	
	//Rotates the wall array counter clockwise and updates the visuals
	public void RotateCounterClockwise()
	{
		int temp = _paths[0];
		_paths[0] = _paths[1];
		_paths[1] = _paths[2];
		_paths[2] = _paths[3];
		_paths[3] = _paths[4];
		_paths[4] = _paths[5];
		_paths[5] = temp;
		//UpdatePaths();
		
	}

}
