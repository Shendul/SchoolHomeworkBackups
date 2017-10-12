// CSCI 310 Advanced Algorithms
// Sebastian van Delden
//
// Skeleton Code for Eight Puzzle Assignment
//
import java.util.ArrayList;
import java.util.Collections;

public class PriorityQueueHeap<T extends Comparable<T>> extends PriorityQueue<T> {

	PriorityQueueHeap(){
		super();
	}
	
	public T PriorityDequeue(){
		// You must implement this...
		// NOTE: when swapping items in an ArrayList, 
		// use Collections.swap()....
		ArrayList<T> data = this.getData();
		T root = data.get(0); // grab root and return it.
		int end = data.size() - 1;
		if(end == 0) {
			data.remove(end);
			return root;
		}
		Collections.swap(data, 0, end); // swap root and end
		data.remove(end); // remove the end/old root
		
		int parent = 0;
		int lchild = 0;
		int rchild = 0;
		
		if(data.size() - 1 >= 2) {
			lchild = 2 * parent + 1;
			rchild = 2 * parent + 2;
		} else if (data.size() - 1 == 1) {
			lchild = 2 * parent + 1;
		} else {
			// something went wrong
		}
		boolean swapping = true;
		int smallestChild = 0; // something went wrong if this ends up being 0
		// check to see if we need to make any swaps.
		while(swapping) {
			if (lchild == 0) {
				smallestChild = rchild;
			} else if (rchild == 0) {
				smallestChild = lchild;
			} else {
				if (data.get(lchild).compareTo(data.get(rchild)) >= 1) {
					smallestChild = rchild;
				} else if (data.get(lchild).compareTo(data.get(rchild)) <= -1) {// lchild is smaller
					smallestChild = lchild;
				} else {
					// something went wrong.
				}
			}
			
			// compare smaller child with parent, if parent is greater than perform a swap.
			if (data.get(parent).compareTo(data.get(smallestChild)) >= 1) { // swap parent + child
				Collections.swap(data, smallestChild, parent);
				parent = smallestChild; // also swap index
				lchild = 2 * parent + 1;
				rchild = 2 * parent + 2;
			} else {
				swapping = false;
			}
			
			if (lchild > (data.size() - 1) && rchild > (data.size() - 1)) { 
				swapping = false;
			} else if (lchild > (data.size() - 1)){
				lchild = 0;
			} else if (rchild > (data.size() - 1)) {
				rchild = 0;
			}
		}
		
		
		return root;
	}
	
	public void PriorityEnqueue(Comparable<T> item){
		// You must implement this...
		// NOTE: when swapping items in an ArrayList, 
		// use Collections.swap()....
		ArrayList<T> data = this.getData();
		int child = data.size();
		int parent = (child - 1)/2;
		data.add((T) item); // always add to the end of the list
		boolean swapping = true;
		// check to see if we need to make any swaps.
		while(swapping) {
			if (data.get(parent).compareTo(data.get(child)) >= 1) { // swap parent + child
				Collections.swap(data, child, parent);
				child = parent; // also swap index
				parent = (child - 1)/2; // recalculate the new parent
			} else {
				swapping = false;
			}
		}
	}
	
	
}
