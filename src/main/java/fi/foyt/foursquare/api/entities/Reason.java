package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Reason entity
 * 
 * @author Antti Leppä
 */
public class Reason implements FoursquareEntity {

  private static final long serialVersionUID = -6388056927086935959L;

  /**
   * Returns type
   * 
   * @return type
   */
  public String getType() {
    return type;
  }
  
  /**
   * Returns message
   * 
   * @return message
   */
  public String getMessage() {
    return message;
  }

  public String getSummary() {
    return summary;
  }

  public String getReasonName() {
    return reasonName;
  }

  private String type;
  private String message;
  private String summary;
  private String reasonName;

}