package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Phrase implements FoursquareEntity {
    private String phrase;
    private Integer count;
    private PhraseSample sample;
}
