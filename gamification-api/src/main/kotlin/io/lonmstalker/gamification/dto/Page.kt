package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class Page<T> @JsonCreator constructor(

    @field:Schema(description = "Кол-во элементов")
    val size: Int,

    @field:Schema(description = "Текущая страница")
    val page: Int,

    @field:Schema(description = "Список элементов")
    val content: List<T>,

    @field:Schema(description = "Доступных страниц по запросу")
    val availablePages: Int,

    @field:Schema(description = "Доступных данных по запросу")
    val elementCounts: Long,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class PageRq @JsonCreator constructor(

    @field:Schema(description = "Необходимая страница")
    val page: Int = 1,

    @field:Schema(description = "Необходимый размер страницы")
    val offset: Int = 10,

    @field:Schema(description = "Список фильтров для поиска")
    var filter: MutableSet<FilterRq>? = null,

    @field:Schema(description = "Тип сортировки")
    var sort: SortRq? = null,

    @field:Schema(description = "Список необходимых полей объекта, если пусто - все")
    val selectFields: MutableList<String>? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class FilterRq @JsonCreator constructor(

    @field:Schema(description = "Необходимое значение")
    val value: Any,

    @field:Schema(description = "Необходимое поле")
    val fieldName: String,

    @field:Schema(description = "Необходимая операция")
    val operation: Operation
) {
    enum class Operation {
        EQUAL, NOT_EQUAL, IN, IS_NULL, NOT_NULL
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class SortRq @JsonCreator constructor(

    @field:Schema(description = "По какому полю")
    val fieldName: String,

    @field:Schema(description = "Тип сортировки")
    val direction: Direction
) {
    enum class Direction {
        ASC, DESC
    }
}