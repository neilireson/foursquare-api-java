package fi.foyt.foursquare.api.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fi.foyt.foursquare.api.FoursquareApi;

import static fi.foyt.foursquare.api.Credentials.CLIENT_ID_PROPERTY_KEY;
import static fi.foyt.foursquare.api.Credentials.CLIENT_SECRET_PROPERTY_KEY;
import static fi.foyt.foursquare.api.Credentials.REDIRECT_URL_PROPERTY_KEY;
import static fi.foyt.foursquare.api.Credentials.OAUTH_TOKEN_PROPERTY_KEY;

public class TestUtils {

  public static FoursquareApi getAnonymousFoursquareApi() {
    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, new TestIO());
    foursquareApi.setSkipNonExistingFields(false);
    return foursquareApi;
  }

  public static FoursquareApi getAuthenticatedFoursquareApi() {
    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, OAUTH, new TestIO());
    foursquareApi.setSkipNonExistingFields(false);
    return foursquareApi;
  }

  public static byte[] getFileData(String path) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    byte[] buf = new byte[1024];
    InputStream in = TestUtils.class.getResourceAsStream(path);

    int l;
    while ((l = in.read(buf)) > 0) {
      bos.write(buf, 0, l);
    }

    return bos.toByteArray();
  }

  private final static String CLIENT_ID = System.getProperty(CLIENT_ID_PROPERTY_KEY,"FAKE_CLIENT_ID");
  private final static String CLIENT_SECRET = System.getProperty(CLIENT_SECRET_PROPERTY_KEY,"FAKE_CLIENT_SECRET");
  private final static String REDIRECT_URL = System.getProperty(REDIRECT_URL_PROPERTY_KEY,"FAKE_REDIRECT_URL");
  private final static String OAUTH = System.getProperty(OAUTH_TOKEN_PROPERTY_KEY,"FAKE_OAUTH");
}
