package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.Expression;
import hillbillies.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends Statement {

	private PositionExpression position;

	public WorkStatement(PositionExpression position,SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	public PositionExpression getPosition() {
		return this.position;
	}

	@Override
	public void execute() {
		if (isAssignedToUnit()){
			getUnit().workAt(getPosition().evaluate()[0], getPosition().evaluate()[1], getPosition().evaluate()[2]);
		}else{
			throw new NoSuchElementException("No unit found.");
		}
		
		
	}

}
