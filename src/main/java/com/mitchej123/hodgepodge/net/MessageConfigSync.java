package com.mitchej123.hodgepodge.net;

import com.mitchej123.hodgepodge.config.TweaksConfig;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {

    private boolean longerSentMessages;

    public MessageConfigSync() {
        longerSentMessages = TweaksConfig.longerSentMessages;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        longerSentMessages = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(longerSentMessages);
    }

    public boolean isLongerSentMessages() {
        return longerSentMessages;
    }

    @Override
    public IMessage onMessage(MessageConfigSync message, MessageContext ctx) {
        TweaksConfig.longerSentMessages = message.isLongerSentMessages();

        return null;
    }
}
