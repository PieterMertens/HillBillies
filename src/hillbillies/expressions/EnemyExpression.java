package hillbillies.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Unit evaluate() {
		// TODO Auto-generated method stub

		return getUnit().getWorld().getNearestEnemy(getUnit());

	}

}
