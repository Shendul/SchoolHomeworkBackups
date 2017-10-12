// CSCI 310 Advanced Algorithms
// Sebastian van Delden
//
// Skeleton Code for Eight Puzzle Assignment
//

public class BoardState implements Comparable{
	private int[] currentState;
	private BoardState parent;
	private int g;
	private int h;
	
	public BoardState(int[] data){
		this.currentState = new int[9];
		for(int i = 0;i < 9;++i){
			this.currentState[i] = data[i];
		}
		this.parent = null;
		this.g = 0;
		this.h = 0;
	}
	
	public int compareTo(Object item){
		// You must implement this method
		// Since we are implementing the A* algorithm,
		// this method needs to compare the g+h in both objects.
		int f = this.g + this.h;
		return 0;
	}
	

	public boolean equals(Object item){
		// You must implement this method
		// The objects are equal if the currentState[] arrays
		// are identical
		int[] itemState =  ((BoardState) item).getCurrentState();
		for (int i = 0; i < 9; i++) {
			if(this.currentState[i] != itemState[i]) {
				return false;
			}
		}
		return true;
	}
	

	public String toString(){
		// You need to implement this method
		// See example output to determine how to implement this method
		return "";
	}
	
	
	public int[] getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int[] currentState) {
		this.currentState = currentState;
	}

	public BoardState getParent() {
		return parent;
	}

	public void setParent(BoardState parent) {
		this.parent = parent;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

 
}
