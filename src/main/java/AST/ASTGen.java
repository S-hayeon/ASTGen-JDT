package AST;



import AST.JDT.ASTStream;
import AST.JDT.CustomVisitor;
import AST.JDT.Serializer.ASTNodeSerializer;
import org.eclipse.jdt.core.dom.*;

//
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.Document;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ASTGen {

    public static void main(String[] args) {
        String filePath = "./src/main/resources/demo.java";
        char[] javaCode = getSourceCode(filePath);
        CompilationUnit compilationUnit = getCompilationUnit(javaCode);

        List<ASTNode> actual = ASTStream.stream(compilationUnit.getRoot())
        .collect(Collectors.toList());


        for(int i=0;i<actual.size();i++){
            if (actual.get(i).getClass().getSimpleName().equals("PackageDeclaration")){
                ASTNode node = actual.get(i);
                System.out.println(node.toString());
                System.out.println(node.getParent().getClass().toString());


            }

            if (actual.get(i).getClass().getSimpleName().equals("TypeDeclaration")){
                ASTNode astNode = actual.get(i);
//                System.out.println(astNode.getStartPosition());
//                System.out.println(actual.get(i));
            }
//            System.out.println(actual.get(i));
//            System.out.println("********");
//            System.out.println(actual.get(i).getClass());
//            System.out.println("=======");
//            System.out.println("=======");
//            System.out.println("=======");

        }
//        System.out.println(actual);
//		Statement first = statements.get(0);
//		VariableDeclarationStatement invocation = (VariableDeclarationStatement)first;

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


