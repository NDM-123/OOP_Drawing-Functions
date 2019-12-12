package Ex1;
/**
 * 

 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ComplexFunctionTest {
	public static final double EPS = 0.00001;
	@Test
	void test1() {
		System.out.println("******  Test1: copy and f(x)******");       //test deep copy and f(x)
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		System.out.println(cf.toString());
		cf.mul(m2);
		System.out.println(cf);
		Polynom p = new Polynom();
		p.add(m1);
		p.add(m2);
		p.multiply(m2);
		double v = 4.0;
		double dp = p.f(v);
		double dcf = cf.f(v);
		double dd = Math.abs(dp-dcf);
		if(dd>EPS) {
			System.out.println(p+" at "+v+" = "+dp);
			System.out.println(cf+" at "+v+" = "+dcf);
			fail("ERR: should got the same value from: "+p+"  and "+cf);

		}
	}

	@Test
	void test2() {                      //test complex function constructor operation from polynom and initial from to string            
		System.out.println("******  Test2: testOfString******");
		Polynom p1 = new Polynom();
		p1.add( new Monom(2,2));
		Polynom p2 = new Polynom();
		p2.add(new Monom(3,3));
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);
		System.out.println(cf);
		cf.mul(m2);
		cf3.mul(m2);
		String s = cf.toString();
		function cf2 = cf.initFromString(s);
		if(!cf.equals(cf2)) {
			fail("ERR: "+cf+" should be equals to "+cf2);
		}
		if(!cf.equals(cf3)) {
			fail("ERR: "+cf+" should be equals to "+cf3);
		}
	}
	@Test
	void test3() {                             //test complex function add operation from polynom and initial from to string
		System.out.println("******  Test3: ComplexFunction()******");
		String s1 = "+3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x -1","x -2", "x -3", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		for(int i=1;i<s3.length;i++) {
			p3.multiply(new Polynom(s3[i]));
		}
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		System.out.println(cf.toString());
		ComplexFunction cf4 = new ComplexFunction("div", new Monom("x"),p3);
		System.out.println(cf4.toString());
		cf.div(p1);
		System.out.println(cf.toString());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s);
		System.out.println(cf5.toString());
		if(!cf.equals(cf5)) {
			fail("ERR: "+cf+" should be equals to "+cf5);
		}

		int size=10;
		for(int i=0;i<size;i++) {
			double x = Math.random();
			double d = cf.f(x);
			double d5 = cf5.f(x);
			assertEquals(d,d5,EPS);
		}
		System.out.println(cf4);
		System.out.println(cf5);
	}
	@Test
	void test4() {                   //test initFromString of complex function and checks operations and equals.
		System.out.println("******  Test4: initFromString******");
		String a ="div(1.0x +1.0,mul(mul(1.0x +3.0,1.0x -2.0),1.0x -4.0))";
		String b = "plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)";
		String c = "max(6,2)";
		String d = "plus(4,2)";
		ComplexFunction a1 = new ComplexFunction(a);
		ComplexFunction b1 = new ComplexFunction(b);
		ComplexFunction c1 = new ComplexFunction(c);
		ComplexFunction d1 = new ComplexFunction(d);
		Monom m1 = new Monom(2,0);
		a1.plus(m1);
		System.out.println("a1 = "+a1);
		System.out.println("b1 = "+b1);
		if(!a1.equals(b1)) {
			fail("ERR: "+a1+" should be equals to "+b1);
		}
		if(!c1.equals(d1)) {
			fail("ERR: "+c1+" should be equals to "+d1);
		}
	}
	@Test
	void test5() {                         //check equals polynom to complex function.
		System.out.println("******  Test5: equals******");
		String a ="2x^2 + 4x";
		String b = "plus(mul(2,x),4)";
		ComplexFunction a1 = new ComplexFunction(a);
		ComplexFunction b1 = new ComplexFunction(b);
		System.out.println("a1 = "+a1);
		System.out.println("b1 = "+b1);
		Monom m1 = new Monom(1,1);
		a1.div(m1);
		System.out.println("a1 = "+a1);
		System.out.println("b1 = "+b1);
		if(!a1.equals(b1)) {
			fail("ERR: "+a1+" should be equals to "+b1);
		}
	}
	@Test
	void test6() {                //test comp operation (comp(f1(x), f2(x)) == f1(f2(x)).
		System.out.println("******  Test6: comp******");
		String a = "comp(4x,mul(2,4))";
		String b = "mul(4,8)";
		String c = "comp(2x,mul(2,1))";
		String d = "div(8,2)";
		ComplexFunction a1 = new ComplexFunction(a);
		ComplexFunction b1 = new ComplexFunction(b);
		ComplexFunction c1 = new ComplexFunction(c);
		ComplexFunction d1 = new ComplexFunction(d);
		System.out.println("a1 = "+a1);
		System.out.println("b1 = "+b1);
		if(!a1.equals(b1)) {
			fail("ERR: "+a1+" should be equals to "+b1);
		}
		System.out.println("c1 = "+c1);
		System.out.println("d1 = "+d1);
		if(!a1.equals(b1)) {
			fail("ERR: "+c1+" should be equals to "+d1);
		}
	}

	@Test
	void test7() {                        //test value of f(x) at the point x.
		System.out.println("******  Test7: f(x)******");
		double res = 46;
		double res2;
		double res3 = 80;
		double res4;
		String a = "plus(div(mul(8,5x),2.5x),7.5x)";      //8*5*4/2.5*4+7.5*4=46  (x=4)
		ComplexFunction a1 = new ComplexFunction(a);
		res2 =a1.f(4);
		assertEquals(res,res2,EPS);
		
		String b ="4";                          
		ComplexFunction b1 = new ComplexFunction(b);
		Monom m1 = new Monom(8,1);
		b1.mul(m1);
		res4 = b1.f(2.5);
		assertEquals(res3,res4,EPS);             //4*8*2.5=80
		
	}
	
}



