package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonCreator
import io.swagger.v3.oas.annotations.media.Schema

data class Page <T> @JsonCreator constructor(

    @field:Schema(description = "Кол-во элементов")
    val size: Int,

    @field:Schema(description = "Текущая страница")
    val pageNumber: Int,

    @field:Schema(description = "Список элементов")
    val content: List<T>,

    @field:Schema(description = "Доступных данных по запросу")
    val elementCounts: Int,
)

data class PageRq @JsonCreator constructor(

    @field:Schema(description = "Необходимая страница")
    val page: Int,

    @field:Schema(description = "Необходимый размер страницы")
    val pageSize: Int
)