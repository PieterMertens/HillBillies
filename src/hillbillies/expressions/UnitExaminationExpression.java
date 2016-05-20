package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public abstract class UnitExaminationExpression extends BooleanExpression {

	private UnitExpression unitToExamine;

	public UnitExaminationExpression(UnitExpression unitToExamine, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToExamine = unitToExamine;
		System.out.println("unitExaminationExp: "+unitToExamine);
	
	}
	
	

	public Unit getUnitToExamine(Statement statement) {
		this.unitToExamine.setStatement(statement);
		System.out.println("unitExamExpr: " + unitToExamine + " statem: "+unitToExamine.getStatement());
		return this.unitToExamine.evaluate();
	}

}
