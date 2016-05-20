package hillbillies.expressions;

import hillbillies.helper.Helper;
import hillbillies.part3.programs.SourceLocation;

public class BoulderPositionExpression extends PositionExpression {

	public BoulderPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public int[] evaluate() {
		return Helper.doubleArrayToIntArray(getWorld().getNearestBoulder(getUnit()).getPosition());
	}

}
