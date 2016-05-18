package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class CarriesItemExpression extends UnitExaminationExpression {

	public CarriesItemExpression(UnitExpression unitToExamine, SourceLocation sourceLocation) {
		super(unitToExamine, sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return (getUnitToExamine().getCarryingBoulder() || getUnitToExamine().getCarryingLog());
	}

}
