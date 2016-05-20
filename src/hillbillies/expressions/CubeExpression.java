package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public abstract class CubeExpression extends BooleanExpression {

	private PositionExpression position;

	public CubeExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
		
	}

	public int[] getPosition(Statement statement) {
		this.position.setStatement(statement);
		return this.position.evaluate();
	}

}
