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
import java.util.Random;

import com.google.gson.Gson;

public class Functions_GUI implements functions{
	public LinkedList <function>  fun; 
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};

	public Functions_GUI() {
		fun = new LinkedList<function>();	
	}
	//the next codes are for setting our object to have the same attributes as a data structure
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
			//object that reads from file 
			FileReader fr = new FileReader(file);
			//object that translate the text into valid string 
			BufferedReader br = new BufferedReader(fr);
			//read the first line
			String inp = br.readLine();
			String f ="f(x)=";
			//go over the entire file
			while(inp!=null) {
				//delete lines
				inp=inp.replaceAll("\\s+","");
				//find the location of f(x) in line and then go over it  
				if(inp.indexOf(f)!=-1)	inp = inp.substring(inp.indexOf(f)+f.length());
				//create Complex function from the line
				ComplexFunction cf = new ComplexFunction(inp);
				//add the complex function to our data structure
				fun.add(cf);
				//go down to the next line
				inp=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void saveToFile(String file) throws IOException {
		try {
			//object that print lines and then creates a folder that prints into it 
			PrintWriter pw = new PrintWriter(new File("NewFunctions.json"));
			//random colors
			Random r = new Random();
			Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
		//print functions into lines 
			for (int i = 0; i < fun.size(); i++) 
			{
				pw.println(i + ") " + randomColor + "  f(x)= " + fun.get(i).toString());
				pw.flush();
			}

			pw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}
	//exceptions for parameters
	public static boolean isException(int width, int height, Range rx, Range ry, int resolution) {
		if (width<=0)return true;
		if (height<=0)return true;
		if(rx.get_min() >= rx.get_max())return true;
		if(resolution<=0)return true;

		return false;

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

		try {
			if(isException(width, height, rx, ry, resolution))throw new Exception("one of the parameters is wrong");

			double interval = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;

			setSystem(width, height, rx, ry, resolution);		//set Cartesian coordinate system

			//draw functions
			for (int i = 0; i < fun.size(); i++) {		//advancing the function 
				
				function a = fun.get(i);		//the function we want to draw
				
				//random color code from stackoverflow:https://inneka.com/programming/java/creating-random-colour-in-java/
				Random r = new Random();
				Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
			
				//draw each function into the scale by intervals
				StdDraw.setPenColor(randomColor);
					for (double j = rx.get_min(); j < rx.get_max(); j+=interval){	//advancing the interval
				
						if(a!=null)	StdDraw.line(j, a.f(j), j+interval, a.f(j+interval));		//draw the line between the intervals
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(json_file);			//read from file
			GUI_param param    = gson.fromJson(reader,GUI_param.class);		//insert the values of the parameters 
			
			//insert the values of ranges x and y
			Range rangeX      = new Range(param.Range_X[0],param.Range_X[1]);	
			Range rangeY      = new Range(param.Range_Y[0],param.Range_Y[1]);

			//draw the function with the function we built 
			drawFunctions(param.Width, param.Height, rangeX, rangeY, param.Resolution);		
			return;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void setSystem(int width, int height, Range rx, Range ry, int resolution) {
		
		//set Scale
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		//set y axis
		StdDraw.setPenColor(Color.BLUE);
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(),i ,rx.get_max(), i );
		}
		//set x axis
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		
		}
		//draw numbers for Cartesian coordinate system
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.010);
		StdDraw.setFont(new Font("Ariel", Font.PLAIN, 14));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {				//for x axis
			StdDraw.text(i, -0.20, Integer.toString(Math.toIntExact((long) i)));
		}
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {				//for y axis
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
	}
	public class GUI_param {			//GUI default parameters 
		int Width = 750;
		int Height = 750;
		int Resolution = 150;
		double[] Range_X = {-10,10};
		double[] Range_Y = {-10,10};
	}
}



