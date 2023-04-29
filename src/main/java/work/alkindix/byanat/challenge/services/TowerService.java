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
  public Set<Tower> getTowers(Map<String,String> allParams, String jsonString) throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Set<Tower> towers = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(Set.class, Tower.class));
    return towers.stream()
      .collect(
        Collectors.filtering(
          tower -> {
            boolean valid = true;
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
              if (key.equals("latitude")) {
                if (tower.latitude != Double.parseDouble(allParams.get(key))) {
                  valid = false;
                  break;
                }
              } 
              if (key.equals("longitude")) {
                if (tower.longitude != Double.parseDouble(allParams.get(key))) {
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
            }
            return valid;
          },
          Collectors.toSet()
        )
      );
  }
}
