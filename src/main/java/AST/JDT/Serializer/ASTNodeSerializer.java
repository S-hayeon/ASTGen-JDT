package AST.JDT.Serializer;
import com.google.gson.*;
import org.eclipse.jdt.core.dom.ASTNode;

import java.lang.reflect.Type;

public class ASTNodeSerializer implements JsonSerializer<ASTNode> {


  @Override
  public JsonElement serialize(final ASTNode node, final Type type,
      final JsonSerializationContext context) {

    JsonObject jObject = new JsonObject();
    int Ntype = node.getNodeType();
    String ClassName = node.nodeClassForType(Ntype).getName().substring(25);

    jObject.addProperty("@type", ClassName);
    return jObject;

//    return new JsonPrimitive(node.toString());
  }



}
