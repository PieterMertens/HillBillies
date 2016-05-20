package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class ThisExpression extends UnitExpression {

	public ThisExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Unit evaluate() {
		// TODO Auto-generated method stub
		System.out.println("thisexpression this= "+this+" getStatement()="+getStatement());
		return getUnit();
	}

}
