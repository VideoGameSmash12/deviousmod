package me.allink.deviousmod.mixin.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    @Shadow
    @Nullable
    protected abstract PlayerListEntry getPlayerListEntry();

    @Inject(method = "getCapeTexture()Lnet/minecraft/util/Identifier;", at = @At("RETURN"), cancellable = true)

    public void getCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        assert MinecraftClient.getInstance().player != null;
        if (Objects.equals(getPlayerListEntry().getProfile().getName(), "G6_")) {
            cir.setReturnValue(new Identifier("deviousmod", "cape.png"));
        }
    }
}
