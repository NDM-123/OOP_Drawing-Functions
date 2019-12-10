package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

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
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String inp = reader.readLine();
			String f ="f(x):";
			while(inp!=null) {
				inp=inp.replaceAll("\\s+","");
				inp = inp.substring(inp.indexOf("f(x):")+f.length());
				ComplexFunction cf = new ComplexFunction(inp);
				fun.add(cf);
				inp=reader.readLine();
			}
			reader.close();
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
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.setPenColor(Color.BLUE);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(),i ,rx.get_max(), i );
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.010);
		StdDraw.setFont(new Font("Ariel", Font.PLAIN, 14));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		for (int i = 0; i < fun.size(); i++) {
			double interval = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			int R = (int)(Math.random()*256);
			int G = (int)(Math.random()*256);
			int B= (int)(Math.random()*256);
			Color color = new Color(R, G, B);
			StdDraw.setPenColor(color);
			for (double j = rx.get_min(); j < rx.get_max(); j+=interval)
			{
				function a = fun.get(0);
				StdDraw.line(j, a.f(j), j+interval, a.f(j+interval));
			}
		}
	}		@Override
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


