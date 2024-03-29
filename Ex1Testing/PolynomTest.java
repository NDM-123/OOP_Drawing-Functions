package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PolynomTest {
	String[] monoms = {"-2", "3","-4.1","0","5.2","-x","5x","-2.1x","4.1x","-x^2","5x^3","-4.3x^4","5.3x^6","0x^3","0x"};
	String[] polynoms = {"x^2+2x+1","-x^2-2x-1","x^3+1","-x^3-1","x^5+2x^4+3x^3+4x^2+5x+6"};

	@BeforeEach
	void initEach(){
		System.out.println();
		System.out.println("*******next test******");
		System.out.println();
	}
	@Test
	void test1() {
		System.out.println("******  Test1: Test value f(x)******");
		for(int i=0;i<polynoms.length;i++) {				//insert a value x to polynom
			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.f(1);
			System.out.println("after inserting the value 1 to x to our polynom: "+m.f(1));
		}
		System.out.println("test complete");
	}

	@Test
	void test2() {
		System.out.println("******  Test2: Add Polynom able******");
		for(int i=0;i<polynoms.length;i++) {				//add monom
			Monom m = new Monom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.add(m);
			System.out.println("after the adding to our polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test3() {
		System.out.println("******  Test3: Add Monoms with Polynom******");
		for(int i=0;i<monoms.length;i++) {				//add monom
			Monom m = new Monom(monoms[i]);
			System.out.println("the monom is: "+m);
			m.add(m);
			System.out.println("after the adding to our polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test4() {
		System.out.println("******  Test4: Polynom Substract******");
		for(int i=0;i<polynoms.length;i++) {				//subtract polynom
			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.substract(m);
			System.out.println("after the adding to our polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test5() {
		System.out.println("******  Test5: Multiply Polynom able******");
		for(int i=0;i<polynoms.length;i++) {				//Multiply polynom
			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.multiply(m);
			System.out.println("after the adding to our polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test6() {
		System.out.println("******  Test6: Equals Object******");

		Polynom m = new Polynom("2x^2+2x+2");
		Polynom m1 = new Polynom("2x+2x^2+2");
		Polynom m2 = new Polynom("2+2x^2+2x");
		Polynom m3 = new Polynom("2.1x^2+2x+2");
		System.out.println("is "+m +" equal to: "+m1 +"? " +m.equals(m1));
		System.out.println("is "+m1 +" equal to: "+m2 +"? " +m1.equals(m2));
		System.out.println("is "+m2 +" equal to: "+m3 +"? " +m2.equals(m3));
		System.out.println("test complete");
	}

	@Test
	void test7() {
		System.out.println("******  Test7: Is Zero******");
		for(int i=0;i<polynoms.length;i++) {				//isZero polynom
			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.isZero();
			System.out.println("is the polynom zero? "+m);
		}
		System.out.println("test complete");
	}
	@Test
	void test8() {
		System.out.println("******  Test8: Root Monom******");
		for(int i=0;i<monoms.length;i++) {				//root monom
			Monom m = new Monom(monoms[i]);
			System.out.println("the monom is: "+m);
			m.root(-1, 1, 0.01);
			System.out.println("the root of our monom: "+m);
		}
		System.out.println("test complete");
	}
	@Test
	void test9() {
		System.out.println("******  Test9: Root Polynom******");
		for(int i=0;i<polynoms.length;i++) {				//root polynom

			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.root(-1, 1, 0.01);
			System.out.println("the root of our polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test10() {
		 //test deep copy
		System.out.println("******  Test10 : Copy and creat from string with space  ******");
		Polynom_able p1 = new Polynom("-4.7x^2-1.0x+6.0");
		Polynom_able p2 = (Polynom_able) p1.copy();
		if ( !p1.equals(p2)) {
			System.out.println("fail copy");
		}
		p2.add(new Monom("x^2"));
		if ( p1.equals(p2)) {
			System.out.println("fail deep copy");
		}
		System.out.println("test complete");
	}

	@Test
	void test11() {
		System.out.println("******  Test11: Derivative Monom******");
		for(int i=0;i<monoms.length;i++) {				//derivative of monom
			Monom m = new Monom(monoms[i]);
			System.out.println("the monom is: "+m);
			m.derivative();
			System.out.println("after derivating of monom: "+m);
		}
		System.out.println("test complete");
	}
	@Test
	void test12() {
		System.out.println("******  Test12: Derivative Polynom******");
		for(int i=0;i<polynoms.length;i++) {				//derivative of polynom
			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.derivative();
			System.out.println("after derivating of polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test13() {
		System.out.println("******  Test13: Area Monom******");
		for(int i=0;i<monoms.length;i++) {				//area monom
			Monom m = new Monom(monoms[i]);
			System.out.println("the monom is: "+m);
			m.area(-1, 1, 0.001);
			System.out.println("the area of our monom: "+m);
		}
		System.out.println("test complete");
	}
	@Test
	void test14() {
		System.out.println("******  Test14: Area Polynom******");
		for(int i=0;i<polynoms.length;i++) {				//area polynom
			Polynom m = new Polynom(polynoms[i]);
			System.out.println("the polynom is: "+m);
			m.area(-1, 1, 0.001);
			System.out.println("the area of our polynom: "+m);
		}
		System.out.println("test complete");
	}

	@Test
	void test15() {
		System.out.println("******  Test15: Multiply Monom******");
		boolean flag =true;;
		for (int i = 0; i < monoms.length; i++) {
			Monom a = new Monom(monoms[i]);
			Monom b = new Monom(monoms[i]);
			a.multipy(a);
			if(a.toString()==b.toString()) flag=false;
		}
		if(flag)System.out.println("Method work");
		System.out.println("test complete");
	}

	
	@Test
	void test16() {
		System.out.println("******  Test16: init from string******");
		for (int i = 0; i < polynoms.length; i++) {
			Polynom a = new Polynom(polynoms[i]);
			System.out.println(a.toString());
		}
		System.out.println("test complete");
	}
	@Test
	void test17() {
		System.out.println("******  Test17: InvalidInput******");
		String[] polynoms = {"x^e","x^2+4-ax","x+s","2^x","4x^3r","x^e4"};		//polynom is made from monoms thats why an invalid polynom string will print the parts that are valid monoms
		for(int i=0;i<polynoms.length;i++) {
			Polynom m = new Polynom(polynoms[i]);
			String s = m.toString();
			m = new Polynom(s);
			System.out.println("the Monom is: "+m);
		}
		System.out.println("test complete");
	}


}
