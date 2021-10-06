package fi.foyt.foursquare.api;

import picocli.CommandLine;

@CommandLine.Command(name = "VenueSearchOptions",
        showDefaultValues = true,
        mixinStandardHelpOptions = true,
        version = "1.0")
public class VenueSearchOptions {
    @CommandLine.Option(names = {"--fsq-ll"},
            description = "e.g. 40.74224,-73.99386 \trequired unless near is provided. Latitude and longitude of the user’s location.")
    public String ll;
    @CommandLine.Option(names = {"--fsq-near"},
            description = "e.g. Chicago, IL \trequired unless ll is provided. A string naming a place in the world. If the near string is not geocodable, returns a failed_geocode error. Otherwise, searches within the bounds of the geocode and adds a geocode object to the response.")
    public String near;
    @CommandLine.Option(names = {"--fsq-radius"},
            description = "e.g. 250 \tLimit results to venues within this many meters of the specified location. Defaults to a city-wide area. Only valid for requests that use categoryId or query. The maximum supported radius is currently 100,000 meters.")
    public Integer radius;
    @CommandLine.Option(names = {"--fsq-query"},
            description = "e.g. tacos \tA search term to be applied against venue names.")
    public String query;
    @CommandLine.Option(names = {"--fsq-limit"},
            description = "e.g. 10 \tNumber of results to return, up to 50.")
    public Integer limit;
    @CommandLine.Option(names = {"--fsq-categoryId"},
            description = "e.g. 4bf58dd8d488d11094, 4bf58dd8d1bd941735 \tA comma separated list of categories to limit results to. If you specify categoryId. Specifying a radius may improve results. If specifying a top-level category, all sub-categories will also match the query.")
    public String categoryId;
    @CommandLine.Option(names = {"--fsq-llAcc"},
            description = "e.g. 10000.0 \tAccuracy of latitude and longitude, in meters.")
    public Double llAcc;
    @CommandLine.Option(names = {"--fsq-alt"},
            description = "e.g. 0.0 \tAltitude of the user’s location, in meters.")
    public Double alt;
    @CommandLine.Option(names = {"--fsq-altAcc"},
            description = "e.g. 10000.0 \tAccuracy of the user’s altitude, in meters.")
    public Double altAcc;
    @CommandLine.Option(names = {"--fsq-url"},
            description = "e.g. http://nymag.com/food \tA third-party URL which we will attempt to match against our map of venues to URLs.")
    public String url;
    @CommandLine.Option(names = {"--fsq-providerId"},
            description = "e.g. nymag \tIdentifier for a known third party that is part of our map of venues to URLs, used in conjunction with linkedId.")
    public String providerId;
    @CommandLine.Option(names = {"--fsq-linkedId"},
            description = "e.g. 1002207971611 \tIdentifier used by third party specified in providerId, which we will attempt to match against our map of venues to URLs.")
    public String linkedId;
}
