package fi.foyt.foursquare.api;

public class Endpoints {

    public enum Group {
        venue,
        user
    }

    public enum Venue {
        search,
        explore,
        select,
        trending,
        suggestcompletion,
        likes,
        categories,
        similar,
        nextvenues,
        listed,
        timeseries,
        stats,
        managed,
        add,
        claim,
        flag,
        proposededit,
        like
    }

    public enum User {
        details,
        checkins,
        photos,
        venuehistory,
        venuelikes,
        lists
    }
}
