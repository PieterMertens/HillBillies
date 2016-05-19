package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.Expression;
import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class AttackStatement extends Statement {

	// TODO voor attack and follow superclass maken met targetUnit fso

	private Expression<Unit> unitToAttack;
	

	public AttackStatement(Expression<Unit> unitToAttack, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToAttack = unitToAttack;
		unitToAttack.setStatement(this);
	}

	public Expression<Unit> getUnitToAttack() {
		return this.unitToAttack;
	}
	
	@Override
	public void execute() {

		if (isAssignedToUnit()) {
			if(!getUnit().getIsAttacking()&&!getUnit().getWantToAttack()&&getStarted()){
				setIsExecuted();
				setStarted(false);
				
			}else{
			getUnit().attack(getUnit(),getUnitToAttack().evaluate());
			setStarted(true);
			}
		} else {
			throw new NoSuchElementException("No unit found.");
		}

	}

}
