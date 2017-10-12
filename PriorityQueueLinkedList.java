// CSCI 310 Advanced Algorithms
// Sebastian van Delden
//
// Skeleton Code for Eight Puzzle Assignment
//

import java.util.ArrayList;

public class PriorityQueueLinkedList<T extends Comparable<T>> extends PriorityQueue<T>{
	
	PriorityQueueLinkedList(){
		super();
	}
	
	public T PriorityDequeue(){
		// You must implement this...
		ArrayList<T> data = this.getData();
		T head = data.get(0);
		data.remove(0);
		return head;		
	}
	
	public void PriorityEnqueue(Comparable<T> item){
		// You must implement this...
		ArrayList<T> data = this.getData();
		int oldSize = data.size();
		int index = oldSize/2;
		if (data.isEmpty()) {
			data.add(0, (T) item); // add to front of queue if empty
		} else {
			boolean inserted = false;
			int positionCheck = 0;
			while (!inserted) {
				if (item.compareTo(data.get(index)) == 0) { // equal items
					// don't know what to do here.
					
				} else if  (item.compareTo(data.get(index)) <= -1) { // item is less than index
					if (positionCheck == 2) {
						data.add(index, (T) item); // found spot
						inserted = true;
					} else if (index == 0) { // you are the new first in queue.
						data.add(index, (T) item);
						inserted = true;
					}
					positionCheck = 1; // gone left
					index --;
				} else if (item.compareTo(data.get(index)) >= 1) { // item is greater than index 
					if (positionCheck == 1) {
						index++; // go back to where we want to insert
						data.add(index, (T) item); // found spot
						inserted = true;
					} else if (index == oldSize - 1) { // you are to be added last in queue
						data.add((T) item);
						inserted = true;
					}
					positionCheck = 2; // gone right
					index ++;
				}
			}
			
		}
	}
	
}
