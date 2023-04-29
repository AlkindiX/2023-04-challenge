package work.alkindix.byanat.challenge.resolvers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Tower {
  // TODO: Use JsonNaming annotation instead of static field names.
  static String[] FIELDS = {
    "tower_id",
    "operator",
    "address",
    "height",
    "tower_type",
    "latitude",
    "longitude",
    "technology"
  };
  public int towerId;
  public String operator;
  public String address;
  public double height;
  public String towerType;
  public double latitude;
  public double longitude;
  public String technology;

  
}
