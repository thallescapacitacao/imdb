package dtos

data class Search(
    val items: List<Movie>,
    val errorMessage: String
)

data class Movie(
    val id: String,
    val rank: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,
    val imDbRating: String,
    val imDbRatingCount: String
)
