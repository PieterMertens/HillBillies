package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class LiteralPositionExpression extends PositionExpression {

	private int[] position;

	public LiteralPositionExpression(int x, int y, int z, SourceLocation sourceLocation) {
		super(sourceLocation);
		int[] position = { x, y, z };
		this.position = position;
	}

	public int[] getPosition() {
		return this.position;
	}

	@Override
	public int[] evaluate() {
		// TODO Auto-generated method stub
		return getPosition();
	}

}
