
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "TestOne";
		Huffman.compress(fileName);
		
		fileName = "TestOne.compressed";
		Huffman.decompress(fileName);
		
		
		System.out.println();
	}

}
