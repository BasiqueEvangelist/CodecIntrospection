package me.basiqueevangelist.codecintrospection.mixin.codec.dfu.xmap;

import com.mojang.serialization.Encoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(targets = "com/mojang/serialization/Encoder$1", remap = false)
public interface ComappedEncoderAccessor {
    @Accessor
    Function<?, ?> getVal$function();

    @Accessor
    Encoder<?> getThis$0();
}
