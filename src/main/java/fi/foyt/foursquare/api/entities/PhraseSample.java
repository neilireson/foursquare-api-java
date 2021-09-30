package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class PhraseSample implements FoursquareEntity {
    private static final long serialVersionUID = 5628782720168166066L;
    private String text;
    private PhraseEntity[] entities;
}
