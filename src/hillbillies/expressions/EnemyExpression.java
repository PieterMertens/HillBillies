package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() {
		return getUnit().getWorld().getNearestEnemy(getUnit());

	}

}
