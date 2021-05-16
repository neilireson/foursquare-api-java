package fi.foyt.foursquare.api.entities;


public class AttributeGroup extends Group<Attribute> {

    private String summary;
    private Attribute[] items;

    @Override
    public Attribute[] getItems() {
        return items;
    }
}
