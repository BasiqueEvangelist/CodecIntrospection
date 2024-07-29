package me.basiqueevangelist.codecintrospection.element.codec.dfu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.MappedElement;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.*;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.xmap.ComappedEncoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.xmap.FlatComappedEncoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.xmap.FlatMappedDecoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.xmap.MappedDecoderAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public record UnknownMappedCodecElement(Codec<?> original, IntrospectionElement source, Function<?, DataResult<?>> to, Function<?, DataResult<?>> from) implements MappedElement, CodecIntrospectionElement {
    @SuppressWarnings("unchecked")
    @ApiStatus.Internal
    public static @Nullable UnknownMappedCodecElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof CombinedCodecAccessor combined)) return null;

        Function<?, DataResult<?>> to;
        Function<?, DataResult<?>> from;
        Codec<?> source;

        switch (combined.getVal$encoder()) {
            case ComappedEncoderAccessor comapped -> {
                var func = (Function<Object, ?>) comapped.getVal$function();
                from = unknown -> DataResult.success(func.apply(unknown));

                if (!(comapped.getThis$0() instanceof Codec<?> src)) return null;

                source = src;
            }
            case FlatComappedEncoderAccessor flatComapped -> {
                from = flatComapped.getVal$function();

                if (!(flatComapped.getThis$0() instanceof Codec<?> src)) return null;

                source = src;
            }
            case null, default -> {
                return null;
            }
        }

        switch (combined.getVal$decoder()) {
            case MappedDecoderAccessor mapped -> {
                var func = (Function<Object, ?>) mapped.getVal$function();
                to = unknown -> DataResult.success(func.apply(unknown));

                if (mapped.getThis$0() != source) return null;
            }

            case FlatMappedDecoderAccessor flatMapped -> {
                to = flatMapped.getVal$function();

                if (flatMapped.getThis$0() != source) return null;
            }
            case null, default -> {
                return null;
            }
        }
        
        return new UnknownMappedCodecElement(codec, CodecIntrospection.introspect(source), to, from);
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "UnknownMappedCodecElement");
        source.dump(out, indent + 1);
    }
}
