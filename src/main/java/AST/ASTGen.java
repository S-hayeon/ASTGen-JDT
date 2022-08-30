package AST;



import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.*;

public class ASTGen {
    public static void main(String[] args) {
        String filePath = "./src/main/resources/demo.java";
        char[] javaCode = getSourceCode(filePath);
        CompilationUnit compilationUnit = getCompilationUnit(javaCode);
        // TODO: visitor
    }

    private static CompilationUnit getCompilationUnit(char[] javaCode) {
        ASTParser astParser = ASTParser.newParser(AST.JLS11);
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
        astParser.setSource(javaCode);
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

