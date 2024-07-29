package me.basiqueevangelist.codecintrospection.mixin.codec.minecraft;

import me.basiqueevangelist.codecintrospection.pond.StringRepresentableCodecAccess;
import net.minecraft.util.StringRepresentable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@Mixin(StringRepresentable.StringRepresentableCodec.class)
public class StringRepresentableCodecMixin implements StringRepresentableCodecAccess {
    @Unique private StringRepresentable[] codecintrospection$values;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void storeElements(StringRepresentable[] values, Function<String, StringRepresentable> nameLookup, ToIntFunction<StringRepresentable> indexLookup, CallbackInfo ci) {
        codecintrospection$values = values;
    }

    @Override
    public StringRepresentable[] codecintrospection$values() {
        return codecintrospection$values;
    }
}
