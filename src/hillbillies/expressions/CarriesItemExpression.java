package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

public class CarriesItemExpression extends UnitExaminationExpression {

	public CarriesItemExpression(UnitExpression unitToExamine, SourceLocation sourceLocation) {
		super(unitToExamine, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		System.out.println("this= "+this+" getStatement()="+getStatement());
		System.out.println("carriesItemevalu: " +getUnitToExamine(this.getStatement()));
		return (getUnitToExamine(this.getStatement()).getCarryingBoulder() || getUnitToExamine(this.getStatement()).getCarryingLog());
	}

}
