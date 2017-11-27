import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Huffman {

	
	 public static void compress(String textFileName) {

	        // This will reference one line at a time
	        String line = null;
	        String fullFile = "";

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(textFileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                //System.out.println(line);
	                fullFile += line + "\n";
	                
	            }   

	            // Always close files.
	            bufferedReader.close();  
	            fullFile = fullFile.substring(0, fullFile.length() - 1);
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		textFileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + textFileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
	        
	        HuffmanTree tree = new HuffmanTree(new PriorityQueueLinkedList(),
					fullFile);
	        tree.printAllCodes(tree.root,0);
	 }
	 
	 public static void decompress(String textFileName) {
		 // read in compressed file and tree file, recreate the tree .
	 }
}
