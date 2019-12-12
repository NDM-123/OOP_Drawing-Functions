package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.Functions_GUI.GUI_param;


public class Functions_GUITest {
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};

	public static functions FunctionsFactory() {			//creating functions for tests
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		return ans;
	}

	@Test
	void test1(){				//test add function
		System.out.println("******  Test1: Test add function******");
		functions data = FunctionsFactory();
		function m = new Monom("x");
		function p = new Polynom("x+1");
		ComplexFunction cf =new ComplexFunction(Operation.Plus,m,p);
		if(data.add(m)!=true)System.out.println("fail");
		if(data.add(p)!=true)System.out.println("fail");
		if(data.add(cf)!=true)System.out.println("fail");
		System.out.println("test complete");
	}
	@Test
	void test2(){				//test addAll function
		System.out.println("******  Test2: Test AddAll function******");
		functions data = FunctionsFactory();
		function m = new Monom("x");
		function p = new Polynom("x+1");
		ComplexFunction cf =new ComplexFunction(Operation.Plus,m,p);
		if(data.add(m)!=true)System.out.println("fail");
		if(data.add(p)!=true)System.out.println("fail");
		if(data.add(cf)!=true)System.out.println("fail");
		if(data.addAll(data)!=true)System.out.println("fail");
		System.out.println("test complete");
		System.out.println("******  Test3: Test contain function******");				    //test3
		if(data.contains(m)!=true) System.out.println("test failed");
		System.out.println("test complete");
		System.out.println("******  Test4: Test contains all function******");				//test 4
		if(data.containsAll(data)==false)System.out.println("test failed");
		System.out.println("test complete");
		System.out.println("******  Test5: Test clear function******");						//test 5
		data.clear();
		System.out.println("test complete");
		System.out.println("******  Test6: Test is Empty function******");					//test 6
		if(data.isEmpty()!=true)System.out.println("test failed");	
		System.out.println("test complete");
		System.out.println("******  Test7: Test size function******");						//test 7
		if(data.size()!=0)System.out.println("test failed");	
		System.out.println("test complete");
	}
	@Test
	void test8 (){				//test remove function
		System.out.println("******  Test8: Test remove function******");
		functions data = FunctionsFactory();
		function m = new Monom("x");
		function p = new Polynom("x+1");
		data.add(m);
		ComplexFunction cf =new ComplexFunction(Operation.Plus,m,p);
		if(data.add(m)!=true)System.out.println("fail");
		if(data.add(p)!=true)System.out.println("fail");
		if(data.add(cf)!=true)System.out.println("fail");
		if(data.addAll(data)!=true)System.out.println("fail");
		if(data.contains(m)!=true) System.out.println("fail");
		System.out.println(data.remove(m));
		System.out.println("test complete");
		System.out.println("******  Test9: Test  removeAll function******");			//test 9
		if(data.containsAll(data)!=true) System.out.println("fail");
		System.out.println(data.removeAll(data));
		System.out.println("test complete");
	}
	@Test
	void test10(){
		System.out.println("******  Test10: Test iterator function******");
		functions data = FunctionsFactory();
		Iterator<function> iter = data.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		System.out.println("test complete");
	}
	@Test
	void test11() {
		System.out.println("******  Test11: Test Draw function******");
		functions data = FunctionsFactory();
		try {
			String JSON_param_file = "GUI_params.txt";
			data.drawFunctions(JSON_param_file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("test complete");
	}

	@Test
	void test12() {
		System.out.println("******  Test12: Test Save To File function******");
		functions data = FunctionsFactory();
		String file = "function_file3.txt";
		String file2 = "function_file4.txt";
		try {
			data.saveToFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("test complete");
	}
	@Test
	void test13() {
		System.out.println("******  Test13: Test Init From File function******");
		functions data = FunctionsFactory();
		String file = "function_file.txt";
		String JSON_param_file = "GUI_params.txt";
		Functions_GUI data2 = new Functions_GUI();
		try {
			data.saveToFile(file);
			data2.initFromFile(file);
			data2.drawFunctions(JSON_param_file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("test complete");
	}

}
