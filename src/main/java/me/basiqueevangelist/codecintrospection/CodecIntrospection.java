package me.basiqueevangelist.codecintrospection;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.element.*;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.codec.UnknownCodecElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.RecursiveCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.WithLifecycleCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.minecraft.EncoderCacheCodecAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class CodecIntrospection {
    private static final Map<Codec<?>, IntrospectionElement> STATIC_ELEMENTS = new IdentityHashMap<>();

    private static final List<Function<Codec<?>, @Nullable IntrospectionElement>> CONVERTERS = new ArrayList<>();

    private CodecIntrospection() {

    }

    public static void registerStatic(CodecIntrospectionElement element) {
        STATIC_ELEMENTS.put(element.original(), element);
    }

    public static void registerConverter(Function<Codec<?>, @Nullable IntrospectionElement> converter) {
        CONVERTERS.add(converter);
    }

    public static IntrospectionElement introspect(Codec<?> codec) {
        var staticElement = STATIC_ELEMENTS.get(codec);

        if (staticElement != null) return staticElement;

        if (codec instanceof WithLifecycleCodecAccessor withLifecycle)
            return introspect(withLifecycle.getThis$0());

        if (codec instanceof RecursiveCodecAccessor recursive)
            return introspect(recursive.getWrapped().get());

        if (codec instanceof MapCodec.MapCodecCodec<?> mapCodecCodec)
            return MapCodecIntrospection.introspect(mapCodecCodec.codec());

        if (codec instanceof EncoderCacheCodecAccessor encoderCacheCodec)
            return introspect(encoderCacheCodec.getVal$codec());

        for (var converter : CONVERTERS) {
            var res = converter.apply(codec);
            if (res != null) return res;
        }

        return new UnknownCodecElement(codec);
    }

    static {
        IntrospectionElements.init();
    }
}
