package ar.com.domain.entities

data class Post(val id: Int = 0,
                val userId: Int = 0,
                val title: String = "",
                val details: String = ""
)
