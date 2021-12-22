package ir.rezarasuolzadeh.passengers.utils.interfaces

interface DataSource<Input, Output> {
    suspend fun fetch(input: Input): Output
}