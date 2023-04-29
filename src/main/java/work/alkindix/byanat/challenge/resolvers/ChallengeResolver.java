package work.alkindix.byanat.challenge.resolvers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import work.alkindix.byanat.challenge.services.TowerService;

@RestController()
@RequestMapping("/challenge")
public class ChallengeResolver {
  
  private static final String HTTPS_BYANAT_WIREMOCKAPI_CLOUD_API_V1_TOWERS = "https://byanat.wiremockapi.cloud/api/v1/towers";

  String jsonCache = null;

  @Autowired
  private TowerService service;

  @GetMapping("towers")
  public ResponseEntity towers(
    @RequestParam Map<String,String> allParams
  ) throws IOException {
    // Make sure allParams contains only valid fields. Check from Tower.FIELDS
    List<String> errors = new ArrayList<String>();
    
    for (String key : allParams.keySet()) {
      if (key.equals("radius")) {
        continue;
      }
      boolean found = false;
      for (String field : Tower.FIELDS) {
        if (key.equals(field)) {
          found = true;
          break;
        }
      }
      if (!found) {
        errors.add(key);
      }
    }

    if (errors.size() > 0) {
      return ResponseEntity.badRequest().body(new InvalidParameter(errors));
    }
    for (String key : allParams.keySet()) {
      allParams.put(key, allParams.get(key).strip());
    }
    // Treat empty parameters as not set
    for (String key : allParams.keySet()) {
      if (allParams.get(key).equals("")) {
        allParams.remove(key);
      }
    }
    
    // Download https://byanat.wiremockapi.cloud/api/v1/towers into json object
    if (jsonCache == null) {
      jsonCache = Request.get(HTTPS_BYANAT_WIREMOCKAPI_CLOUD_API_V1_TOWERS)
        .execute()
        .returnContent()
        .asString();
    }
    
    
    return ResponseEntity.ok(service.getTowers(allParams, jsonCache));
  }
}
