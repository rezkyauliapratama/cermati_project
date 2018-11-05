package android.rezkyauliapratama.com.cermatiproject.data.network.schema

data class SearchResponseSchema(
    val incomplete_results: Boolean,
    val items: List<UserSchema>,
    val total_count: Int
)