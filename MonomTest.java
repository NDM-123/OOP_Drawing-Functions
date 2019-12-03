package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {
	String[] monoms = {"-2", "3","-4.1","0","5.2","-x","5x","-2.1x","4.1x","-x^2","5x^3","-4.3x^4","5.3x^6","0x^3","0x"};

	public static void Main(String[] args) {
	//	test1();


	}



	@Test
	void testDerivative() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			Monom D = m.derivative();
			System.out.println(i+") the monom is: "+m +"\t the derivative is = "+D);
		}
	}

	@Test
	void testF() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			double fx = m.f(i);
			System.out.println(i+") the monom is: "+m +"\t the value at f("+i+") is = "+fx);
		}
	}


	@Test
	void testIsZero() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			System.out.println(i+")the monom is: "+m +"    \tisZero?: "+m.isZero());
		}
	}

	@Test
	void testAdd() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			System.out.println("the Monom is: "+m);
			m.add(m);
			System.out.println("After adding it to himself: "+m);
		}
	}

	@Test
	void testMultipy() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			System.out.println("the Monom is: "+m);
			m.multipy(m);
			System.out.println("After multipliying it to himself: "+m);
		}
	}

	@Test
	void testEqualsObject() {
		Object obj = new Object();
		Object obj1 = new Monom("2x");
		Monom a = new Monom("2x");
		Monom a1 = new Monom("2.0x");
		Monom a2 = new Monom("2.1x");
		Monom b = new Monom("2.0x^2");
		Monom b1 = new Monom("2x^2");
		Monom b2 = new Monom("2x^3");
		System.out.println("check for regular monom");
		System.out.println(a+" isEqual to: "+a1+"?" +a.equals(a1));			//int with double same value
		System.out.println(a+" isEqual to: "+a2+"?" +a.equals(a2));			//different value with int and double
		System.out.println(a1+" isEqual to: "+a2+"?" +a1.equals(a2));		//different value with double and double
		System.out.println("check for monom with a power");
		System.out.println(b+" isEqual to: "+b1+"?" +b.equals(b1));			//int with double same value in coefficient and same power
		System.out.println(b+" isEqual to: "+b2+"?" +b.equals(b2));			//same value with int and double in coefficient but diffrent power
		System.out.println(b1+" isEqual to: "+b2+"?" +b1.equals(b2));		//same value with int and different power
		System.out.println("does the object: "+obj+" equals to:"+a+" " +obj.equals(a));
		System.out.println("does the object: "+obj1+" equals to:"+a +" "+obj1.equals(a));
	}

	@Test
	void testArea() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			System.out.println("the Monom is: "+m);
			m.multipy(m);
			System.out.println("The area is: "+m.area(0, 2, 0.1));
		}
	}

	@Test
	void testRoot() {
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			System.out.println("the Monom is: "+m);
			m.multipy(m);
			System.out.println("The root is: "+m.root(0, 2, 0.1));
		}
	}

	@Test
	void testInitFromString() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

}
