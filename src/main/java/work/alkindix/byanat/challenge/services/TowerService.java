package work.alkindix.byanat.challenge.services;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import work.alkindix.byanat.challenge.resolvers.Tower;

@Service
public class TowerService {
  private static final String DEFAULT_RADIUS = "50";

  public Set<Tower> getTowers(Map<String,String> allParams, String jsonString) throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Set<Tower> towers = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(Set.class, Tower.class));
    return towers.stream()
      .collect(
        Collectors.filtering(
          tower -> {
            boolean valid = true;
            GeoPoint towerPoint = new GeoPoint(
              tower.latitude,
              tower.longitude
            );
            GeoPoint centerPoint = new GeoPoint();

            for (String key : allParams.keySet()) {
              
              if (key.equals("tower_id")) {
                if (tower.towerId != Integer.parseInt(allParams.get(key))) {
                  valid = false;
                  break;
                }
              } 
              if (key.equals("operator")) {
                if (!tower.operator.equals(allParams.get(key))) {
                  valid = false;
                  break;
                }
              } 
              if (key.equals("address")) {
                if (!tower.address.equals(allParams.get(key))) {
                  valid = false;
                  break;
                }
              } 
              if (key.equals("height")) {
                if (tower.height != Double.parseDouble(allParams.get(key))) {
                  valid = false;
                  break;
                }
              } 
              if (key.equals("tower_type")) {
                if (!tower.towerType.equals(allParams.get(key))) {
                  valid = false;
                  break;
                }
              }
              if (key.equals("technology")) {
                if (!tower.technology.equals(allParams.get(key))) {
                  valid = false;
                  break;
                }
              }
              if (key.equals("latitude")) {
                centerPoint.latitude = Double.parseDouble(allParams.get(key));
              } 
              if (key.equals("longitude")) {
                centerPoint.longitude = Double.parseDouble(allParams.get(key));
              }
            }
            // Check if we have latitude and longitude to check radius
            if (centerPoint.latitude != 0 && centerPoint.longitude != 0) {
              // get radius from params
              double radius = Double.parseDouble(allParams.getOrDefault("radius", DEFAULT_RADIUS));
              System.out.println("radius: " + radius);
              valid = isPointInRadius(
                towerPoint,
                centerPoint,
                radius,
                RadiusUnit.KILOMETER
              );
            }
            return valid;
          },
          Collectors.toSet()
        )
      );
  }

  enum RadiusUnit {
    METER,
    KILOMETER
  }

  class GeoPoint {
    double latitude;
    double longitude;

    public GeoPoint(
      double latitude,
      double longitude
    ) {
      this.latitude = latitude;
      this.longitude = longitude;
    }

    public GeoPoint() {
      // no tower in the middle of the sea :)
      this.latitude = 0;
      this.longitude = 0;
    }
  }

  private boolean isPointInRadius (
    GeoPoint point,
    GeoPoint center,
    double radius,
    RadiusUnit unit
  ) {
    if (unit == RadiusUnit.METER) {
      radius = radius / 1000.0;
    }

    double distance = Math.acos(
      Math.sin(point.latitude) * Math.sin(center.latitude) +
      Math.cos(point.latitude) * Math.cos(center.latitude) *
      Math.cos(center.longitude - point.longitude)
    ) * 6371 * Math.PI / 180;
    if (distance > radius) {
      return false;
    }
    return true;
  }
}
