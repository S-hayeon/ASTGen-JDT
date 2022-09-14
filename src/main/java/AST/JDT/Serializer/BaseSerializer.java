//package AST.JDT.Serializer;
//
//
//import java.lang.reflect.Type;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonSerializationContext;
//import com.google.gson.JsonSerializer;
//import jp.kusumotolab.kgenprog.ga.variant.Base;
//import jp.kusumotolab.kgenprog.project.ASTLocation;
//import jp.kusumotolab.kgenprog.project.LineNumberRange;
//import jp.kusumotolab.kgenprog.project.Operation;
//import jp.kusumotolab.kgenprog.project.SourcePath;
//
//
//public class BaseSerializer implements JsonSerializer<Base> {
//
//  @Override
//  public JsonElement serialize(final Base base, final Type type,
//      final JsonSerializationContext context) {
//
//    final Operation operation = base.getOperation();
//    final ASTLocation targetLocation = base.getTargetLocation();
//    final JsonObject serializedBase = new JsonObject();
//
//    serializedBase.addProperty("name", operation.getName());
//    serializedBase.add("fileName",
//        context.serialize(targetLocation.getSourcePath()));
//    serializedBase.addProperty("snippet", operation.getTargetSnippet());
//    serializedBase.add("lineNumberRange", context.serialize(targetLocation.inferLineNumbers()));
//
//    return serializedBase;
//  }
//}
