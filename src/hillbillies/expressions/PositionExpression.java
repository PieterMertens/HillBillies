package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public abstract class PositionExpression extends Expression<int[]> {

	public PositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

}
