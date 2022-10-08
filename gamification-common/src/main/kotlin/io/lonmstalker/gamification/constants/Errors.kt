package io.lonmstalker.gamification.constants

object Errors {
    // logs
    const val ERROR_GENERATE_NFT_LOG = "Ошибка генерации NFT: '{}'"
    const val ERROR_GET_NFT_INFO_LOG = "Ошибка получении информации об NFT: '{}'"
    const val ERROR_GET_NFT_LOG = "Ошибка получении списка NFT: '{}'"
    const val ERROR_TRANSFER_LOG = "Ошибка обмена {}: '{}'"
    const val ERROR_CHECK_TRANSFER_LOG = "Ошибка проверки статуса обмена с хешом '{}': '{}'"
    const val ERROR_CREATE_WALLET_LOG = "Ошибка создания кошелька: '{}'"
    const val ERROR_GET_WALLET_LOG = "Ошибка получения баланса: '{}'"
    const val ERROR_GET_NFT_WALLET_LOG = "Ошибка получения баланса в NFT: '{}'"
    const val ERROR_GET_TRANSACTIONS_LOG = "Ошибка получения списка транзакций: '{}'"
    const val ERROR_FORBIDDEN_ACCESS_LOG = "Пользователь '{}' пытался '{}'"

    // messages
    const val ERROR_GENERATE_NFT = "Ошибка генерации NFT"
    const val ERROR_GET_NFT_INFO = "Ошибка получении информации об NFT"
    const val ERROR_GET_NFT_LIST = "Ошибка получении списка NFT"
    const val ERROR_TRANSFER = "Ошибка обмена {}"
    const val ERROR_CHECK_TRANSFER = "Ошибка проверки статуса обмена"
    const val ERROR_CREATE_WALLET = "Ошибка создания кошелька"
    const val ERROR_GET_WALLET = "Ошибка получения баланса"
    const val ERROR_GET_NFT_WALLET = "Ошибка получения баланса в NFT"
    const val ERROR_GET_TRANSACTIONS = "Ошибка получения списка транзакций"

    const val SERVER_ERROR = "Ошибка работы сервиса"

    // info messages
    const val NEWS_NOT_FOUND = "Новость не найдена"
    const val COMMENTS_CLOSED = "Комментарии закрыты"
    const val FORBIDDEN_CHANGE_LIKE = "Лайки можно обновлять только через специальную кнопку"
    const val COMMENT_FORBIDDEN = "Нет доступа к комментарию"
}