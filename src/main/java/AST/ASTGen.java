package AST;



import AST.JDT.ASTStream;
import AST.JDT.Serializer.CompilationUnitSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.eclipse.jdt.core.dom.*;

//

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ASTGen {

    public static void main(String[] args) throws IOException {
        String filePath = "./src/main/resources/demo.java";
        char[] javaCode = getSourceCode(filePath);
        CompilationUnit compilationUnit = getCompilationUnit(javaCode);

        List<ASTNode> actual = ASTStream.stream(compilationUnit.getRoot())
        .collect(Collectors.toList());
        ASTNode ast = compilationUnit.getRoot();
        List<StructuralPropertyDescriptor> descriptorList = ast.structuralPropertiesForType();
        System.out.println(descriptorList.toString());
        System.out.println(descriptorList.size());

        final int Ntype = ast.getNodeType();
        String ClassName = ast.nodeClassForType(Ntype).getName().substring(25);
        System.out.println(ClassName);





//        for(int i=0;i<actual.size();i++){
//            ASTNode ast = actual.get(i);
//            List<StructuralPropertyDescriptor> descriptorList = ast.structuralPropertiesForType();
//            System.out.println(descriptorList.toString());
//            System.out.println(descriptorList.size());
//
//            final int Ntype = ast.getNodeType();
//            String ClassName = ast.nodeClassForType(Ntype).getName().substring(25);
//            System.out.println(ClassName);
//
//
////            if (actual.get(i).getClass().getSimpleName().equals("PackageDeclaration")){
////                ASTNode node = actual.get(i);
////                System.out.println(node.toString());
////                System.out.println(node.getParent().getClass().toString());
////
////            }
//        }

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


