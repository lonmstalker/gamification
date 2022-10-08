package io.lonmstalker.gamification.constants

object EndpointConstants {
    private const val ADMIN_ENDPOINT = "/admin/v1"
    private const val EXTERNAL_ENDPOINT = "/external/v1"

    const val ADMIN_TEAM_ENDPOINT = "$ADMIN_ENDPOINT/teams"
    const val ADMIN_OPERATION_ENDPOINT = "$ADMIN_ENDPOINT/operations"
    const val ADMIN_WALLET_ENDPOINT = "$ADMIN_ENDPOINT/wallets"
    const val ADMIN_TRIGGER_ENDPOINT = "$ADMIN_ENDPOINT/trigger"
    const val ADMIN_ITEMS_ENDPOINT = "$ADMIN_ENDPOINT/items"
    const val ADMIN_NEWS_ENDPOINT = "$ADMIN_ENDPOINT/news"

    const val WALLET_ENDPOINT = "$EXTERNAL_ENDPOINT/wallets"
    const val MARKETPLACE_ENDPOINT = "$EXTERNAL_ENDPOINT/marketplace"
    const val NEWS_ENDPOINT = "$EXTERNAL_ENDPOINT/news"
}