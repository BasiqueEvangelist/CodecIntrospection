package me.basiqueevangelist.codecintrospection.element.codec.dfu;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.MappedElement;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.CombinedCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.PromotePartialDecoderAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public record PromotePartialCodecElement(Codec<?> original, IntrospectionElement source) implements MappedElement, CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable PromotePartialCodecElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof CombinedCodecAccessor combined)) return null;
        if (!(combined.getVal$encoder() instanceof Codec<?> src)) return null;
        if (!(combined.getVal$decoder() instanceof PromotePartialDecoderAccessor promotePartial)) return null;
        if (promotePartial.getThis$0() != src) return null;

        return new PromotePartialCodecElement(codec, CodecIntrospection.introspect(src));
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "PromotePartialCodecElement");
        source.dump(out, indent + 1);
    }
}
