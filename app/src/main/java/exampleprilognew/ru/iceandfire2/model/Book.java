package exampleprilognew.ru.iceandfire2.model;

import java.io.Serializable;


public class Book implements Serializable {

    private String name;
    private int numberOfPages;
    private String released;
    private int characters;
    private String charactersList;
    private String authors;
    private String publisher;
    private String country;
    private String mediaType;

    public Book(String name, int numberOfPages, String released, int characters, String charactersList, String authors, String publisher, String country, String mediaType) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.released = released;
        this.characters = characters;
        this.charactersList = charactersList;
        this.authors = authors;
        this.publisher = publisher;
        this.country = country;
        this.mediaType = mediaType;
    }



    public String getName() {
        return name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getReleased() {
        return released;
    }

    public int getCharacters() {
        return characters;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCharactersList() {
        return charactersList;
    }

  public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCountry() {
        return country;
    }

    public String getMediaType() {
        return mediaType;
    }
}
