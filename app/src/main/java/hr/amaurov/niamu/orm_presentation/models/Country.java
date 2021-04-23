package hr.amaurov.niamu.orm_presentation.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
    // Flag if the DAO should create the database table (default is true).
    // Set this to false, if you have multiple entities mapping to one table,
    // or the table creation is done outside of greenDAO.
    createInDb = true,

    // Whether an all properties constructor should be generated.
    // A no-args constructor is always required.
    generateConstructors = true,

    // Whether getters and setters for properties should be generated if missing.
    generateGettersSetters = true
)
public class Country {
    @Id(autoincrement = true)
    private Long id;

    private String name;

    @Generated(hash = 1662620089)
    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 668024697)
    public Country() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
