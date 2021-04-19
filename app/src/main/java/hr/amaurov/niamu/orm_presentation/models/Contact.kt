package hr.amaurov.niamu.orm_presentation.models

import java.time.LocalDate

data class Contact (
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var dateOfBirth: LocalDate? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var isFavorite: Boolean? = false,
    var city: City? = null
)
