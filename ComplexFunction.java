package Ex1;

public class ComplexFunction implements complex_function {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	function right = null;
	function left = null;
	Operation opa = null;

	public ComplexFunction() {											//default constructor
		this.left = null;
		this.right = null;
		this.opa = opa.None;
	}
	public ComplexFunction(String a) {											//default constructor
		function b = initFromString(a);
		ComplexFunction c = new ComplexFunction(b);
		this.left = c.left;
		this.right = c.right;
		this.opa = c.opa;
	}
	public ComplexFunction(Polynom a) {									//polynom constructor
		this.left = a;
		this.opa  = opa.None;
		this.right = null;
	}

	public ComplexFunction(Operation opa,function le,function ri) {		//segment constructor
		this.left = le;
		this.right = ri;
		this.opa = opa; 
	}
	public ComplexFunction(function f1) {								//function constructor 
		this.left = f1;
		this.right = null;
		this.opa = opa.None;
	}

	public ComplexFunction(ComplexFunction a) {				//copy constructor
		this.right = a.right;
		this.left = a.left;
		this.opa = a.opa; 

	}


	@Override
	public function initFromString(String s) {
		function ans = null;
		s = s.toLowerCase();
		String st = getOp(s);
		if(st=="") st = ",";					//the last case (,)
		int start = s.indexOf(st);
		int end=getLastPol(s);
		function right = new Polynom(s.substring(end, s.length()-1));
		this.right = right;
		if(start==end-1) {
			return ans; 
		}
		if(start+1>=0 && s.endsWith(")") || s.endsWith(",")) {
			String s1 = s.substring(start+1,s.length()-1);
			function ff = initFromString(s1);
			ans = ff;
			return ans;
		}

		return ans;
	}
	public static int getLastPol(String s) {
		int count=0;
		for (int i = s.length(); i < 0 ; i--) {
			if(s.charAt(i) == ',' ) {
				break;
			}
			count++;
		}
		count =s.length()-count;
		return count;
	}

	public static String getOp(String a) {
		String op="";
		String plus = "plus";
		String div = "div";
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
		}if(a.charAt(0)=='p'&& a.charAt(1)=='i') {
			return min;
		}if(a.charAt(0)=='N') {
			return none;
		}if(a.charAt(0)=='c') {
			return comp;
		}if(a.charAt(0)=='t') {
			return mul;
		}

		return op;
	}

	@Override
	public function copy() {
		ComplexFunction cop = new ComplexFunction();
		cop.left = this.left;
		cop.right = this.right;
		cop.opa = this.opa;

		return null;
	}
	@Override
	public void div(function f1) {
				if(this.right==null) {				// !null | null
					this.right = f1;
					this.opa = opa.Divid;
				}else{
					ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
					this.left = cf;
					this.right = f1;
					this.opa = Operation.Divid;
				}



	}
	@Override
	public void max(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1;
			this.opa = opa.Max;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1;
			this.opa = Operation.Max;
		}
	}
	@Override
	public void plus(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1;
			this.opa = opa.Plus;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1;
			this.opa = Operation.Plus;
		}

	}
	@Override
	public void mul(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1;
			this.opa = opa.Times;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1;
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
	public double f(double x) {

		double sum = 0;
		switch(this.opa) {

		case Plus:
			sum += ((Polynom)this.left).f(x);
			if(this.right!=null)sum += ((Polynom)this.right).f(x);
			break;
		case Times:
			sum *= ((Polynom)this.left).f(x);
			if(this.right!=null)sum *= ((Polynom)this.right).f(x);
			break;
		case Divid:
			sum /= ((Polynom)this.left).f(x);
			if(this.right!=null)sum /= ((Polynom)this.right).f(x);
			break;
		case Max:
			sum = this.left.f(x)>=this.right.f(x)?this.left.f(x):this.right.f(x);
			break;	
		case Min:
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
			this.right = f1;
			this.opa = opa.Min;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1;
			this.opa = Operation.Min;
		}
	}
	@Override
	public void comp(function f1) {
		if(this.right==null) {				// !null | null
			this.right = f1;
			this.opa = opa.Comp;
		}else{
			ComplexFunction cf = new ComplexFunction(this.opa,this.left,this.right);
			this.left = cf;
			this.right = f1;
			this.opa = Operation.Comp;
		}
	}
	@Override
	public Operation getOp() {
	
		return this.opa;
	}
	public static void main(String[]  args) {
		String a =" plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)";
		ComplexFunction b = new ComplexFunction(a);
	}

}
