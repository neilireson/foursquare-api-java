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

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing List entity
 *
 * @see <a href="https://developer.foursquare.com/docs/responses/list.html" target="_blank">https://developer.foursquare.com/docs/responses/list.html</a>
 *
 * @author Blake Dy
 */
public class List implements FoursquareEntity {

  private static final long serialVersionUID = -7255898184182462517L;

  // TODO
  private String id;
  private String name;
  private String description;
  private String type;
  private CompactUser user;
  private String following;
  private Count followers;
  private Boolean editable;
  private Boolean collaborative;
  private String collaborators;
  private String url;
  private String canonicalUrl;
  private Photo photo;
  private String venueCount;
  private String visitedCount;
  private ListGroup listItems;
  private Long createdAt;
  private Long updatedAt;
  private Boolean isPublic;
  private Boolean logView;
  private CompleteTip tip;

  private PhraseEntity[] entities;

  public void setPublic(Boolean isPublic) {
    this.isPublic = isPublic;
  }

  @Override
  public String toString() {
    return "List{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", type='" + type + '\'' +
            ", user='" + user + '\'' +
            ", following='" + following + '\'' +
            ", followers='" + followers + '\'' +
            ", editable='" + editable + '\'' +
            ", collaborative='" + collaborative + '\'' +
            ", collaborators='" + collaborators + '\'' +
            ", url='" + url + '\'' +
            ", canonicalUrl='" + canonicalUrl + '\'' +
            ", photo='" + photo + '\'' +
            ", venueCount='" + venueCount + '\'' +
            ", visitedCount='" + visitedCount + '\'' +
            ", listItems='" + listItems + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", updatedAt='" + updatedAt + '\'' +
            ", entities='" + entities + '\'' +
            ", isPublic=" + isPublic +
            ", logView=" + logView +
            '}';
  }
}
