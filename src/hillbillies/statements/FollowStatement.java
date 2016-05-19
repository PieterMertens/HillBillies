package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.Expression;
import hillbillies.expressions.UnitExpression;
import hillbillies.part3.programs.SourceLocation;

public class FollowStatement extends Statement {

	private UnitExpression unitToFollow;

	public FollowStatement(UnitExpression unitToFollow, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToFollow = unitToFollow;
		unitToFollow.setStatement(this);
	}

	@Override
	public void execute() {

		if (isAssignedToUnit()) {
			if (getStarted() && !getUnit().getIsFollowing()) {
				setIsExecuted();
				setStarted(false);				
			}else{
				getUnit().followUnit(getUnitToFollow().evaluate());
				setStarted(true);
			}
		} else {
			throw new NoSuchElementException("No unit found.");
		}

	}

	public UnitExpression getUnitToFollow() {
		return unitToFollow;
	}

}
