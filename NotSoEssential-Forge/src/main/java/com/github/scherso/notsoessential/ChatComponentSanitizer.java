package com.github.scherso.notsoessential;

import net.minecraft.util.ChatComponentScore;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public final class ChatComponentSanitizer
{

    private ChatComponentSanitizer() {}

    /**
     * Vanilla can't serialize Essential's UTextComponent, which crashes LabyMod's chat.
     * Anything that isn't a vanilla component is copied into a ChatComponentText.
     *
     * @param component component about to be serialized
     * @return the component, or a vanilla copy of it
     */
    public static IChatComponent sanitize(IChatComponent component)
    {
        if (component == null
            || component instanceof ChatComponentText
            || component instanceof ChatComponentTranslation
            || component instanceof ChatComponentScore
            || component instanceof ChatComponentSelector)
            return (component);

        final ChatComponentText copy = new ChatComponentText(component.getUnformattedTextForChat());
        copy.setChatStyle(component.getChatStyle().createShallowCopy());

        /* Siblings get sanitized when the serializer recurses into them. */
        for (Object sibling : component.getSiblings())
            copy.appendSibling((IChatComponent) sibling);

        return (copy);
    }

}
