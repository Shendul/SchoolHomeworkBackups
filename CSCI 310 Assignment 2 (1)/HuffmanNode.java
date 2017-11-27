
public class HuffmanNode  implements Comparable {
	
	public double weight;
	public HuffmanNode left, right;
	public String string;
	
	public HuffmanNode(String string, double weight) {
		// TODO make sure this is what i need to do.
		this.weight = weight;
		this.string = string;
		this.left = null;
		this.right = null;
	}
	
	public int compareTo(Object arg0) {
		// used to compare weights of a node.
		int toReturn = 0;
		HuffmanNode comparator = (HuffmanNode) arg0;		
		if (comparator.weight == this.weight) {
			toReturn = 0;
		} else if (comparator.weight > this.weight) {
			toReturn = -1;
		} else {
			toReturn = 1;
		}
		
		return toReturn;
	}

}
