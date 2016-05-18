package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsFriendExpression extends UnitExaminationExpression {

	public IsFriendExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return (getUnit().getFaction() == getUnitToExamine().getFaction() && getUnit() != getUnitToExamine());
	}

}
