package domain

import javax.validation.Validation
import javax.validation.constraints.Email

data class EmailAddress(@field:Email val value: String) {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    init {
        validator.validate(this).apply {
            if (isNotEmpty()) throw InvalidEmail()
        }
    }

    class InvalidEmail : Exception()
}