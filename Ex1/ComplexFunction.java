package Ex1;

import static Ex1.Operation.None;

public class ComplexFunction implements complex_function {
	public static final double EPS = 0.0001;
	private static final long serialVersionUID = 1L;
	function right;
	function left;
	Operation opa ;

	public ComplexFunction() {											//default constructor
		this.left = null;
		this.right = null;
		this.opa = opa.None;
	}
	public ComplexFunction(String opa, function a,function b) {											
		this.left = a.copy();
		this.right = b.copy();
		this.opa = getOpEnum(opa);
	}
	public ComplexFunction(String a) {											//default constructor
		function b = initFromString(a);
		ComplexFunction c = new ComplexFunction(b);
		this.left = c.left;
		this.right = c.right;
		this.opa = c.opa;
	}
	public ComplexFunction(Polynom a) {									//polynom constructor
		this.left = a.copy();
		this.opa  = opa.None;
		this.right = null;
	}
	public ComplexFunction(Operation opa,function le,function ri) {		//segment constructor
		this.left = le;
		this.right = ri;
		this.opa = opa; 
	}
	public ComplexFunction(function f1) {								//function constructor 
		this.left = f1.copy();
		this.right = null;
		this.opa = opa.None;
	}
	public ComplexFunction(ComplexFunction a) {				//copy constructor
		this.right = a.right;
		this.left = a.left;
		this.opa = a.opa; 

	}
	public void initFunction(ComplexFunction cf, String s) {      //add sub string to complex function.
		String polinom1 = "";
		String polinom2 = "";
		s = s.toLowerCase();
		String st = getOp(s);
		Operation opa1 = getOp2(s);
		if(st.equals("times")) {
			st = "mul";
		}
		if(st.equals("divid")) {
			st = "div";
		}
		int i = 0;
		for ( i = st.length()+1; s.charAt(i) != ','; i++) {
			polinom1 += s.charAt(i);
		}
		for (i++; s.charAt(i) != ')'; i++) {
			polinom2 += s.charAt(i);
		}
		if(st.equals("div")) {
			st = "divid";
		}
		if(st.equals("mul")) {
			st = "times";
		}
		function left = new Polynom(polinom1);
		if(polinom1.equals("")) {
			left = null;	
		}
		if(cf.left == null) {
			cf.left = left;
		}
		function right = new Polynom(polinom2);
		if(polinom2.equals("")&&left!=null) {
			right = left;
			ComplexFunction cf2 = new ComplexFunction(cf.opa,cf.left,cf.right);
			cf.right = cf2;
			cf.left = left;
			cf.opa = opa1;
		}
		else if(left != null&&right != null&&cf.right!=null) {
			ComplexFunction cf2 = new ComplexFunction(cf.opa,cf.left,cf.right);
			cf.left = cf2;
			ComplexFunction cf3 = new ComplexFunction(opa1,left,right);
			cf.right = cf3;
			cf.opa = opa.None;
		}
		else if(st.equals("times")) {
			if(cf.opa==None) {
				cf.opa=opa.Times;
			}
			else
			cf.mul(right);
		}
		else if(st.equals("plus")) {
			if(cf.opa==None) {
				cf.opa=opa.Plus;
			}
			else
			cf.plus(right);
		}
		else if(st.equals("divid")) {
			if(cf.opa==None) {
				cf.opa=opa.Divid;
			}
			else
			cf.div(right);
		}
		else if(st.equals("max")) {
			if(cf.opa==None) {
				cf.opa=opa.Max;
			}
			else
			cf.max(right);
		}
		else if(st.equals("min")) {
			if(cf.opa==None) {
				cf.opa=opa.Min;
			}
			else
			cf.min(right);
		}
		else if(st.equals("comp")) {
			if(cf.opa==None) {
				cf.opa=opa.Comp;
			}
			else
			cf.comp(right);
		}
	}
	@Override
	public function initFromString(String s) {          // creates complex function from string 
		s=s.toLowerCase();
		s=s.replaceAll("\\s+","");
		ComplexFunction cf=new ComplexFunction(this.opa,null,null);
		String comFunction ="";
		if(s.indexOf('(')!=-1 && s.indexOf(')')!=-1) {
    	while(s.length()>2) {
    		int i = 0;													
    		int j = 0;
            int count = 0;
            int count2 = 0;
		for ( i = 0; i < s.length()&&s.charAt(i) != ')'; i++) {
		}
		for ( j=i ; j > 0; j--) {
			if (s.charAt(j) == '(') {
				count++;
			}
			if (s.charAt(j) == ',') {
				count2++;
			}
			if (count == 2||count2 == 2) {
				break;
			}
		}
		
		if(j==0)
		   comFunction = s.substring(j , i)+')';          
		else
		    comFunction = s.substring(j+1 , i)+')';
		initFunction(cf,comFunction);                              //insert a sub string.
		s = s.substring(0 , j+1)+s.substring(i+1 , s.length());
		
		}

     return cf;
		}else {
			function cf1 = new Polynom(s);
			return cf1;
		}
		
	}
	public static int getLastPol(String s) {
		int count=0;
		for (int i = s.length()-1; i > 0 ; i--) {
			if(s.charAt(i) == ',' ) {
				break;
			}
			count++;
		}
		count =s.length()-count;
		return count;
	}
	public static Operation getOpEnum(String a) {
		if(a.charAt(0)=='p') {
			return Operation.Plus;
		}
		if(a.charAt(0)=='d') {
			return Operation.Divid;
		}if(a.charAt(0)=='m' && a.charAt(1)=='a') {
			return Operation.Max;
		}if(a.charAt(0)=='p'&& a.charAt(1)=='i') {
			return Operation.Min;
		}if(a.charAt(0)=='N') {
			return Operation.None;
		}if(a.charAt(0)=='c') {
			return Operation.Comp;
		}if(a.charAt(0)=='m') {
			return Operation.Times;
		}

		return null;
	}
	public static String getOp(String a) {           //receive string operation and get to enum string operation. 
		String op="";
		String plus = "plus";
		String div = "divid";
		String min = "min";
		String max = "max";
		String none = "None";
		String comp = "comp";
		String mul = "times";
		if(a.charAt(0)=='p') {
			return plus;
		}
		if(a.charAt(0)=='d') {
			return div;
		}if(a.charAt(0)=='m' && a.charAt(1)=='a') {
			return max;
		}if(a.charAt(0)=='m'&& a.charAt(1)=='i') {
			return min;
		}if(a.charAt(0)=='N') {
			return none;
		}if(a.charAt(0)=='c') {
			return comp;
		}if(a.charAt(0)=='m') {
			return mul;
		}

		return op;
	}
	public  Operation getOp2(String a) {               //receive string operation and get to enum operation. 
		Operation op= null;
		if(a.charAt(0)=='p') {
			return opa.Plus;
		}
		if(a.charAt(0)=='d') {
			return opa.Divid;
		}if(a.charAt(0)=='m' && a.charAt(1)=='a') {
			return opa.Max;
		}if(a.charAt(0)=='m'&& a.charAt(1)=='i') {
			return opa.Min;
		}if(a.charAt(0)=='N') {
			return opa.None;
		}if(a.charAt(0)=='c') {
			return opa.Comp;
		}if(a.charAt(0)=='m') {
			return opa.Times;
		}

		return op;
	}
	@Override
	public function copy() {                //deep copy function.
		ComplexFunction cop = new ComplexFunction();
		cop.left = this.left;
		cop.right = this.right;
		cop.opa = this.opa;

		return cop;
	}
	@Override
	public void div(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1.copy();
			this.opa = opa.Divid;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1.copy();
			this.opa = Operation.Divid;
		}



	}
	@Override
	public void max(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1.copy();
			this.opa = opa.Max;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1.copy();
			this.opa = Operation.Max;
		}
	}
	@Override
	public void plus(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1.copy();
			this.opa = opa.Plus;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1.copy();
			this.opa = Operation.Plus;
		}

	}
	@Override
	public void mul(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1.copy();
			this.opa = opa.Times;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1.copy();
			this.opa = Operation.Times;
		}

	}
	@Override
	public function left() {

		return this.left;
	}
	@Override
	public function right() {

		return this.right;
	}
	@Override
	public double f(double x) {          //return sum of complex function at the point x.

		double sum = 0;
		switch(this.opa) {

		case Plus:
			sum += (this.left).f(x);
			if(this.right!=null)sum += (this.right).f(x);
			break;
		case Times:
			sum=1;
			sum *= (this.left).f(x);
			if(this.right!=null)sum *= (this.right).f(x);
			break;
		case Divid:
			sum=1;
			sum = (this.left).f(x)/sum;
			if(this.right!=null)sum /= (this.right).f(x);
			break;
		case Max:
			if(this.right==null)return this.left.f(x);
			sum = this.left.f(x)>=this.right.f(x)?this.left.f(x):this.right.f(x);
			break;	
		case Min:
			if(this.right==null)return this.left.f(x);
			sum = this.left.f(x)<this.right.f(x)?this.left.f(x):this.right.f(x);
			break;
		case Comp:
			sum = this.left.f(this.right.f(x));
			break;	
		case None:
			sum = this.left.f(x);
			break;	
		default:
			System.out.println("error");
			sum=-1;
			break;
		}
		return sum;
	}
	@Override
	public void min(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1.copy();
			this.opa = opa.Min;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1.copy();
			this.opa = Operation.Min;
		}
	}
	@Override
	public void comp(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1.copy();
			this.opa = opa.Comp;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1.copy();
			this.opa = Operation.Comp;
		}
	}
	@Override
	public Operation getOp() {

		return this.opa;
	}
	public String toString() {
		StringBuilder write = new StringBuilder();
		write.append("("+this.left+","+this.right+")");
		switch(this.opa) {
		
		case Plus:
			write.insert(0,"plus");
			break;
		case Times:
			write.insert(0,"mul");
			break;
		case Divid:
			write.insert(0,"div");
			break;
		case Max:
			write.insert(0,"max");
			break;	
		case Min:
			write.insert(0,"min");
			break;
		case Comp:
			write.insert(0,"comp");
			break;	
		case None:
			break;
		default:
			System.out.println("error");
			break;
		}
		return write.toString();
	}
	@Override
	public boolean equals(Object obj) {									//for the test
		if(obj instanceof ComplexFunction) {
			ComplexFunction ob = (ComplexFunction)obj;
			boolean flagThRi = false;
			boolean flagObRi = false;
			if(this.right==null)flagThRi =true;
			if(ob.right==null)flagObRi =true;
			for (double i = -10; i < 10; i+=0.1) {
				if(flagThRi) { if(flagObRi) {						//both null
					if(!(this.left.f(i) <= (ob.left.f(i)+EPS) && this.left.f(i) >= (ob.left.f(i)-EPS))) return false;
				}}
				if(!flagThRi) {if(flagObRi) {									//this.right!=null ob.right==null
					ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
					this.left = cf;
					this.right = null;
					if( !(this.left.f(i) <= (ob.left.f(i)+EPS) && this.left.f(i) >= (ob.left.f(i)-EPS)) ) return false;
				}}
				if(flagThRi) {if(!flagObRi) {									//this.right==null ob.right!=null
					ComplexFunction cf = new ComplexFunction(ob.opa,ob.left,ob.right);
					ob.left = cf;
					ob.right = null;
					if(!(this.left.f(i) <= (ob.left.f(i)+EPS) && this.left.f(i) >= (ob.left.f(i)-EPS))) return false;
				}}
				if(!flagThRi) {if(!flagObRi) {									//both not null
					ComplexFunction cf = new ComplexFunction(ob.opa,ob.left,ob.right);
					ob.left = cf;
					ob.right = null;
					ComplexFunction cf2 = new ComplexFunction(this.opa,this.left,this.right);
					this.left = cf2;
					this.right = null;
					if(!(this.left.f(i) <= (ob.left.f(i)+EPS) && this.left.f(i) >= (ob.left.f(i)-EPS))) return false;
				}}

			}
			return true;
		}

		return false;

	}
	public boolean equals(Object obj,int start,int end,double eps) {				//for the user to check equality
		if(obj instanceof ComplexFunction) {
			ComplexFunction ob = (ComplexFunction)obj;
			boolean flagThRi = false;
			boolean flagObRi = false;
			if(this.right==null)flagThRi =true;
			if(ob.right==null)flagObRi =true;
			for (double i = start; i < end; i+=eps) {
				if(flagThRi) { if(flagObRi) {						//both null
					if(this.left.f(i) != ob.left.f(i)) return false;
				}}
				if(!flagThRi) {if(flagObRi) {									//this.right!=null ob.right==null
					if( (this.left.f(i)+this.right.f(i)) != ob.left.f(i) ) return false;
				}}
				if(flagThRi) {if(!flagObRi) {									//this.right==null ob.right!=null
					if( (this.left.f(i)) != (ob.left.f(i)+ob.right.f(i)) ) return false;
				}}
				if(!flagThRi) {if(!flagObRi) {									//both not null
					if( (this.left.f(i)+this.right.f(i)) != (ob.left.f(i)+ob.right.f(i)) ) return false;
				}}

			}
			return true;
		}

		return false;

	}
}
