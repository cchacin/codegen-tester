Feature: Models

  Scenario: Verify definitions
    Given the following swagger file "2_0/petstore.json":

    When process the "Pet" definition with the "io.swagger.codegen.languages.JavaClientCodegen" codegen class

#    Then the result should be equals to the file "fixtures/Pet.java"
    Then the result should contain:
      """
      package ???;
      
      import java.util.Objects;
      """
    And the result should contain "package ???"
    And the result should be equals to:
      """
      package ???;
 
      import java.util.Objects;
      
      """
    
