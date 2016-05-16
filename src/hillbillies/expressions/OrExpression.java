package hillbillies.expressions;
import hillbillies.expressions.BinaryExpression;
import hillbillies.expressions.BooleanExpression;
import hillbillies.part3.programs.SourceLocation;

public class OrExpression extends BinaryExpression{

	public OrExpression(BooleanExpression left, BooleanExpression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return (this.getLeft().evaluate()||this.getRight().evaluate());
	}

}
