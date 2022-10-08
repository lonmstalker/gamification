package io.lonmstalker.gamification.model

data class ApiPageDto(
    val page: Int, // номер страницы, если пагинация включена
    val offset: Int, //  количество транзакций отображенных на странице
    val sort: String // параметр сортировки
)