package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public class NextToPositionExpression extends PositionExpression {

	private PositionExpression position;

	public NextToPositionExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}
	
	public PositionExpression getPositionExpression(Statement statement){
		this.position.setStatement(statement);
		return position;
	}
	
	public int[] getPosition(Statement statement){
		this.position.setStatement(statement);
		return position.evaluate();
	}

	
	@Override
	public int[] evaluate() {
		// TODO Auto-generated method stub
		return getUnit().getNearestNeighbour(getPosition(this.getStatement())[0], getPosition(this.getStatement())[1], getPosition(this.getStatement())[2]);
	}

}
