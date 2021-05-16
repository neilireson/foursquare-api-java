package fi.foyt.foursquare.example;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import picocli.CommandLine;

/**
 * Basic search example
 *
 * @author rmangi
 * @TODO - more examples please :)
 */
@CommandLine.Command(name = "example",
        mixinStandardHelpOptions = true,
        version = "1.0")
public class BasicExample
        implements Runnable {

    @CommandLine.Option(names = {"--ll"},
            description = "e.g. 40.74224,-73.99386 \trequired unless near is provided. Latitude and longitude of the user’s location.")
    private String ll;
    @CommandLine.Option(names = {"--near"},
            description = "e.g. Chicago, IL \trequired unless ll is provided. A string naming a place in the world. If the near string is not geocodable, returns a failed_geocode error. Otherwise, searches within the bounds of the geocode and adds a geocode object to the response.")
    private String near;
    @CommandLine.Option(names = {"--radius"},
            description = "e.g. 250 \tLimit results to venues within this many meters of the specified location. Defaults to a city-wide area. Only valid for requests that use categoryId or query. The maximum supported radius is currently 100,000 meters.")
    private Integer radius;
    @CommandLine.Option(names = {"--query"},
            description = "e.g. tacos \tA search term to be applied against venue names.")
    private String query;
    @CommandLine.Option(names = {"--limit"},
            description = "e.g. 10 \tNumber of results to return, up to 50.")
    private Integer limit;
    @CommandLine.Option(names = {"--categoryId"},
            description = "e.g. 4bf58dd8d488d11094, 4bf58dd8d1bd941735 \tA comma separated list of categories to limit results to. If you specify categoryId. Specifying a radius may improve results. If specifying a top-level category, all sub-categories will also match the query.")
    private String categoryId;
    @CommandLine.Option(names = {"--llAcc"},
            description = "e.g. 10000.0 \tAccuracy of latitude and longitude, in meters.")
    private Double llAcc;
    @CommandLine.Option(names = {"--alt"},
            description = "e.g. 0.0 \tAltitude of the user’s location, in meters.")
    private Double alt;
    @CommandLine.Option(names = {"--altAcc"},
            description = "e.g. 10000.0 \tAccuracy of the user’s altitude, in meters.")
    private Double altAcc;
    @CommandLine.Option(names = {"--url"},
            description = "e.g. http://nymag.com/food \tA third-party URL which we will attempt to match against our map of venues to URLs.")
    private String url;
    @CommandLine.Option(names = {"--providerId"},
            description = "e.g. nymag \tIdentifier for a known third party that is part of our map of venues to URLs, used in conjunction with linkedId.")
    private String providerId;
    @CommandLine.Option(names = {"--linkedId"},
            description = "e.g. 1002207971611 \tIdentifier used by third party specified in providerId, which we will attempt to match against our map of venues to URLs.")
    private String linkedId;

    @Override
    public void run() {
        // First we need a initialize FoursquareApi.
        FoursquareApi foursquareApi = new FoursquareApi();

        try {
            // After client has been initialized we can make queries.
            Result<VenuesSearchResult> result =
                    foursquareApi.venuesSearch(ll, llAcc, alt, altAcc, query, limit, null, categoryId, url, providerId, linkedId, radius, near);

            if (result.getMeta().getCode() == 200) {
                // if query was ok we can finally we do something with the data
                for (CompactVenue venue : result.getResult().getVenues()) {
                    // TODO: Do something we the data
                    System.out.println(venue);
                }
            } else {
                // TODO: Proper error handling
                System.out.println("Error occurred: ");
                System.out.println("  code: " + result.getMeta().getCode());
                System.out.println("  type: " + result.getMeta().getErrorType());
                System.out.println("  detail: " + result.getMeta().getErrorDetail());
            }
        } catch (FoursquareApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CommandLine(new BasicExample()).execute(args);
    }
}
