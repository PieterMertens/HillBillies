package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnitExaminationExpression extends BooleanExpression {

	private UnitExpression unitToExamine;

	public UnitExaminationExpression(UnitExpression unitToExamine, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToExamine = unitToExamine;
	}

	public Unit getUnitToExamine() {
		return this.unitToExamine.evaluate();
	}

}
