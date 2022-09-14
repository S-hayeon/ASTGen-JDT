package AST.JDT;


import java.nio.file.Path;

import AST.JDT.Serializer.ASTNodeSerializer;
import com.google.gson.GsonBuilder;
import org.eclipse.jdt.core.dom.ASTNode;

import com.google.gson.Gson;


class JsonExporter  {

  private final Path outdir;
  public static final String JSON_FILENAME = "history.json";

  JsonExporter(final Path outdir) {
    this.outdir = outdir;
  }

  private Gson setupGson() {
    return new GsonBuilder().registerTypeHierarchyAdapter(ASTNode.class, new ASTNodeSerializer()).create();

  }
}