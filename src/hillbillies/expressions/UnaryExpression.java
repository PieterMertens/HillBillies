package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public abstract class UnaryExpression extends BooleanExpression {

	private BooleanExpression expression;

	public UnaryExpression(BooleanExpression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression= expression;
	
	}

	public BooleanExpression getExpression(Statement statement) {
		this.expression.setStatement(statement);
		return expression;
	}

}
