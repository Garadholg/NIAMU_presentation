package hr.amaurov.niamu.orm_presentation.orm.greenDao.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

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
public class City {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "CUSTOM_COLUMN_NAME") // Specifies the column name in the table.
    private String name;

    private long countryId; // This defines our foreign key.
    @ToOne(joinProperty = "countryId") // 1:1 relation
    private Country country;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 448079911)
private transient CityDao myDao;
@Generated(hash = 684499629)
public City(Long id, String name, long countryId) {
    this.id = id;
    this.name = name;
    this.countryId = countryId;
}
@Generated(hash = 750791287)
public City() {
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
public long getCountryId() {
    return this.countryId;
}
public void setCountryId(long countryId) {
    this.countryId = countryId;
}
@Generated(hash = 1591299782)
private transient Long country__resolvedKey;
/** To-one relationship, resolved on first access. */
@Generated(hash = 240571258)
public Country getCountry() {
    long __key = this.countryId;
    if (country__resolvedKey == null || !country__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CountryDao targetDao = daoSession.getCountryDao();
        Country countryNew = targetDao.load(__key);
        synchronized (this) {
            country = countryNew;
            country__resolvedKey = __key;
        }
    }
    return country;
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 617812975)
public void setCountry(@NotNull Country country) {
    if (country == null) {
        throw new DaoException(
                "To-one property 'countryId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.country = country;
        countryId = country.getId();
        country__resolvedKey = countryId;
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
@Generated(hash = 293508440)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCityDao() : null;
}
}
