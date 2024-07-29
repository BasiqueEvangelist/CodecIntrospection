package me.basiqueevangelist.codecintrospection.element.mapcodec.dfu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.CombinedMapCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.FieldDecoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.FieldEncoderAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;

public record FieldElement(MapCodec<?> original, String name, IntrospectionElement codec) implements MapCodecIntrospectionElement {
    @ApiStatus.Internal
    public static FieldElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof CombinedMapCodecAccessor combined)) return null;

        Codec<?> source;
        String name;

        if (combined.getVal$encoder() instanceof FieldEncoderAccessor encoder) {
            name = encoder.getName();

            if (!(encoder.getElementCodec() instanceof Codec<?> src)) return null;

            source = src;
        } else {
            return null;
        }

        if (combined.getVal$decoder() instanceof FieldDecoderAccessor decoder) {
            if (!name.equals(decoder.getName())) return null;

            if (decoder.getElementCodec() != source) return null;
        } else {
            return null;
        }

        return new FieldElement(codec, name, CodecIntrospection.introspect(source));
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "FieldElement [" + name + "]");
        codec.dump(out, indent + 1);
    }
}
