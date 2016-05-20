package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsEnemyExpression extends UnitExaminationExpression {

	public IsEnemyExpression(UnitExpression unitToExamine, SourceLocation sourceLocation) {
		super(unitToExamine, sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return (getUnit().getFaction() != getUnitToExamine(this.getStatement()).getFaction());
	}

}
