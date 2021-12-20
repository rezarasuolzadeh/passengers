package ir.rezarasuolzadeh.passengers.utils.interfaces

interface Mapper<First, Second> {
    fun mapResponseToModel(response: First): Second
    fun mapModelToResponse(model: Second): First
}