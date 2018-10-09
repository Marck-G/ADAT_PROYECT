package resouce.controller;

import java.util.ArrayList;

public class DataDecoder<T> {
	
	private ArrayList<ArrayList<String>> obj;
	
	public DataDecoder (){
		obj = new ArrayList<>();
	}
	
	public DataDecoder ( ArrayList<T> data ){
		this.obj = obj;
	}
		
	public ArrayList<ArrayList<String>> getArrayList(){
		return obj;
	}
	
	public void addObj( T obj ) {
		
	}
	
	public String[][] getDataMatrix(){
		ArrayList<String[]> aux = new ArrayList<String[]>();
		for (ArrayList<String> a : obj) {
			String[] aux1;
			a.toArray(aux1);
			aux.add( aux1 );
		}
		String [][] out;
		return  out;
	}

}
