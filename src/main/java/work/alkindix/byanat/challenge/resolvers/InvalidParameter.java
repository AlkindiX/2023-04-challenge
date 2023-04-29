package work.alkindix.byanat.challenge.resolvers;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InvalidParameter extends ErrorResponse {
  public Set<String> invalidParameters;

  public InvalidParameter(List<String> invalidParameters) {
    this.invalidParameters = Set.copyOf(invalidParameters);
    this.message = "Invalid Parameters";
    this.returnCode = 400;
  }
}
