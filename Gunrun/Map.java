package Gunrun;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class Map {
	private boolean[][] isFull;
	Random random=new Random();
	/**
	 * 
	 * @param Size
	 */
	public Map(int Size) {
		isFull= new boolean[Size][Size];
		for(int i=0;i<Size;i++){
			for(int f=0;f<Size;f++){
				isFull[i][f]=random.nextBoolean();
			}
		}
		printMap(1);
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @return
	 */
	public int getSize(){
		return isFull.length;
	}
	public void setGrid(int x, int y,boolean value){
		isFull[y][x]=value;
	}
	public boolean getIsFull(int x, int y){
		return isFull[y][x];
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */

	/**
	 * 
	 * @param serial
	 */
	public void printMap(int serial){
		String output= ("Map "+serial+".txt");
		File outputFile= new File(output);
		try{
			fileWriter writer= new fileWriter(outputFile,false);
			for(int i=0;i<getSize();i++){

				for(int f=0;f<getSize();f++){
					if(isFull[f][i]){
						writer.write("[$] ");
					}else{
						writer.write("[ ] ");
					}

				}
				writer.write("\r\n"+"\n");;
			}
			writer.close();
		}catch(IOException e){

		}
	}
	/**
	 * 
	 * @author Ozzy1
	 *
	 */
	private class fileWriter extends FileWriter{

		public fileWriter(File file, boolean append) throws IOException {
			super(file, append);
			// TODO Auto-generated constructor stub
		}



	}



}