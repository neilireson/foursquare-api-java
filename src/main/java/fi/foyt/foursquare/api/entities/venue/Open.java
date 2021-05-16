/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities.venue;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Venue Timeframe Open entity
 *
 * @author Neil Ireson
 */
public class Open implements FoursquareEntity {

  private static final long serialVersionUID = -3089247857268218769L;

  private String renderedTime;

  @Override
  public String toString() {
    return "Open{" +
            "renderTime='" + renderedTime + '\'' +
            '}';
  }
}
