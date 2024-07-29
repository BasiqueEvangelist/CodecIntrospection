package me.basiqueevangelist.codecintrospection;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.element.*;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.codec.UnknownCodecElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.UnknownMapCodecElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.RecursiveCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.WithLifecycleCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.AssumeMapUnsafeMapCodecAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class CodecIntrospection {
    private static final Map<Codec<?>, IntrospectionElement> CODEC_TO_ELEMENT = new IdentityHashMap<>();
    private static final Map<MapCodec<?>, IntrospectionElement> MAP_CODEC_TO_ELEMENT = new IdentityHashMap<>();

    private static final List<Function<Codec<?>, @Nullable IntrospectionElement>> CODEC_CONVERTERS = new ArrayList<>();
    private static final List<Function<MapCodec<?>, @Nullable IntrospectionElement>> MAP_CODEC_CONVERTERS = new ArrayList<>();

    private CodecIntrospection() {

    }

    public static void registerStatic(CodecIntrospectionElement element) {
        CODEC_TO_ELEMENT.put(element.original(), element);
    }

    public static void registerStatic(MapCodecIntrospectionElement element) {
        MAP_CODEC_TO_ELEMENT.put(element.original(), element);
    }

    public static void registerCodecConverter(Function<Codec<?>, @Nullable IntrospectionElement> converter) {
        CODEC_CONVERTERS.add(converter);
    }

    public static void registerMapCodecConverter(Function<MapCodec<?>, @Nullable IntrospectionElement> converter) {
        MAP_CODEC_CONVERTERS.add(converter);
    }

    public static IntrospectionElement introspect(Codec<?> codec) {
        var staticElement = CODEC_TO_ELEMENT.get(codec);

        if (staticElement != null) return staticElement;

        if (codec instanceof WithLifecycleCodecAccessor withLifecycle)
            return introspect(withLifecycle.getThis$0());

        if (codec instanceof RecursiveCodecAccessor recursive)
            return introspect(recursive.getWrapped().get());

        if (codec instanceof MapCodec.MapCodecCodec<?> mapCodecCodec)
            return introspect(mapCodecCodec.codec());

        for (var converter : CODEC_CONVERTERS) {
            var res = converter.apply(codec);
            if (res != null) return res;
        }

        return new UnknownCodecElement(codec);
    }

    public static IntrospectionElement introspect(MapCodec<?> codec) {
        var staticElement = MAP_CODEC_TO_ELEMENT.get(codec);

        if (staticElement != null) return staticElement;

        if (codec instanceof AssumeMapUnsafeMapCodecAccessor assumeMapUnsafe)
            return introspect(assumeMapUnsafe.getVal$codec());

        for (var converter : MAP_CODEC_CONVERTERS) {
            var res = converter.apply(codec);
            if (res != null) return res;
        }

        return new UnknownMapCodecElement(codec);
    }

    static {
        IntrospectionElements.init();
    }
}
