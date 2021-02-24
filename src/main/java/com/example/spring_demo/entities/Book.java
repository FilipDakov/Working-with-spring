package com.example.spring_demo.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@NamedStoredProcedureQuery(name = "Book.numberOfBooks", procedureName = "GetBooksByAuthorTwo", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "firstName", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastName", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "numbers", type = Integer.class) })
@Table(name ="books")
public class Book extends BaseEntity {
    public String title;
    public String description;
    public EditionType editionType;
    public BigDecimal price;
    public int copies;
    public LocalDate releaseDate;
    public AgeRestriction ageRestriction;
    public Author author;
    public Set<Category> categories;


    public Book(){}

    @Column(name = "title",nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.ORDINAL)
    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    @Column(name = "price",nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Enumerated(EnumType.ORDINAL)
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @Column(name = "copies",nullable = false)
    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
