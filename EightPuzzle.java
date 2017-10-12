// CSCI 310 Advanced Algorithms
// Sebastian van Delden
//
// Skeleton Code for Eight Puzzle Assignment
//
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class EightPuzzle  {
	
	PriorityQueue<BoardState> openNodesQueue;	
	Map<String, BoardState> closedNodes;
	BoardState GoalState;
	int AllPossibleStates[][];
	int index;
	
	public EightPuzzle(PriorityQueue<BoardState> dataStructure, int[] Goal){
		
		openNodesQueue = dataStructure; // Nice right? Polymorphism...
		GoalState = new BoardState(Goal);
		closedNodes = new HashMap<String,BoardState>();
		index = 0;
		generateAllPossibleStates();
		
		long totalRunTime = 0;
		int statesSolved = 0;
		
		for(int i = 0;i < AllPossibleStates.length;++i){
		
			if(checkReachable(AllPossibleStates[i], GoalState.getCurrentState())){
				// Do the EightPuzzle/A* algorithm:
				//
				// You need to finish this code here
				//System.out.println("made it past check reachable");
				BoardState checkState = new BoardState(AllPossibleStates[i]);
				openNodesQueue.PriorityEnqueue(checkState);
				boolean goalFound = false;
				int count = 1;
				while(!goalFound) {
					BoardState currentState = openNodesQueue.PriorityDequeue();
					if(currentState.equals(GoalState)) {
						printPath(currentState, count);
						goalFound = true;
					} else {
						count++; //dunno what this for yet prob needs to change.
						String hashKey = makeKey(currentState);
						closedNodes.put(hashKey, currentState);
						int childCount = 0;
						BoardState childOne = null;
						BoardState childTwo = null;
						BoardState childThree = null;
						BoardState childFour = null;
						//System.out.println(currentState.getCurrentState()[0]);
						if(currentState.getCurrentState()[0] == 0) {
							childOne = move(currentState, -1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, 1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childCount += 2;
						} else if (currentState.getCurrentState()[1] == 0) {
							childOne = move(currentState, 0, 1);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, -1, 0);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childThree = move(currentState, 0, -1);
							childThree.setH(manhattan(childThree.getCurrentState(), GoalState.getCurrentState()));
							childThree.setG(count - 1);
							childThree.setParent(currentState);
							childCount += 3;
						} else if (currentState.getCurrentState()[2] == 0) {
							childOne = move(currentState, -1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, -1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childCount += 2;
						} else if (currentState.getCurrentState()[3] == 0) {
							childOne = move(currentState, 1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, 1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childThree = move(currentState, -1, 0);
							childThree.setH(manhattan(childThree.getCurrentState(), GoalState.getCurrentState()));
							childThree.setG(count - 1);
							childThree.setParent(currentState);
							childCount += 3;
						} else if (currentState.getCurrentState()[4] == 0) {
							childOne = move(currentState, 1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, 1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childThree = move(currentState, -1, 0);
							childThree.setH(manhattan(childThree.getCurrentState(), GoalState.getCurrentState()));
							childThree.setG(count - 1);
							childThree.setParent(currentState);
							childFour = move(currentState, 0, -1);
							childFour.setH(manhattan(childFour.getCurrentState(), GoalState.getCurrentState()));
							childFour.setG(count - 1);
							childFour.setParent(currentState);
							childCount += 4;
						} else if (currentState.getCurrentState()[5] == 0) {
							childOne = move(currentState, 1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, -1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childThree = move(currentState, -1, 0);
							childThree.setH(manhattan(childThree.getCurrentState(), GoalState.getCurrentState()));
							childThree.setG(count - 1);
							childThree.setParent(currentState);
							childCount += 3;
						} else if (currentState.getCurrentState()[6] == 0) {
							childOne = move(currentState, 1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, 1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childCount += 2;
						} else if (currentState.getCurrentState()[7] == 0) {
							childOne = move(currentState, 1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, -1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childThree = move(currentState, 0, 1);
							childThree.setH(manhattan(childThree.getCurrentState(), GoalState.getCurrentState()));
							childThree.setG(count - 1);
							childThree.setParent(currentState);
							childCount += 3;
						} else if (currentState.getCurrentState()[8] == 0) {
							childOne = move(currentState, 1, 0);
							childOne.setH(manhattan(childOne.getCurrentState(), GoalState.getCurrentState()));
							childOne.setG(count - 1);
							childOne.setParent(currentState);
							childTwo = move(currentState, 0, -1);
							childTwo.setH(manhattan(childTwo.getCurrentState(), GoalState.getCurrentState()));
							childTwo.setG(count - 1);
							childTwo.setParent(currentState);
							childCount += 2;
						}
						
					
						if(!closedNodes.containsValue(childOne) && childCount >= 1) {
							openNodesQueue.PriorityEnqueue(childOne);
						} if (!closedNodes.containsValue(childTwo) && childCount >= 2) {
							openNodesQueue.PriorityEnqueue(childTwo);
						} if (!closedNodes.containsValue(childThree) && childCount >= 3) {
							openNodesQueue.PriorityEnqueue(childThree);
						} if (!closedNodes.containsValue(childFour) && childCount >= 4) {
							openNodesQueue.PriorityEnqueue(childFour);
						}
						
					}
				}
				
				
				statesSolved++;
			} // end if
		} // end for
		
	} // end EightPuzzle
	
	
	private String makeKey(BoardState current){
		// You need to implement this
		// Convert the board state array into a String
		// so that it can be used as the key value in the closedNodes
		// hashmap.
		int hash = current.hashCode();
		String hashS = "" + hash;
		return hashS;
	}
	
	private BoardState move(BoardState current, int rowMove, int colMove){
		// You need to implement this
		// Given a board state, return the new board state given
		// the rowMove/ColMove parameters which will be 0, 1, or -1.
		// rowMove 1 = up, -1 = down, colMove 1 = right, -1 = left.
		BoardState newState = new BoardState(current.getCurrentState());
		int index0 = -1;
		int search = 0;
		while(index0 == -1) {
			if(current.getCurrentState()[search] == 0) {
				index0 = search;
			} else {
				search++;
			}
		}
		if (rowMove == 0 && colMove == -1) { // go left
			int toSwap = newState.getCurrentState()[search - 1];
			newState.getCurrentState()[search - 1] = newState.getCurrentState()[search];
			newState.getCurrentState()[search] = toSwap;
		} else if(rowMove == 0 && colMove == 1) { // go right
			int toSwap = newState.getCurrentState()[search + 1];
			newState.getCurrentState()[search + 1] = newState.getCurrentState()[search];
			newState.getCurrentState()[search] = toSwap;
		} else if(rowMove == -1 && colMove == 0) { // go down
			int toSwap = newState.getCurrentState()[search + 3];
			newState.getCurrentState()[search + 3] = newState.getCurrentState()[search];
			newState.getCurrentState()[search] = toSwap;
		} else if(rowMove == 1 && colMove == 0) { // go up
			int toSwap = newState.getCurrentState()[search - 3];
			newState.getCurrentState()[search - 3] = newState.getCurrentState()[search];
			newState.getCurrentState()[search] = toSwap;
		} else {
			System.out.println("system tried to make an invalid move");
		}
		return newState;
	}
	
	public boolean checkReachable(int state1[], int state2[]){
		// You need to implement this
		// Returns true if any two states are reachable
		int inversionCount = 0;
		int inversionCount2 = 0;
		
		for(int i = 0; i < 9; i++) {
			for(int j = i+1; j < 9; j++) {
				if (state1[i] > state1[j] && state1[i] != 0 && state1[j] != 0) {
					inversionCount++;
				}
				
				if (state2[i] > state2[j] && state2[i] != 0 && state2[j] != 0) {
					inversionCount2++;
				}
			}
		}
		
		
		if ( (inversionCount % 2) == (inversionCount2 % 2) ) { // checking for even or odd and if both same
			return true;
		} else {
			return false;
		}
	}
	
	public int manhattan(int[] initialState, int[] endState){
		// You need to implement this
		// Return manhattan distance between these two states
		int manDist = 0;
		int initRow = 0;
		int initCol = 0;
		int endRow = 0;
		int endCol = 0;
		for (int i = 0; i < initialState.length; ++i) {
			if (initialState[i] != endState[i] && initialState[i] != 0 && endState[i] != 0) {
				if(i == 0) {
					initRow = 1;
					initCol = 1;
				} else if (i == 1) {
					initRow = 1;
					initCol = 2;
				} else if (i == 2) {
					initRow = 1;
					initCol = 3;
				} else if (i == 3) {
					initRow = 2;
					initCol = 1;
				} else if (i == 4) {
					initRow = 2;
					initCol = 2;
				} else if (i == 5) {
					initRow = 2;
					initCol = 3;
				} else if (i == 6) {
					initRow = 3;
					initCol = 1;
				} else if (i == 7) {
					initRow = 3;
					initCol = 2;
				} else if (i == 8) {
					initRow = 3;
					initCol = 3;
				}
				
				for (int j = 0; j < endState.length; j++) {
					if (initialState[i] == endState[j] && initialState[i] != 0 && endState[j] != 0) {
						if(j == 0) {
							endRow = 1;
							endCol = 1;
						} else if (j == 1) {
							endRow = 1;
							endCol = 2;
						} else if (j == 2) {
							endRow = 1;
							endCol = 3;
						} else if (j == 3) {
							endRow = 2;
							endCol = 1;
						} else if (j == 4) {
							endRow = 2;
							endCol = 2;
						} else if (j == 5) {
							endRow = 2;
							endCol = 3;
						} else if (j == 6) {
							endRow = 3;
							endCol = 1;
						} else if (j == 7) {
							endRow = 3;
							endCol = 2;
						} else if (j == 8) {
							endRow = 3;
							endCol = 3;
						}
						
						manDist += Math.abs((endRow - initRow) + (endCol - initCol));
					}
				}
			}
		}
		return manDist;
	}

	public int printPath(BoardState current, int count){
		// You must implement this
		// Prints out path to goal state
		// Must be recursive
		if ( count > 1) {
			for (int i = count; i > 0; i--) {
				BoardState parent = current.getParent();
				printPath(parent, i);
				for(int j = 0; j < 9; j++) {
					if (j % 3 == 0) { // add newlines every row
						System.out.println();
					}
					System.out.print(current.getCurrentState()[j]);
				}
				System.out.println("---");
				System.out.println();
			}
		} else {
			for(int j = 0; j < 9; j++) {
				if (j % 3 == 0) { // add newlines every row
					System.out.println();
				}
				System.out.print(current.getCurrentState()[j]);
			}
			System.out.println();
			System.out.println("---");
		}
		
		return 0;
	}
	
	private void generateAllPossibleStates(){
		System.out.print("Generating All Possible States... ");
		AllPossibleStates = new int[362880][9];
		// You must implement the rest of this method
		// Good luck! :)
		int[] basicBoard = new int[] {0,1,2,3,4,5,6,7,8};
		heapPermutation(basicBoard, basicBoard.length, basicBoard.length, AllPossibleStates);
	}
	
	 //Generating permutation using Heap Algorithm
    void heapPermutation(int a[], int size, int n, int AllPossibleStates[][])
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
        	//System.out.println("Generating " + index + "th permutation");
        	for (int i = 0; i < 9; i++) {
        		AllPossibleStates[index][i] = a[i];
        	}
        	//System.out.println(Arrays.toString(AllPossibleStates[index]));
        	
        	index++;
        
        }
 
        for (int i=0; i<size; i++)
        {
            heapPermutation(a, size-1, n, AllPossibleStates);
 
            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                int temp = a[0];
                a[0] = a[size-1];
                a[size-1] = temp;
            }
 
            // If size is even, swap ith and last
            // element
            else
            {
                int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;
            }
        }
    }
  
	
}
