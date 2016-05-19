package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;

public class MoveToStatement extends Statement {

	private PositionExpression position;

	public MoveToStatement(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	public PositionExpression getPosition() {
		return this.position;
	}

	@Override
	public void execute() {
		if (isAssignedToUnit()) {
			if (getUnit().moveToTargetReached()) {
				setIsExecuted();
			} else {
				getUnit().moveTo(getPosition().evaluate());
			}
		} else {
			throw new NoSuchElementException("No unit found.");
		}

	}

}
