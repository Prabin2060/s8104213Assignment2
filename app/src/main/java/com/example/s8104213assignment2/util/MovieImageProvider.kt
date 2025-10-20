package com.example.s8104213assignment2.util // Make sure this line is exactly correct

// This object provides a poster URL for a given movie title
object MovieImageProvider {

    private val moviePosterMap = mapOf(
        "The Godfather" to "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        "Pulp Fiction" to "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
        "The Shawshank Redemption" to "https://image.tmdb.org/t/p/w500/9O7gLzmBCUjxYm5pagJgZpmeevp.jpg",
        "Schindler's List" to "https://image.tmdb.org/t/p/w500/sF1U4EUQS8YHGqgKfMQAGd1aRV.jpg",
        "Forrest Gump" to "https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg",
        "The Matrix" to "https://image.tmdb.org/t/p/w500/f89JAYsfte0HYJObGqAYALEwJAH.jpg",
        "Citizen Kane" to "https://image.tmdb.org/t/p/w500/k2E6iYN1upc3eIeAUoVp8i7h3I3.jpg"
        // Add more movie titles and URLs here if needed
    )

    fun getPosterUrl(title: String?): String? {
        return moviePosterMap[title]
    }
}