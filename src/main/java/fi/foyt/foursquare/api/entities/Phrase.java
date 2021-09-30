package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Phrase implements FoursquareEntity {
    private static final long serialVersionUID = -4218205439631504051L;
    private String phrase;
    private Integer count;
    private PhraseSample sample;
}
