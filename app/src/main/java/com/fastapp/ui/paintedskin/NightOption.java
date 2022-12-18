package com.fastapp.ui.paintedskin;

import org.alee.component.skin.service.IThemeSkinOption;

import java.util.LinkedHashSet;

//主题配置
class NightOption implements IThemeSkinOption {

    @Override
    public LinkedHashSet<String> getStandardSkinPackPath() {
        LinkedHashSet<String> pathSet = new LinkedHashSet<>();
        pathSet.add("/sdcard/night.skin");
        return pathSet;
    }
}
