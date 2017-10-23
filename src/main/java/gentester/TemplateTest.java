package gentester;

import com.google.common.io.Resources;

import com.samskivert.mustache.Mustache;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import cucumber.runtime.CucumberException;
import io.swagger.codegen.DefaultCodegen;

public interface TemplateTest {

    String templateToTest();

    default Map<String, Object> vars(String codegenClass) {
        try {
            final Object o = (DefaultCodegen) Class.forName(codegenClass).newInstance();
        } catch (Exception ex) {
            throw new CucumberException(ex.getMessage(), ex);
        }
        return new HashMap<>();
    }

    default String template(String name) {
        return templateDirectory() + "/" + name + ".mustache";
    }

    default String templateDirectory() {
        return "Java/libraries/my";
    }

    default String fileContent(String fileName) throws IOException {
        return Resources.toString(Resources.getResource(fileName), Charset.defaultCharset());
    }

    default Mustache.Compiler compiler() {
        return Mustache.compiler().defaultValue("???").withLoader(
            name -> new FileReader(new File(Resources.getResource(template(name)).toURI())));
    }
}
