package hr.amaurov.niamu.orm_presentation.orm.greenDao.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

@Entity(
        // Flag if the DAO should create the database table (default is true).
        // Set this to false, if you have multiple entities mapping to one table,
        // or the table creation is done outside of greenDAO.
        createInDb = false,

        // Whether an all properties constructor should be generated.
        // A no-args constructor is always required.
        generateConstructors = true,

        // Whether getters and setters for properties should be generated if missing.
        generateGettersSetters = true
)
public class Contact {
    @Id(autoincrement = true)
    private Long id;

    private String firstName;
    private String lastName;

    // BUG: Cannot make it work with java.time.LocalDate (unsupported type error)
    private java.util.Date dateOfBirth;

    private String email;
    private String phoneNumber;

    private long cityId; // This defines our foreign key.
    @ToOne(joinProperty = "cityId")
    private City city;

    private Boolean isFavorite;
}
