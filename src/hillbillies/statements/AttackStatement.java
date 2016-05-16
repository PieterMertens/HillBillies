package hillbillies.statements;

import java.util.NoSuchElementException;

import hillbillies.expressions.Expression;
import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class AttackStatement extends Statement{
	
	//TODO voor attack and follow superclass maken met targetUnit fso

	private Expression<Unit> unitToAttack;

	public AttackStatement(Expression<Unit> unitToAttack,SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToAttack = unitToAttack;
		
	}
	
	public Expression<Unit> getUnitToAttack(){
		return this.unitToAttack;
	}

	@Override
	public void execute() {

		if (isAssignedToUnit()) {
			getUnit().attack(getUnit(),getUnitToAttack().evaluate());
		} else {
			throw new NoSuchElementException("No unit found.");
		}

	}

}
