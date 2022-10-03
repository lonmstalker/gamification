package io.lonmstalker.gamificationapi.constants

object EndpointConstants {
    private const val ADMIN_ENDPOINT = "/admin/v1"
    const val EXTERNAL_ENDPOINT = "/external/v1"

    const val ADMIN_TEAM_ENDPOINT = "$ADMIN_ENDPOINT/team"
    const val ADMIN_OPERATION_ENDPOINT = "$ADMIN_ENDPOINT/operation"
    const val ADMIN_WALLET_ENDPOINT = "$ADMIN_ENDPOINT/user"
}