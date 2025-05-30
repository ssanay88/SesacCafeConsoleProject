package model.charge

sealed class ChargeValidationResult {
    object Valid: ChargeValidationResult()
    data class Invalid(val message: String): ChargeValidationResult()
}