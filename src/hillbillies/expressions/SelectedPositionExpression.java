package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class SelectedPositionExpression extends PositionExpression{

	public SelectedPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] evaluate() {
		// TODO Auto-generated method stub
		return getStatement().getTask().getSelectedPosition();
	}

}
