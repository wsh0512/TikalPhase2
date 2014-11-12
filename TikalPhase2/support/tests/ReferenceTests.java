package tests;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import tikal_dev.Player;
import tikal_dev.Tile;

public class ReferenceTests {
	private tikal_dev.Player _player; 
	private tikal_dev.Move _move;
	private tikal_dev.Tile _tile; 
	
	@Before
	public void setup(){
		int[] thing = new int[] {0,0,0,0,0,0};
		_player = new tikal_dev.Player("name");
		_move = new tikal_dev.Move(); 
		_tile = new tikal_dev.Tile(0, 0, thing, false); 
	}
	
	@Test
	public void testGetNameMethod(){
		setup();
		String expected = "name";
		String actual = _player.getName();
		assertTrue(_player.getName(), expected.equals(actual));
	}
	
	@Test 
	public void testNextTileMethod(){
		_player.setNextTile(_tile);
		Tile expected = _tile; 
		Tile actual = _player.getNextTile();
		assertTrue("", expected.equals(actual));
	}
	
	@Test 
	public void testAvailbleExplorersMethod(){
		_player.setAvailableExplores(3);
		int expected = 3; 
		int actual = _player.getAvailableExplores();
		assertTrue("", expected == actual);
	}
	
	@Test
	public void testFirstTileMethod(){
		_move.setFirstTile(_tile);
		Tile expected = _tile; 
		Tile actual = _move.getFirstTile();
		assertTrue("", expected.equals(actual));
	}
	
	@Test
	public void testNextToPlace(){
		_move.setCurrentPlayer(_player);
		_move.setNexttoPlace(_tile);
		Tile expected = _tile; 
		Tile actual = _move.getNexttoPlace();
		assertTrue("", expected.equals(actual));
	}
	
	@Test
	public void testCurrentPlayerMethod(){
		_move.setCurrentPlayer(_player);
		Player expected = _player; 
		Player actual = _move.getCurrentPlayer();
		assertTrue("", expected.equals(actual));
	}
	
	@Test
	public void testMoveCountMethod(){
		_move.setMoves(3);
		int expected = 3; 
		int actual = _move.getMoves();
		assertTrue("", expected == actual);
	}
	
	@Test
	public void testCurrentTileMethod(){
		_move.setCurrentTilePlaced(3);
		int expected = 3; 
		int actual = _move.getCurrentTilePlaced();
		assertTrue("", expected==actual);
	}
	
	public void testPyramidNumberMethod(){
		_move.setPyrimidNumber(3, 0);
		int expected = 0;
		int actual = _move.getPyrimidNumber(3);
		assertTrue("", expected==actual);
	}
}
