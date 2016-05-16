package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class NotExpression extends UnaryExpression {


	public NotExpression(BooleanExpression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	
	}

	@Override
	public Boolean evaluate() {
		return (!this.getExpression().evaluate());
	}

}
