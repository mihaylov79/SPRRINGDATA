package springdata.bookshop.data.entities;

import jakarta.persistence.*;
import springdata.bookshop.data.entities.Enums.Edition;
import springdata.bookshop.data.entities.Enums.Restriction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(length = 50,nullable = false)
    private String title;


    @Column(columnDefinition = "TEXT(1000)")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "edition_type",nullable = false)
    private Edition editionType;

    @Basic(optional = false)
    private BigDecimal price;

    @Basic(optional = false)
    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction",nullable = false)
    private Restriction ageRestriction;

    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "book_Id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private Set<Category>categories;

    public Book(){
        this.categories = new HashSet<>();
    }

    public Book(Long bookId, String title, String description, Edition editionType, BigDecimal price, int copies, LocalDate releaseDate, Restriction ageRestriction, Author author, Set<Category> categories) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public Book(Edition editionType, LocalDate releaseDate, int copies, BigDecimal price, Restriction ageRestriction, String title) {
        this.editionType = editionType;
        this.releaseDate = releaseDate;
        this.copies = copies;
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.title = title;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Enum<Edition> getEditionType() {
        return editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCopies() {
        return copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Enum<Restriction> getAgeRestriction() {
        return ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEditionType(Edition editionType) {
        this.editionType = editionType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAgeRestriction(Restriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
