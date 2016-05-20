package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;

public class MoveToStatement extends Statement {

	private PositionExpression position;

	public MoveToStatement(PositionExpression pos, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = pos;
		pos.setStatement(this);
		System.out.println("--MOvtostatement pos=" + pos + " getPos=" + getPosition());

		// System.out.println("MoveToStatement(PositionExpression position,
		// SourceLocation sourceLocation) - position="+pos);

		// getPosition().setStatement(this);
	}

	public PositionExpression getPosition() {
		return this.position;
	}

	@Override
	public void execute() {

		System.out.println("getstatement: " + getFirstParentStatement());
		System.out.println("getstatementunit: " + getTask().getAssignedUnit());
		System.out.println("---moveto statement getunit=" + getUnit());
		System.out.println("---moveto statement getposition=" + getPosition());
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
