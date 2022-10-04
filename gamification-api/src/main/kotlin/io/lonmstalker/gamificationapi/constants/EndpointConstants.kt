package io.lonmstalker.gamificationapi.constants

object EndpointConstants {
    private const val ADMIN_ENDPOINT = "/admin/v1"
    private const val EXTERNAL_ENDPOINT = "/external/v1"

    const val ADMIN_TEAM_ENDPOINT = "$ADMIN_ENDPOINT/team"
    const val ADMIN_OPERATION_ENDPOINT = "$ADMIN_ENDPOINT/operation"
    const val ADMIN_WALLET_ENDPOINT = "$ADMIN_ENDPOINT/user"
    const val ADMIN_TRIGGER_ENDPOINT = "$ADMIN_ENDPOINT/trigger"
    const val ADMIN_ITEMS_ENDPOINT = "$ADMIN_ENDPOINT/items"

    const val WALLET_ENDPOINT = "$EXTERNAL_ENDPOINT/user"
    const val MARKETPLACE_ENDPOINT = "$EXTERNAL_ENDPOINT/marketplace"
}