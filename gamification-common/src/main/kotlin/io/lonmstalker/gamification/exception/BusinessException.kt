package io.lonmstalker.gamification.exception

import java.lang.RuntimeException

abstract class BusinessException(
    override val message: String?
) : RuntimeException(message, null, false, false) {
}