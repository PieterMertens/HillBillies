package hillbillies.expressions;
import hillbillies.expressions.BinaryExpression;
import hillbillies.expressions.BooleanExpression;
import hillbillies.part3.programs.SourceLocation;

public class AndExpression extends BinaryExpression{

	public AndExpression(BooleanExpression left, BooleanExpression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return (this.getLeft(this.getStatement()).evaluate()&&this.getRight(this.getStatement()).evaluate());
	}

}
