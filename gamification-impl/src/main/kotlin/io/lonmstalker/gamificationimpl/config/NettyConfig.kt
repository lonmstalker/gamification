package io.lonmstalker.gamificationimpl.config

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame
import io.netty.handler.timeout.IdleStateEvent
import io.netty.handler.timeout.IdleStateEvent.WRITER_IDLE_STATE_EVENT
import io.netty.handler.timeout.IdleStateHandler
import io.netty.handler.timeout.ReadTimeoutException
import io.netty.handler.timeout.WriteTimeoutException
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.stereotype.Component


@Component
class NettyConfig : WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    override fun customize(factory: NettyReactiveWebServerFactory) {
        with(factory) {
            addServerCustomizers(
                { server ->
                    server.doOnConnection { connection ->
                        connection.addHandler(
                            object : IdleStateHandler(0, 0, 10) {
                                override fun channelIdle(
                                    ctx: ChannelHandlerContext,
                                    evt: IdleStateEvent,
                                ) {
                                    ctx.fireExceptionCaught(
                                        if (evt.state() == WRITER_IDLE_STATE_EVENT.state()) WriteTimeoutException.INSTANCE
                                        else ReadTimeoutException.INSTANCE,
                                    )
                                    ctx.write(CloseWebSocketFrame())
                                    ctx.close()
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}