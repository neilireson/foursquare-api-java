package fi.foyt.foursquare.api;

public class Credentials {

    public static final String CLIENT_ID_PROPERTY_KEY = "foursquare.client.id";
    public static final String CLIENT_SECRET_PROPERTY_KEY = "foursquare.client.secret";
    public static final String REDIRECT_URL_PROPERTY_KEY = "foursquare.redirect.url";
    public static final String OAUTH_TOKEN_PROPERTY_KEY = "foursquare.oauth.token";

    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String oAuthToken;

    /**
     * Constructor
     *
     * By default, attempts to acquire credentials from system properties
     */
    public Credentials() {
        clientId = System.getProperty(CLIENT_ID_PROPERTY_KEY, null);
        clientSecret = System.getProperty(CLIENT_SECRET_PROPERTY_KEY, null);
        redirectUrl = System.getProperty(REDIRECT_URL_PROPERTY_KEY, null);
        oAuthToken = System.getProperty(OAUTH_TOKEN_PROPERTY_KEY, null);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getoAuthToken() {
        return oAuthToken;
    }

    public void setoAuthToken(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }
}
