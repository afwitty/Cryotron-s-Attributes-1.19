package dev.cryotron.attributes.util;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.renderer.RenderType;

public class RenderingUtils {
    public static void draw(VertexFormat.Mode drawMode, VertexFormat format, Consumer<BufferBuilder> fn) {
        draw(drawMode, format, bufferBuilder -> {
            fn.accept(bufferBuilder);
            return null;
        });
    }

    public static <R> R draw(VertexFormat.Mode drawMode, VertexFormat format, Function<BufferBuilder, R> fn) {
        BufferBuilder buf = Tesselator.getInstance().getBuilder();
        buf.begin(drawMode, format);
        R result = fn.apply(buf);
        finishDrawing(buf);
        return result;
    }
    
    public static void finishDrawing(BufferBuilder buf) {
        finishDrawing(buf, null);
    }
    
    public static void finishDrawing(BufferBuilder buf, @Nullable RenderType type) {
        if (buf.building()) {
            if (type != null) {
                type.end(buf, 0, 0, 0);
            } else {
                buf.end();
                //BufferUploader.drawWithShader(buf);   ERROR: Parameter needs to be a BufferBuilder.RenderBuffer!
            }
        }
    }
}
