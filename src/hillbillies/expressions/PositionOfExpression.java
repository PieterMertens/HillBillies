package hillbillies.expressions;

import hillbillies.helper.Helper;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public class PositionOfExpression extends PositionExpression {

	private UnitExpression unit;

	public PositionOfExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unit = unit;
	}

	public UnitExpression getUnitForPositionOf(Statement statement) {
		this.unit.setStatement(statement);
		return unit;
	}

	@Override
	public int[] evaluate() {
		// TODO Auto-generated method stub
		return Helper.doubleArrayToIntArray(getUnitForPositionOf(this.getStatement()).getUnit().getPosition());
	}

}
