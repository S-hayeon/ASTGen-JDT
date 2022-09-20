package AST;

import org.eclipse.jdt.core.dom.*;

//

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ASTGen {

    public static void main(String[] args) throws IOException {
        String filePath = "./src/main/resources/demo.java";
        char[] javaCode = getSourceCode(filePath);
        CompilationUnit compilationUnit = getCompilationUnit(javaCode);

        ASTNode root = compilationUnit.getRoot();

        String x = visitTree(root);
        System.out.println(x);



    }
    private static ArrayList<ASTNode> getChildren(ASTNode node) {
        ArrayList<ASTNode> nodeList = new ArrayList<ASTNode>();
        List<Object> list = node.structuralPropertiesForType();
        for (int i = 0; i < list.size(); i++) {
            StructuralPropertyDescriptor curr = (StructuralPropertyDescriptor) list.get(i);
            Object child = node.getStructuralProperty(curr);
            if (child instanceof List) {
                nodeList.addAll((Collection<? extends ASTNode>) child);
            } else if (child instanceof ASTNode) {
                nodeList.add((ASTNode) child);
            } else {
            }
        }
        return nodeList;
    }

    private static void visitNode(StringBuffer result, String indent, ASTNode node) {
        ArrayList<ASTNode> children = getChildren(node);
        String nodeType = ASTNode.nodeClassForType(node.getNodeType()).getSimpleName();
        if (children.size() > 0) {
            result.append(indent + "<" + nodeType + ">\n");
            for (ASTNode child : children) {
                visitNode(result, indent + "   ", child);
            }
            result.append(indent + "</" + nodeType + ">\n");
        } else {
            result.append(indent + "<" + nodeType + ">");
            result.append(node.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"));
            result.append("</" + nodeType + ">\n");
        }
    }

    protected static String visitTree(ASTNode root) {
        StringBuffer result = new StringBuffer("");
        visitNode(result, "", root);
        return result.toString();
    }

    private static CompilationUnit getCompilationUnit(char[] javaCode) {
        ASTParser astParser = ASTParser.newParser(AST.JLS11);
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
        astParser.setSource(javaCode);
		astParser.setResolveBindings(true);
        CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);
        return compilationUnit;
    }

    public static char[] getSourceCode(String javaFilePath) {
        byte[] input = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
            input = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] javaCode = new String(input).toCharArray();
        return javaCode;
    }
}


