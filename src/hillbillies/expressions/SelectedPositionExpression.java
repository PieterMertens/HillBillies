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
		System.out.println("--SelectedPosexp: getst	t+getS()+" +getStatement());

		System.out.println("--SelectedPosexp: getTasl="+getTask()+" get selecetedpos="+getTask().getSelectedPosition()[0]);

		return getTask().getSelectedPosition();
	}

}
