package fi.foyt.foursquare.cli;

import fi.foyt.foursquare.api.Endpoints;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.VenueSearchOptions;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import picocli.CommandLine;

import java.util.Arrays;

/**
 * Command line interface
 *
 * @author rmangi
 * @author n.ireson@sheffield.ac.uk
 */
@CommandLine.Command(name = "FoursquareCli",
        showDefaultValues = true,
        mixinStandardHelpOptions = true,
        version = "1.0")
public class FoursquareCli {
    @CommandLine.Option(names = {"--fsq-endpoint"}, required = true, arity = "2",
            description = "FourSquare endpoint, [group endpoint], e.g. venue search")
    String[] endpoint;

    private static void venueSearch(FoursquareApi foursquareApi, String[] args) {
        VenueSearchOptions options = new VenueSearchOptions();
        CommandLine commandLine = new CommandLine(options);
        commandLine.setUnmatchedArgumentsAllowed(true);
        commandLine.parseArgs(args);

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
     *
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
        FoursquareCli cli = new FoursquareCli();

        CommandLine commandLine = new CommandLine(cli);
        commandLine.setUnmatchedArgumentsAllowed(true);
        commandLine.parseArgs(args);

        Endpoints.Group endpointGroup = Endpoints.Group.valueOf(cli.endpoint[0]);

        // Initialize FoursquareApi.
        FoursquareApi foursquareApi = new FoursquareApi();

        switch (endpointGroup) {
            case venue:
                switch (Endpoints.Venue.valueOf(cli.endpoint[1])) {
                    case search:
                        venueSearch(foursquareApi, args);
                        break;
                    case categories:
                        getCategories(foursquareApi);
                        break;
                    default:
                        throw new UnsupportedOperationException("Cannot handle endpoint: " +
                                Arrays.toString(cli.endpoint));
                }
            default:
                throw new UnsupportedOperationException("Cannot handle endpoint group: " + cli.endpoint[0]);
        }

    }
}
