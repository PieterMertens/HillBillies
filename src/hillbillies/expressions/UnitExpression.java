package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnitExpression extends Expression<Unit> {
	
	public UnitExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	
}
