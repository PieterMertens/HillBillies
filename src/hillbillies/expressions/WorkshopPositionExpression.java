package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class WorkshopPositionExpression extends PositionExpression {

	public WorkshopPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] evaluate() {
		// TODO Auto-generated method stub
		return getWorld().getNearestWorkhop(getUnit());
	}

}
