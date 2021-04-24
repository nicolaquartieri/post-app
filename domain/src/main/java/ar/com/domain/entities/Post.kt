package ar.com.domain.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Post(val id: Int = 0,
                val userId: Int = 0,
                val title: String = "",
                val body: String = ""
)
