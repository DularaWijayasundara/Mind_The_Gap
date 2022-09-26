import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/*
 *  The main window of the gui.
 *  The WindowDemo class extends JFrame to add components
 *  This also implements ActionListner and MopuseListner to handle user input.
 *  
 *  This is based on Simple GUI Demonstration 2 by Dr.Mark Hatcher
 *  @author dwijayasunda
 */
public class WindowGame extends JFrame implements ActionListener, MouseListener
{
	// gui components that are contained in this frame:
	private JPanel topPanel, bottomPanel;	// top and bottom panels in the main window
	private JLabel instructionLabel;		// a text label to tell the user what to do
    private JButton topButton;				// a 'New Game' button to appear in the top panel
	private GridSquare [][] gridSquares;	// squares to appear in grid formation in the bottom panel
	private int rows,columns;				// number of rows and columns in the grid
	
	private boolean gameBegun;            // a boolean variable to keep on track if a user has clicked on  the "New Game" button or not
	
	private Random rand;
	private int playerNum;
	private int nextPlayer;
	/*
	 *  WindowDemo constructor method creates 16 gridsquares (4 rows and 4 columns)
	 *  it then creates the panels, their subcomponents and puts them all together in the main frame
	 *  it makes sure that action listeners are added to selectable items
	 *  it makes sure that the gui will be visible
	 */
	public WindowGame()
	{
		rows = 4;
		columns = 4;
		this.setSize(600,600);
		
		gameBegun = false;
		
		
		
		// first the panels are being created
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(rows, columns, 10, 10));
		bottomPanel.setSize(500,500);
		
		// then  the components for each panel are being created and added them to it
		
		// for the top panel:
		instructionLabel = new JLabel("Don't select neighour squres! Click to begin >>");
		topButton = new JButton("New Game");
		topButton.addActionListener(this);  //An ActionListner to the "New Game" button
	    
		topPanel.add(instructionLabel);
		topPanel.add (topButton);
		
	
		// for the bottom panel:	
		// the squares are being created  and added to the grid
		gridSquares = new GridSquare[rows][columns];
		for ( int x = 0; x < columns; x ++)
		{
			for ( int y = 0; y < rows; y ++)
			{
				gridSquares[x][y] = new GridSquare(x, y);
				gridSquares[x][y].setSize(20, 20);
				gridSquares[x][y].setColor();
				
				gridSquares[x][y].addMouseListener(this);  // MouseListener to the squares created
				bottomPanel.add(gridSquares[x][y]);
			}
		}
		
		// adding the top and bottom panels to the main frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.CENTER);	
		
		//this allows user to close the window to quit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	
	/*
	 *  This method implements the ActionListnerInterface  to handle the action performed in the GUI 
	 */
	public void actionPerformed(ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();
				
		//Players cannot start until "New Game" has been selected
		if ( selected.equals(topButton) )
		{
			
			gameBegun = true;
			
			//A player is randomly chosen to begin play
			Random rand = new Random();
			playerNum = rand.nextInt(2) + 1;
			nextPlayer = playerNum;
			
			//the user is told who should begin to play
			instructionLabel.setText("Player "+playerNum+"'s turn...");
		
			//all the squares will turn to white, if new game button is selected
			for ( int x = 0; x < columns; x ++)
			{
				for ( int y = 0; y < rows; y ++)
				{
					gridSquares [x][y].setColor();
				}
			}
			
			
			
			
		}
	}


	// Mouse Listener events
	public void mouseClicked(MouseEvent mevt)
	{
		// get the object that was selected in the gui
		Object selected = mevt.getSource();
		
		/*
		 * instanceof is being used here so that the selection of any of the gridsquares can be covered
		 * with just one piece of code.
		 */
		
		GridSquare square = (GridSquare) selected;
		if (gameBegun == true) 
		{
		// if a gridsquare is selected then switch its color
			if (selected instanceof GridSquare)
			{   
				if (square.getColor() == Color.white)
					{   
					    int playerNumber = nextPlayer;     // the play switches between 02 players
						square.switchColor(playerNumber);
						if (nextPlayer == 1)                
						{
							nextPlayer = 2;
						}
						else {
							nextPlayer = 1;
						}
						playerNumber = nextPlayer;
						
						
						
						//the user is told whose turn it is
						instructionLabel.setText("Player "+playerNumber+"'s turn...");
					    
						
			            
					}
			}
		
		
		//Checking if the player has selected a neighbouring square
		int x = square.getXcoord();   //the x coordinate of the currently selected square
        int y = square.getYcoord();   //the y coordinate of the currently selected square
        for(int i = (x-1); i <= (x+1); i++)
        {
        	for(int j = (y-1); j <= (y+1); j++)
        	{
        		if ((i>=0 && i<=3) && (j>=0 && j<=3))
        		{
        			if(!(x==i && y==j))      
        			{
        				if (gridSquares[i][j].getColor().equals(square.getColor()))
        				{   
        					
        					instructionLabel.setText("Player " + nextPlayer + " wins! Click to play again>>");  //Announcing the winner of the game
        					gameBegun = false;      //when a game is finished, players cannot select anymore squares
        				}
        			}
        		}
        	 }
          }
		}
	}
	


	
	// not used but must be present to fulfil MouseListener contract
		public void mouseEntered(MouseEvent arg0){}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}

}
