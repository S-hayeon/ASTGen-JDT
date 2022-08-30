package AST.JDT;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.*;

public class CustomVisitor extends ASTVisitor{
    private final Set<String> names;
    private final CompilationUnit compliationUnit;
    private boolean visitDocTags;

    public CustomVisitor(CompilationUnit cu) {
        this.compliationUnit = cu;
        names = new HashSet<>();
    }
    public boolean visit(VariableDeclarationFragment node) {
        SimpleName name = node.getName();
        this.names.add(name.getIdentifier());
        System.out.println(
                "Declared '" + name + "' at line " + compliationUnit.getLineNumber(name.getStartPosition()));
        return false;
    }

    public boolean visit(SimpleName node) {
        if (this.names.contains(node.getIdentifier())) {
            System.out.println(
                    "Used '" + node + "' at line " + compliationUnit.getLineNumber(node.getStartPosition()));
        }
        return false;
    }

    public boolean visit(ForStatement node) {
        System.out.println("---------------");
        System.out.println(node.initializers());
        System.out.println(node.initializers().getClass().getName());
        List x = node.initializers();

        System.out.println(x.get(0).getClass().getName());
        // VariableDeclarationExpression
        System.out.println("---------------");
        System.out.println(node.getExpression().getClass().getName());

        Expression aa = node.getExpression();
        System.out.println(aa.toString());



        System.out.println("---------------");
        System.out.println("ForStatement -- content:\n" + node.toString());

        return true; // if true, will iterate Visitor to it child node as well.
    }

    public boolean visit(IfStatement node) {
        System.out.println("---------------");
        System.out.println(node.getExpression());
        System.out.println(node.getExpression().getClass().getName());
        System.out.println("---------------");
        System.out.println("IFStatement -- content:\n" + node.toString());


        System.out.println("@@@@@@");
//        System.out.println(node.getThenStatement().toString());
        if (node.getElseStatement() != null) {
            System.out.println("shit");
            System.out.println(node.getElseStatement().toString());
            Statement elses = node.getElseStatement();

//            System.out.println(elses.toString());
//            elses.getNodeType()
        }
        System.out.println("@@@@@@");

        return true;
    }
    public boolean visit(InfixExpression node) {
        System.out.println("---------------");
        node.getLeftOperand();
        node.getRightOperand();

//        System.out.println(node.getExpression());
        System.out.println("---------------");
        System.out.println("InfixExpression -- content:\n" + node.toString());
        return false;
    }
}