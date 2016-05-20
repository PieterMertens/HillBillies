package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public abstract class BinaryExpression extends BooleanExpression {

	private BooleanExpression left;
	private BooleanExpression right;

	public BinaryExpression(BooleanExpression left, BooleanExpression right, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.left = left;
		this.right = right;
		// left.setStatement(this.getStatement());
		// right.setStatement(this.getStatement());
	}

	public BooleanExpression getLeft(Statement statement) {
		this.left.setStatement(statement);
		return this.left;
	}

	public BooleanExpression getRight(Statement statement) {
		this.right.setStatement(statement);
		return this.right;

	}
}