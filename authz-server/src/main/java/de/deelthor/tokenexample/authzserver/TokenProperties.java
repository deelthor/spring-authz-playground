package de.deelthor.tokenexample.authzserver;

public class TokenProperties {
    private long maxAgeSeconds;
    private String secret;
    private String issuer;

    public TokenProperties(long maxAgeSeconds, String secret, String issuer) {
        this.maxAgeSeconds = maxAgeSeconds;
        this.secret = secret;
        this.issuer = issuer;
    }

    public TokenProperties() {
    }

    public long getMaxAgeSeconds() {
        return maxAgeSeconds;
    }

    public void setMaxAgeSeconds(long maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssuer() {
        return issuer;
    }
}
