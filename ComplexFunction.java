package Ex1;

public class ComplexFunction {
	function right = null;
	function left = null;
	ComplexFunction head=null;
	Operation opa = null;

	public ComplexFunction() {
		this.left = null;
		this.right = null;
		this.head = null;
		this.opa = opa.None;
	}
	public ComplexFunction(Polynom a) {
		this.left = a;
		this.opa  = opa.None;
		this.right = null;
		}

	public ComplexFunction(Operation opa,function le,function ri) {
		if(this.left==this.right && this.left==null) {
			this.left = le;
			this.right = ri;
			this.opa = opa; 
		}
	}
//	public ComplexFunction(ComplexFunction le,ComplexFunction ri) {
//
//	}
//	public ComplexFunction(ComplexFunction a) {
//		if(this.left.head==null) {this.left.head=a;return;}
//		if(this.left.head!=null && this.right.head != null) {
//			ComplexFunction b = new ComplexFunction() ;
//			this.right=this.left;
//			this.left=a;
//
//		}
//	}




	public void plus(function f1) {
		if(this.right==null && this.left != null) {
			this.right = f1;
			this.left = (Polynom)this.left.add((Polynom)this.right);
			this.right = null;
		}
		if(this.right!=null && this.left != null) {
			this.left = (Polynom)this.left.add((Polynom)this.right);
			this.right = f1;
			this.left = (Polynom)this.left.add((Polynom)this.right);
			this.right = null;
		}
		if(this.right==null && this.left == null) {
			(Polynom)this.left = f1;
		}


	}
	public void mul(function f1) {
		if(this.right==null && this.left != null) {
			this.right = f1;
			this.left = (Polynom)this.left.mul((Polynom)this.right);
			this.right = null;
		}
		if(this.right!=null && this.left != null) {
			this.left = (Polynom)this.left.mul((Polynom)this.right);
			this.right = f1;
			this.left = (Polynom)this.left.mul((Polynom)this.right);
			this.right = null;
		}
		if(this.right==null && this.left == null) {
			(Polynom)this.left = f1;
		}


	}

	public function left() {

		return this.left;
	}
	public function right() {

		return this.right;
	}

	public static void main(String[] args) {
		ComplexFunction a = new ComplexFunction("x^2+3");
		System.out.println(a);
	}

}
