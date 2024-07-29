package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.mixin.dfu.codec.CombinedCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.dfu.codec.PromotePartialDecoderAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.Nullable;

public record PromotePartialCodecElement(Codec<?> original, IntrospectionElement source) implements MappedElement, CodecIntrospectionElement {
    static @Nullable PromotePartialCodecElement fromCodec(Codec<?> codec) {
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
