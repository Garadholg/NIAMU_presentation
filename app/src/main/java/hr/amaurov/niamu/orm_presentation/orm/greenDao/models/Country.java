package hr.amaurov.niamu.orm_presentation.orm.greenDao.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

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
public class Country {
    @Id(autoincrement = true)
    private Long id;

    private String name;
}
