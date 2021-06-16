package fi.foyt.foursquare.example;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.VenueSearchOptions;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import picocli.CommandLine;

/**
 * Basic search example
 *
 * @author rmangi
 * @author n.ireson@sheffield.ac.uk
 */
public class BasicExample {


    private static void venueSearch(FoursquareApi foursquareApi, String[] args) {
        VenueSearchOptions options = CommandLine.populateCommand(new VenueSearchOptions(), args);

        try {
            // After client has been initialized we can make queries.
            Result<VenuesSearchResult> result =
                    foursquareApi.venuesSearch(options.ll, options.llAcc, options.alt, options.altAcc,
                            options.query, options.limit, null, options.categoryId, options.url,
                            options.providerId, options.linkedId, options.radius, options.near);

            if (result.getMeta().getCode() == 200) {
                // if query was ok we can finally we do something with the data
                for (CompactVenue venue : result.getResult().getVenues()) {
                    System.out.println(venue);
                }
            } else {
                System.out.println("Error occurred: ");
                System.out.println("  code: " + result.getMeta().getCode());
                System.out.println("  type: " + result.getMeta().getErrorType());
                System.out.println("  detail: " + result.getMeta().getErrorDetail());
            }
        } catch (FoursquareApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * see https://developer.foursquare.com/docs/build-with-foursquare/categories/
     * @param foursquareApi
     */
    private static void getCategories(FoursquareApi foursquareApi) {

        try {
            Result<Category[]> results = foursquareApi.venuesCategories();
            for (Category category : results.getResult()) {
                System.out.println(category);
            }

        } catch (FoursquareApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Initialize FoursquareApi.
        FoursquareApi foursquareApi = new FoursquareApi();

//        venueSearch(foursquareApi, args);

        getCategories(foursquareApi);
    }
}
