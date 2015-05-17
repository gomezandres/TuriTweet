/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

/**
 *
 * @author tebs
 */
public class Tweet {
    private Long id;
    private String status;
    private String statusOriginal;
    private String clase;
    private String user_mentions;
    private String polaridad;
    private boolean media;
    private boolean url;
    private String username;
    private String utcdate;
    private String localdate;
    private String location;
    private String timezone;
    

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_mentions() {
        return user_mentions;
    }

    public void setUser_mentions(String user_mentions) {
        this.user_mentions = user_mentions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

    public String getLocaldate() {
        return localdate;
    }

    public void setLocaldate(String localdate) {
        this.localdate = localdate;
    }

    public String getUtcdate() {
        return utcdate;
    }

    public void setUtcdate(String utcdate) {
        this.utcdate = utcdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isMedia() {
        return media;
    }

    public void setMedia(boolean media) {
        this.media = media;
    }

    public String getPolaridad() {
        return polaridad;
    }

    public void setPolaridad(String polaridad) {
        this.polaridad = polaridad;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusOriginal() {
        return statusOriginal;
    }

    public void setStatusOriginal(String statusOriginal) {
        this.statusOriginal = statusOriginal;
    }
    
    
}
