package AST;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.eclipse.jdt.core.dom.ASTNode;

import java.lang.reflect.Type;



public class BaseSerializer implements JsonSerializer<ASTNode> {


//    @Override
//    public JsonElement serialize(final Base base, final Type type,
//                                 final JsonSerializationContext context) {
//
//        final Operation operation = base.getOperation();
//        final ASTLocation targetLocation = base.getTargetLocation();
//        final JsonObject serializedBase = new JsonObject();
//
//        serializedBase.addProperty("name", operation.getName());
//        serializedBase.add("fileName",
//                context.serialize(targetLocation.getSourcePath()));
//        serializedBase.addProperty("snippet", operation.getTargetSnippet());
//        serializedBase.add("lineNumberRange", context.serialize(targetLocation.inferLineNumbers()));
//
//        return serializedBase;
//    }

    @Override
    public JsonElement serialize(ASTNode src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
