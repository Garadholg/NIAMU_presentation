package hr.amaurov.niamu.orm_presentation.models

data class City(
    var id: Long? = null,
    var name: String? = null,
    var country: Country? = null
)
