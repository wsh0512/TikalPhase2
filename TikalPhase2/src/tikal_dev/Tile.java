

package tikal_dev;


import javax.swing.JLabel;


/**
 * @authors:
 * 
 */

public class Tile extends javax.swing.JPanel {
    
    //tile background
    JLabel BG;
    TileData tileData;
     
    public Tile(TileData TD)
    {
    	tileData = TD;
        this.setSize(100, 100);
        initComponents(tileData._PMV);
        UpdatePaths();
    }
    
    public void setTileData(TileData TD)
    {
    	tileData = TD;
    	if(!tileData._empty)
    	{
    		SetNonEmpty();
    	}
    	initComponents(tileData._PMV);
    	UpdatePaths();
    }
    
    public TileData getTileData()
    {
    	return tileData;
    }
    
    public void RotateClockwise()
    {
    	tileData.RotateClockwise();
    	UpdatePaths();
    }
    
    public void RotateCounterClockwise()
    {
    	tileData.RotateCounterClockwise();
    	UpdatePaths();
    }
    //Builds the GUI and configuration
	public void initComponents(boolean PMV) {
		
		//Initialize Path Components
        Bottom = new javax.swing.JLabel();
        Top = new javax.swing.JLabel();
        RTop = new javax.swing.JLabel();
        LTop = new javax.swing.JLabel();
        RBottom = new javax.swing.JLabel();
        LBottom = new javax.swing.JLabel();
        P = new JLabel[this.tileData.explorers.length];
        //Initialize Player Explorer Counts
        for(int i =0; i <this.tileData.explorers.length;i++){
        	P[i] = new javax.swing.JLabel();
        }       
        //Initialize Background Image
        Background = new javax.swing.JLabel();

        
        
        //
        //  INITIALIZE OVERALL FORMATTING 
        //
        
      //sets the background of the tile panel invisible to only show the hexagon
        setOpaque(false); 			
        
        //Tile Size set to 100x100
        setPreferredSize(new java.awt.Dimension(100, 100));
        
        //Null layout allows for easy placement of tile components
        setLayout(null);

        
        
        //
        //  INITIALIZE ALL PATHS ON TILE OBJECT
        //
        
        //Initialize Bottom Path
        Bottom.setForeground(new java.awt.Color(255, 255, 255));
        Bottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
        add(Bottom);
        Bottom.setBounds(20, 80, 60, 20);

        //Initialize Top Path
        Top.setForeground(new java.awt.Color(255, 255, 255));
        Top.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
        add(Top);
        Top.setBounds(20, 0, 60, 20);

        //Initialize Top Right Path
        RTop.setForeground(new java.awt.Color(255, 255, 255));
        RTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(RTop);
        RTop.setBounds(70, 10, 30, 40);

        //Initialize Top Left Path
        LTop.setForeground(new java.awt.Color(255, 255, 255));
        LTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
        add(LTop);
        LTop.setBounds(0, 0, 30, 60);

        //Initialize Bottom Right Path
        RBottom.setForeground(new java.awt.Color(255, 255, 255));
        RBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
        add(RBottom);
        RBottom.setBounds(60, 40, 50, 60);

        //Initialize Bottom Left Path
        LBottom.setForeground(new java.awt.Color(255, 255, 255));
        LBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(LBottom);
        LBottom.setBounds(0, 50, 30, 40);
        
        
        
        
        //
        //  INITIALIZE ALL PLAYER COUNTERS
        //

        //Initialize  Player 1 counter
        for(int i =0;i<P.length;i++){
        	P[i].setForeground(new java.awt.Color(255, 255, 255));
        	P[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        	P[i].setText("0");
        	add(P[i]);
        	P[i].setBounds(5+10*i, 40, 20, 20);
        }

        //Initialize the Pyramid if the boolean parameter PMV is set to true (has pyramid)
        if (tileData._PMV){
        	//Initialize Pyramid Object
            tileData.PM = new Pyramid(tileData.LocX,tileData.LocY,0);
        	add(tileData.PM);
        	tileData.PM.setBounds(28, 22, 45, 50);       	
        }
        
        //
        //  INITIALIZE ALL PATHS ON TILE OBJECT
        //
        
        //Initialize Background Image that gives the hexagon tile look
        Background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/EmptyBlockSM.png"))); 
        add(Background);
        Background.setBounds(20, 20, 60, 60);
    }
    
	//Refreshes the path images 
	public void UpdatePaths() {
		
		//This function refreshes the images for the paths depending on the contents of paths[]
		//The indexes associated with each path is listed below along with the wall color values. 
		
		//Paths will be defined as following indexes:
	    //	index  0: North path
	    // 	index  1: NorthEast path
	    //  index  2: SouthEast path
	    //  index  3: South path
	    //  index  4: SouthWest path
	    //  index  5: NorthWest path
	    //Values currently accepting
	    //  value 0: no path (blank)
	    //  value 1: path of 1 (green)
	    //  value 2: path of 2 (blue)
		//		-later a path of value 3 (purple)
		
		for (int i = 0; i<6; i++){
			int cur = tileData.getPathValue(i);
			switch(i){
				case 0:
					//NORTH
					if(cur==1){
						Top.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Top.png")));
					}
					else if(cur==2){
						Top.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Top_B.png")));
					}
					else{
						Top.setIcon(null);
					}
					
					break;
				case 1:
					//NORTHEAST
					if(cur==1){
						RTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Backward.png")));
					}
					else if(cur==2){
						RTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Backward_B.png")));
					}
					else{
						RTop.setIcon(null);
					}
					break;
				case 2:
					//SOUTHEAST
					if(cur==1){
						RBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Forward.png")));
					}
					else if(cur==2){
						RBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Forward_B.png")));
					}
					else{
						RBottom.setIcon(null);
					}
					break;
				case 3:
					//SOUTH
					if(cur==1){
						Bottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Bottom.png")));
					}
					else if(cur==2){
						Bottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Bottom_B.png")));
					}
					else{
						Bottom.setIcon(null);
					}
					break;
				case 4:
					//SOUTHWEST
					if(cur==1){
						LBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Backward.png"))); 
					}
					else if(cur==2){
						LBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Backward_B.png"))); 
					}
					else{
						LBottom.setIcon(null);
					}
					break;
				case 5:
					//NORTHWEST
					if(cur==1){
						LTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Forward.png")));
					}
					else if (cur==2){
						LTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Forward_B.png")));
					}
					else{
						LTop.setIcon(null);
					}
					break;
				
			}
		}
		repaint();
	}
	
	public void SetNonEmpty(){
		Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/FullBlockSM_2B.png")));
	}
	
	public void setExplorestext(int index,int val){
		if(this.tileData.GetExplorers(index) == 0){
			P[index].setText("");
		}
		else{
			P[index].setText(String.valueOf(this.tileData.GetExplorers(index)));
		}
	}
	
	
	//Class Component Variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Bottom;
    private javax.swing.JLabel LBottom;
    private javax.swing.JLabel LTop;
    private javax.swing.JLabel P1_Ind;
    private javax.swing.JLabel P2_Ind;
    private javax.swing.JLabel RBottom;
    private javax.swing.JLabel RTop;
    private javax.swing.JLabel Top;
    private javax.swing.JLabel[] P;

    
}
