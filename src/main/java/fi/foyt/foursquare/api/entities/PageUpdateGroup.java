package fi.foyt.foursquare.api.entities;

public class PageUpdateGroup extends Group<PageUpdate>{

    PageUpdate[] items;

    @Override
    public PageUpdate[] getItems() {
        return items;
    }
}
