package gentester;

import org.assertj.core.api.Assertions;
import cucumber.api.java8.En;

public class Steps extends Assertions implements En, TemplateTest {

    private String definition;
    private String swaggerFile;
    private String generated;

    public Steps() {

        Given("the following swagger file \"([^\"]*)\":$", (String swagger) -> this.swaggerFile = swagger);

        When("^^process the \"([^\"]*)\" definition with the \"([^\"]*)\" codegen class$", (String definition, String codegenClass) -> {
            this.definition = definition;
            this.generated = compiler()
                    .compile(fileContent(template(templateToTest())))
                    .execute(vars(codegenClass));
        });

        Then("^the result should be equals to the file \"([^\"]*)\"$",
             (String expected) -> assertThat(fileContent(expected)).isEqualTo(generated));

        Then("^the result should be equals to:$", (String expected) -> assertThat(generated).isEqualTo(expected));
        
        Then("^the result should contain:$", (String expected) -> assertThat(generated).contains(expected));

        Then("^the result should contain \"([^\"]*)\"$",
             (String expected) -> assertThat(generated).contains(expected));
    }

    @Override
    public String templateToTest() {
        return "model";
    }
}
