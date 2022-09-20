package AST;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.eclipse.jdt.core.dom.ASTNode;

import java.lang.reflect.Type;



public class BaseSerializer implements JsonSerializer<ASTNode> {

    @Override
    public JsonElement serialize(ASTNode src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
