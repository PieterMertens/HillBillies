package hillbillies.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class AnyExpression extends UnitExpression {

	public AnyExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Unit evaluate() {
		return getUnit().getWorld().getNearestUnit(getUnit());
	}

}
