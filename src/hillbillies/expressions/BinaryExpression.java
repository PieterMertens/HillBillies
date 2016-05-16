package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryExpression extends BooleanExpression {

	private BooleanExpression left;
	private BooleanExpression right;

	public BinaryExpression(BooleanExpression left, BooleanExpression right, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.left = left;
		this.right = right;
	}
	
	public BooleanExpression getLeft(){
		return this.left;
	}

	
	public BooleanExpression getRight(){
		return this.right;
		
	}
}