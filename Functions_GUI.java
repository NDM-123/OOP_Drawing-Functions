package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Functions_GUI implements functions{
	public LinkedList <function>  fun; 
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};


	@Override
	public boolean add(function arg0) {

		return fun.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {

		return fun.addAll(arg0);
	}

	@Override
	public void clear() {
		fun.clear();
	}

	@Override
	public boolean contains(Object arg0) {

		return fun.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return fun.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return fun.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {

		return fun.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return fun.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return fun.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return fun.retainAll(arg0);
	}

	@Override
	public int size() {
		return fun.size();
	}

	@Override
	public Object[] toArray() {
		return fun.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return fun.toArray(arg0);
	}
	public static boolean isFunction(String a) {
		try {
			ComplexFunction b = new ComplexFunction(a); 
			if(b != null)return true;	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void initFromFile(String file) throws IOException {
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(file);
			JsonElement json= gson.fromJson(reader,JsonElement.class);
			String jsonInString = gson.toJson(json);
			System.out.println(jsonInString);
			String[] f = jsonInString.split("\\r?\\n");
			System.out.println(f);
			int count =0;
			for(int i =0;i<f.length;i++) {
				if(isFunction(f[i])) {
					ComplexFunction cf = new ComplexFunction(f[i]);
					fun.add(cf);
					count++;
				}
			}
			if(count!=f.length)System.out.println("The number of functions that was accepted is: "+count +" /nThe number of functions that wasnt accepted is: "+(f.length-count));
			if(count == f.length)System.out.println("All functions were accepted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void saveToFile(String file) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File("functions.json"));

			for (int i = 0; i < fun.size(); i++) 
			{
				pw.println(fun.get(i));
			}

			pw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

	int n = resolution;
	StdDraw.setCanvasSize(width, height);
	int size = fun.size();
	double[] x = new double[n+1];
	double[][] yy = new double[size][n+1];
	double x_step = (rx.get_max()-rx.get_min())/n;
	double x0 = rx.get_min();
	for (int i=0; i<=n; i++) {
		x[i] = x0;
		for(int a=0;a<size;a++) {
			yy[a][i] = fun.get(a).f(x[i]);
		}
		x0+=x_step;
	}
	StdDraw.setXscale(rx.get_min(), rx.get_max());
	StdDraw.setYscale(ry.get_min(), ry.get_max());
	
	
	// plot the approximation to the function
	for(int a=0;a<size;a++) {
		int c = a%Colors.length;
		StdDraw.setPenColor(Colors[c]);
	
		System.out.println(a+") "+Colors[a]+"  f(x)= "+fun.get(a));
		for (int i = 0; i < n; i++) {
			StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
		}
	}	
}
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();

		//  JSON file to JsonElement, later String
		try {
			String json = gson.toJson(new FileReader(json_file));
		//	String result = gson.toJson(json);
			System.out.println(json.toString());
			String[] f = result.split("\\r?\\n");
			System.out.println(f);
			for(int i =0;i<f.length;i++) {
					ComplexFunction cf = new ComplexFunction(f[i]);
					fun.add(cf);
			}

			  System.out.println(result);
			drawFunctions(json.height,json.width,json.rx,json.ry,json.resolution);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}


