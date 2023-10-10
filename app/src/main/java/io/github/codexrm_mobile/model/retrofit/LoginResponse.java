package io.github.codexrm_mobile.model.retrofit;

import java.util.Date;
import java.util.List;

public class LoginResponse {

    private String refreshToken;
    private Date tokenExpirationDate;
    private Date refreshTokenExpirationDate;
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private boolean enabled;
    private List<String> roles;
    private String tokenType;
    private String accessToken;

    public LoginResponse(String refreshToken, Date tokenExpirationDate, Date refreshTokenExpirationDate, Integer id, String username, String email, String name, String lastName, boolean enabled, List<String> roles,
                         String tokenType, String accessToken) {
        this.refreshToken = refreshToken;
        this.tokenExpirationDate = tokenExpirationDate;
        this.refreshTokenExpirationDate = refreshTokenExpirationDate;
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.enabled = enabled;
        this.roles = roles;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public String getRefreshToken() { return refreshToken; }

    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public Date getTokenExpirationDate() { return tokenExpirationDate; }

    public void setTokenExpirationDate(Date tokenExpirationDate) { this.tokenExpirationDate = tokenExpirationDate; }

    public Date getRefreshTokenExpirationDate() { return refreshTokenExpirationDate; }

    public void setRefreshTokenExpirationDate(Date refreshTokenExpirationDate) { this.refreshTokenExpirationDate = refreshTokenExpirationDate; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public List<String> getRoles() { return roles; }

    public void setRoles(List<String> roles) { this.roles = roles; }

    public String getTokenType() { return tokenType; }

    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public String getAccessToken() { return accessToken; }

    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
}
