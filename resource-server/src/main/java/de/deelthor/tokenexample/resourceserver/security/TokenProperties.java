package de.deelthor.tokenexample.resourceserver.security;

public class TokenProperties {
    private String header;
    private String endpoint;

    public TokenProperties(String header, String endpoint) {
        this.header = header;
        this.endpoint = endpoint;
    }

    public TokenProperties() {
    }

    public void setHeader(String header) {
        this.header = header;
    }
    public String getHeader() {
        return header;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getEndpoint() {
        return endpoint;
    }
}
