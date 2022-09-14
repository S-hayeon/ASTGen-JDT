package AST.JDT.Serializer;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ASTNodeSerializer implements JsonSerializer<ASTNode> {


  @Override
  public JsonElement serialize(final ASTNode node, final Type type,
      final JsonSerializationContext context) {
    return new JsonPrimitive(node.toString());
  }
}
