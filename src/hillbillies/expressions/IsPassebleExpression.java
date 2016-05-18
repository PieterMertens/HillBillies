package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsPassebleExpression extends CubeExpression {

	public IsPassebleExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return getWorld().isPassable(getPosition()[0], getPosition()[1], getPosition()[2]);
	}

}
