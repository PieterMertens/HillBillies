package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnaryExpression extends BooleanExpression {

	private BooleanExpression expression;

	public UnaryExpression(BooleanExpression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression= expression;
	}

	public BooleanExpression getExpression() {
		return expression;
	}

}
