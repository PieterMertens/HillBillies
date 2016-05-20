package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsAliveExpression extends UnitExaminationExpression {

	public IsAliveExpression(UnitExpression unitToExamine, SourceLocation sourceLocation) {
		super(unitToExamine, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return !getUnitToExamine(this.getStatement()).isTerminated();
	}

}
