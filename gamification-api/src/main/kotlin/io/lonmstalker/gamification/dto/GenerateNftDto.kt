package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class GenerateNftDto(
    @field:Schema(
        description = "Ссылка на картинку",
        example = "ipfs://bafybeifesqvvmmtcjlmeso3zaqh56mhttgza2eglw7zwy4ryuyifduy4i/images/star.png"
    )
    val uri: String,

    @field:Schema(description = "Колчество генерируемых NFT")
    val count: Int
)
