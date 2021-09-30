package fi.foyt.foursquare.api.entities;


public class AttributeGroup extends Group<Attribute> {

    private static final long serialVersionUID = -7315918567912784899L;
    private String summary;
    private Attribute[] items;

    @Override
    public Attribute[] getItems() {
        return items;
    }
}
