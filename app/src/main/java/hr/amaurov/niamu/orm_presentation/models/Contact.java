package hr.amaurov.niamu.orm_presentation.models;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Contact {
    @Id(autoincrement = true)
    private Long id;

    private String firstName;
    private String lastName;

    @Convert(converter = DateConverter.class, columnType = Long.class)
    private java.time.LocalDate dateOfBirth;

    @Unique
    private String email;

    @Unique
    private String phoneNumber;

    private long cityId; // This defines our foreign key.
    @ToOne(joinProperty = "cityId")
    private City city;

    private Boolean isFavorite;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 2046468181)
private transient ContactDao myDao;

@Generated(hash = 1448249221)
public Contact(Long id, String firstName, String lastName,
        java.time.LocalDate dateOfBirth, String email, String phoneNumber, long cityId,
        Boolean isFavorite) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.cityId = cityId;
    this.isFavorite = isFavorite;
}

@Generated(hash = 672515148)
public Contact() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getFirstName() {
    return this.firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return this.lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public java.time.LocalDate getDateOfBirth() {
    return this.dateOfBirth;
}

public void setDateOfBirth(java.time.LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
}

public String getEmail() {
    return this.email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPhoneNumber() {
    return this.phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public long getCityId() {
    return this.cityId;
}

public void setCityId(long cityId) {
    this.cityId = cityId;
}

public Boolean getIsFavorite() {
    return this.isFavorite;
}

public void setIsFavorite(Boolean isFavorite) {
    this.isFavorite = isFavorite;
}

@Generated(hash = 1696970556)
private transient Long city__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 133035617)
public City getCity() {
    long __key = this.cityId;
    if (city__resolvedKey == null || !city__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CityDao targetDao = daoSession.getCityDao();
        City cityNew = targetDao.load(__key);
        synchronized (this) {
            city = cityNew;
            city__resolvedKey = __key;
        }
    }
    return city;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1148592723)
public void setCity(@NotNull City city) {
    if (city == null) {
        throw new DaoException(
                "To-one property 'cityId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.city = city;
        cityId = city.getId();
        city__resolvedKey = cityId;
    }
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2088270543)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getContactDao() : null;
}
}
