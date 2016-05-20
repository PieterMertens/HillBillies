package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.Expression;
import hillbillies.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends Statement {

	private PositionExpression position;

	public WorkStatement(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
		position.setStatement(this);
	}

	public PositionExpression getPosition() {
		return this.position;
	}

	@Override
	public void execute() {
		System.out.println("execute Workst");
		if (isAssignedToUnit()) {
			if (getStarted() && !getUnit().getIsWorking() && !getUnit().getWantToWork()) {
				setIsExecuted();
				setStarted(false);
			} else {
				getUnit().workAt(getPosition().evaluate()[0], getPosition().evaluate()[1], getPosition().evaluate()[2]);
				setStarted(true);
			}
		} else {
			throw new NoSuchElementException("No unit found.");
		}

	}

}
