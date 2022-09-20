package AST;

import com.google.gson.Gson;
import org.eclipse.jdt.core.dom.*;

//

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ASTGen {

    public static void main(String[] args) throws IOException {
        String filePath = "./src/main/resources/demo.java";
        char[] javaCode = getSourceCode(filePath);
        CompilationUnit compilationUnit = getCompilationUnit(javaCode);
        ASTNode root = compilationUnit.getRoot();
        visitTree(root);


    }
    private static ArrayList<ASTNode> getChildren(ASTNode node) {
        ArrayList<ASTNode> children = new ArrayList<ASTNode>();
        List<Object> list = node.structuralPropertiesForType();

        for (int i = 0; i < list.size(); i++) {
            StructuralPropertyDescriptor curr = (StructuralPropertyDescriptor) list.get(i);
            Object child = node.getStructuralProperty(curr);
            if (child instanceof List) {
                children.addAll((Collection<? extends ASTNode>) child);
            } else if (child instanceof ASTNode) {
                children.add((ASTNode) child);
            } else{
            }
        }
        return children;
    }


    private static void visitNode(ASTNode node) {
        ArrayList<ASTNode> children = getChildren(node);
        System.out.println(node.getClass().toString());
        String nodeType = ASTNode.nodeClassForType(node.getNodeType()).getSimpleName();

        System.out.println("##########");
        System.out.println(nodeType);
        if (children.size() > 0) {
            for (ASTNode child : children) {
                visitNode(child);
            }
            System.out.println();
        } else {
            System.out.println(node.toString());
        }
    }

    private static void visitTree(ASTNode root) {
        visitNode(root);

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


