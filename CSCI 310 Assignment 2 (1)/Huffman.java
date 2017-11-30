import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	        // create the tree txt file.
	        try {
				File file = new File("huffmanTree.txt");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(tree.treeString);
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        // Create compressed file. using getcode on each character to build a new str
	        try {
				File file = new File( textFileName + ".compressed");
				FileWriter fileWriter = new FileWriter(file);
				for(int w = 0; w < fullFile.length(); w++) {
					
					String c = String.valueOf(fullFile.charAt(w));
					  
					if (c.equals(" ")) {
					   c = "\\s";
					} else if (c.equals("\n")){
					   c = "\\n";
					}
					fileWriter.write(tree.getCode(tree.root, c, 0, ""));
				}
				
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 
	 
	 public static void decompress(String textFileName) {
		 // read in compressed file and tree file, recreate the tree .
		 
		// This will reference one line at a time
	        String line = null;
	        String total = null;
	        int num = 0;
	        String fullFile = "";

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader("huffmanTree.txt");

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            total = bufferedReader.readLine(); // ignore first
	            while((line = bufferedReader.readLine()) != null) {
	                //System.out.println(line);
	            	num = Integer.parseInt( line.substring(2, line.length()));
	            	for (int i = 0; i < num; i++) {
	            		fullFile += line.charAt(0);
	            	}
	            	//System.out.println(fullFile);
	                
	            }   

	            // Always close files.
	            bufferedReader.close();  
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		"huffmanTree.txt" + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + "huffmanTree.txt" + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
	        
	        HuffmanTree tree = new HuffmanTree(new PriorityQueueLinkedList(),
					fullFile);
	        
	        // now decompress using that tree...
	        
	     // This will reference one line at a time
	        line = null;
	        fullFile = "";

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(textFileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            
	            while((line = bufferedReader.readLine()) != null) {
	                fullFile += line;
	                
	            }   

	            // Always close files.
	            bufferedReader.close();  
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
	        
	        // parse file
	        try {
				File file = new File( "TestOne" + ".decompressed");
				FileWriter fileWriter = new FileWriter(file);
				
				for(int p = 0; p < fullFile.length(); p += 2) {
		        	String toWrite = tree.getCharacter(tree.root,fullFile.substring(p, p+2), 0, "");
		        	//System.out.println(toWrite);
		        	
		        	if (toWrite.equals("\\s")) 
		        			toWrite = " ";
		        	fileWriter.write(toWrite);
		        }
				
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	 }
}
