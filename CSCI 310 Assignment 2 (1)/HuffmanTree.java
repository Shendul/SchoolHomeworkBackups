import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class HuffmanTree  implements Comparable {
	
	public HuffmanNode root;
	PriorityQueue<HuffmanNode> huffmanTreeQueue;
	String treeString;
	
	public HuffmanTree(PriorityQueue<HuffmanNode> queue, String s) { 
		// TODO write this.
		huffmanTreeQueue = queue;
		// TODO maybe move this up a level so that i can read in the tree.txt file.
		HashMap<String,Double> map = new HashMap<String,Double>();  
		this.treeString = "";
		this.treeString += s.length() + "\n";
		for(int i = 0; i < s.length(); i++){
		   String c = String.valueOf(s.charAt(i));
		   
		   if (c.equals(" ")) {
			   c = "\\s";
		   } else if (c.equals("\n")){
			   c = "\\n";
		   }
		   
		   Double val = map.get(new String(c));
		   if(val != null){
		     map.put(c, new Double(val + (double)1/s.length()));
		   } else {
		     map.put(c,(double)1/s.length());
		   }
		}
		
		for (Map.Entry<String, Double> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    this.treeString += key + " " + (int)(value*s.length()) + "\n";
		    huffmanTreeQueue.PriorityEnqueue(new HuffmanNode(key,value));
		}
		// All characters have been queued up, now begin algorithm
		while(huffmanTreeQueue.getData().size() > 1) {
			HuffmanNode Right = huffmanTreeQueue.PriorityDequeue();
			HuffmanNode Left = huffmanTreeQueue.PriorityDequeue();
			HuffmanNode merged = mergeTrees(Left,Right);
			huffmanTreeQueue.PriorityEnqueue(merged);
		}
		// TODO make sure root is set, maybe dequeue and put into root then enqueue again?
		this.root = huffmanTreeQueue.PriorityDequeue();
		huffmanTreeQueue.PriorityEnqueue(this.root);
	
			
		
	}
	
	public HuffmanNode mergeTrees(HuffmanNode LeftTree, HuffmanNode Right){
		Double newWeight = Right.weight + LeftTree.weight;
		HuffmanNode newNode = new HuffmanNode(" ",newWeight); // space will be used for new nodes.
		newNode.right = Right;
		newNode.left = LeftTree;
		return newNode;
		
	}
	public void printAllCodes(HuffmanNode root, int code){
	// ..... Left as exercise
		//Pre-order depthFirst should give us leftmost nodes.
		if (root == null) {
			return;
		}
		if (!root.string.equals(" ")) {
			System.out.println(code);
			code++;
		}
		printAllCodes(root.left, code);
		printAllCodes(root.right, code);
	}
	
	public String getCode(String string){
	// Left as exercise
		
	return null;
	}
	public int compareTo(Object arg0) {
	// ..... Left as exercise
	return 0;
	}

}
