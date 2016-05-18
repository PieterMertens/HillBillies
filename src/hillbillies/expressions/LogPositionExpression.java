package hillbillies.expressions;

import hillbillies.helper.Helper;
import hillbillies.part3.programs.SourceLocation;

public class LogPositionExpression extends PositionExpression {

	public LogPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] evaluate() {
		// TODO Auto-generated method stub
		return Helper.doubleArrayToIntArray(getWorld().getNearestLog(getUnit()).getPosition());
	}

}
