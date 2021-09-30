package com.jclian.hmos.avatarfor101.slice;

import com.jclian.hmos.avatarfor101.ResourceTable;
import com.jclian.hmos.avatarfor101.utils.PixelMapUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.render.*;
import ohos.agp.utils.Color;
import ohos.agp.utils.Point;
import ohos.agp.utils.RectFloat;
import ohos.media.image.PixelMap;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        Image image = (ohos.agp.components.Image) findComponentById(ResourceTable.Id_img_avatar);
        image.addDrawTask(new Component.DrawTask() {
            Paint paintImage = null;

            @Override
            public void onDraw(Component component, Canvas canvas) {
                if (paintImage == null) {
                    paintImage = new Paint();
                    paintImage.setAntiAlias(true);
                    paintImage.setStrokeCap(Paint.StrokeCap.ROUND_CAP);
                    paintImage.setStyle(Paint.Style.STROKE_STYLE);
                }
                PixelMap pixelMaps = PixelMapUtils.createPixelMapByResId(ResourceTable.Media_cn_starts, getContext()).get();
                int imageWidth = pixelMaps.getImageInfo().size.width;
                int imageHeight = pixelMaps.getImageInfo().size.height;
                RectFloat rect = new RectFloat(0f, 0f, component.getWidth(), component.getHeight());
                Paint paint = new Paint();
                paint.setGradientShaderColor(new Color[]{Color.RED, Color.TRANSPARENT});
                LinearShader linearShader = new LinearShader(
                        new Point[]{new Point(0, 0), new Point(component.getWidth(), component.getHeight())},
                        new float[]{0f, 0.8f},
                        new Color[]{Color.RED, Color.TRANSPARENT}, Shader.TileMode.CLAMP_TILEMODE
                );
                paint.setShader(linearShader, Paint.ShaderType.LINEAR_SHADER);
                canvas.drawRect(rect, paint);
                // 指定图片在屏幕上显示的区域
                RectFloat dst = new RectFloat(0, 0,
                        component.getWidth() * 2 / 3, component.getWidth() * 2 / 3 * imageHeight / imageWidth);
                canvas.drawPixelMapHolderRect(new PixelMapHolder(pixelMaps), dst, paintImage);
            }
        });

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}