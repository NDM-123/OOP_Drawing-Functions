package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom> polynom = new ArrayList<Monom>();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {							//default constructor 
		Monom m = new Monom("0");
		this.polynom.add(m);

	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {				//plynom constructor from a string
		int i = 0;
		while (i<s.length()) {
			String a = "";
			while(i<s.length()) {
				a+=s.charAt(i);
				i++;
				if(i<s.length()&&(s.charAt(i) == '+' || s.charAt(i) == '-'))
					break;
			}
			Monom m = new Monom(a);
			this.add(m);
			if(i<s.length() && s.charAt(i) == '+')
				i++;
		}
	}
	@Override
	public double f(double x) {							//returns a value in a function for a given double
		double ans=0;
		for (int i = 0; i < polynom.size(); i++) {
			ans += polynom.get(i).f(x);
		}
		return ans;
	}

	@Override
	public void add(Polynom_able p1) {					//adds two polynoms together
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			this.add(it.next());
		}

	}

	@Override
	public void add(Monom m1) {							//adds a monom to polynom
		for (int i = 0; i < polynom.size(); i++) {
			if(this.polynom.get(i).get_power() == m1.get_power()) {
				this.polynom.get(i).add(m1);
				return;
			}
		}
		polynom.add(m1);
	}
	@Override
	public void substract(Polynom_able p1) {			//subtract polynom from polynom
		if(!p1.equals(this)) {
			Monom m = new Monom("-1");
			Iterator<Monom> it = p1.iteretor();
			while(it.hasNext()) {
				it.next().multipy(m);;
			}
			Iterator<Monom> it2 = p1.iteretor();
			while(it2.hasNext()) {
				this.add(it2.next());
			}
		}
		polynom.clear();
		Monom n = new Monom("0");
		polynom.add(n);
		return;
	}

	@Override
	public void multiply(Polynom_able p1) {						//multiply two polynoms
		ArrayList<Monom> polynom2 = new ArrayList<Monom>();
		for (int i = 0; i < polynom.size(); i++) {
			Iterator<Monom> it = p1.iteretor();
			while(it.hasNext()) {
				polynom2.add(it.next().multipy2(polynom.get(i)));
			}
		}
		for (int i = 0; i < polynom2.size(); i++) {
			if(i<polynom.size())
				this.polynom.set(i ,polynom2.get(i));
			else
				this.add(polynom2.get(i));
		}

	}

	@Override
	public boolean equals(Object p) {					//checks if the Object is equal to Polynom
		if(p instanceof Polynom) {
			Polynom p1 =(Polynom)p;
			Iterator<Monom> it0 = p1.iteretor();
			int count = 0;
			for (;it0.hasNext(); count++) {
				it0.next();		
			}
			if(count != polynom.size()) {
				return false;
			}
			boolean[] monoms =new boolean[polynom.size()];
			int i=0;
			for (int j = 0; j < polynom.size(); j++) {
				Iterator<Monom> it1 = p1.iteretor();
				while(it1.hasNext()) {
					if(polynom.get(j).equals( it1.next() ) ) {
						monoms[i]=true;
						break;
					}
				}
				i++;
			}
			for (int j = 0; j < monoms.length; j++) {
				if(monoms[j]==false)return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isZero() {								//checks if a polynom is equal to zero
		boolean b = true;
		for (int i = 0; i < polynom.size(); i++) {
			if(polynom.get(i).get_coefficient()!=0) {
				b = false;
				break;
			}
		}
		return b;
	}

	@Override
	public double root(double x0, double x1, double eps) {					//find the root of the function(where the function meets the x axis)
		try {
			if(f(x0)*f(x1)>0) throw new RuntimeException();
			double low = x0;
			double high = x1;
			double mid=(high+low)/2;
			double lowf;
			while((high-low)>eps) {
				mid = (high+low)/2;
				lowf=f(low);
				if((lowf*f(mid))>0) {									//the same sign
					low=mid;
				}
				else {													//not the same sign
					high=mid;
				}
			}
			return mid;
		}catch(Exception e) {
			System.out.println("err the details aren't in the range:f(x0)*f(x1)<=0");
			return 0;
		}
	}
	@Override
	public Polynom_able copy() {					//creates a deep copy of a polynom
		Polynom cop = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom temp = new Monom(it.next());
			cop.add(temp);
		}
		return cop;
	}

	@Override
	public Polynom_able derivative() {				//returns  the derivative of the polynom
		Polynom ans = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			ans.add(it.next().derivative());
		}
		return ans;
	}

	@Override
	public double area(double x0, double x1, double eps) {			//calculate the area between the function
		double sum = 0;
		for (double i = x0; i < x1; i += eps) {
			if(f(i)>0) {												//above the x axis
				sum +=  f(i)*eps;
			}
		}
		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return polynom.iterator();
	}
	@Override
	public void multiply(Monom m1) {									//multiply one monom with the other
		for(int i=0; i < polynom.size();i++) {
			polynom.get(i).multipy(m1);
		}

	}
	public String toString() {											//to string method
		String ans = "";
		for (int i = 0; i < polynom.size(); i++) {
			if(polynom.get(i).get_coefficient()>0 &&i>0 )
				ans += "+";
			ans += polynom.get(i);

		}
		return ans;
	}
	public void initFromFile(String file) throws IOException{

	}
	public void saveToFile(String file) throws IOException{

	}
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

	}
	public void drawFunctions(String json_file) {

	}
	@Override
	public function initFromString(String s) {
		function a = new Polynom(s);
		return a;
	}

}
