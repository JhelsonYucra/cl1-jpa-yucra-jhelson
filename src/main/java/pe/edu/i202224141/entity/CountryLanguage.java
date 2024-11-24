package pe.edu.i202224141.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "countrylanguage")
public class CountryLanguage {

    @Id
    @Column(name = "Language", length = 30, nullable = false)
    private String language;

    @ManyToOne
    @JoinColumn(name = "CountryCode", nullable = false)
    private Country country;

    @Column(name = "IsOfficial", nullable = false, columnDefinition = "ENUM('T','F')")
    private String isOfficial;

    @Column(name = "Percentage", nullable = false)
    private Double percentage;

    public CountryLanguage() {}

    public CountryLanguage(String language, Country country, String isOfficial, Double percentage) {
        this.language = language;
        this.country = country;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "language='" + language + '\'' +
                ", country=" + (country != null ? country.getCode() : "null") +
                ", isOfficial='" + isOfficial + '\'' +
                ", percentage=" + percentage +
                '}';
    }

    // Getters y Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}

