package hr.amaurov.niamu.orm_presentation.utils

data class SpinnerCity(val id: Long, val city : String, val country: String ) {

    override fun toString(): String {
        return "$city, $country";
    }
}