package fi.foyt.foursquare.api.entities;

public class PageUpdateGroup extends Group<PageUpdate>{

    private static final long serialVersionUID = -5749249616486798323L;
    PageUpdate[] items;

    @Override
    public PageUpdate[] getItems() {
        return items;
    }
}
