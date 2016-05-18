package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public abstract class CubeExpression extends BooleanExpression {

	private PositionExpression position;

	public CubeExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	public int[] getPosition() {
		return this.position.evaluate();
	}

}
