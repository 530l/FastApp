package com.fastapp.ui.paintedskin;

import org.alee.component.skin.service.IOptionFactory;
import org.alee.component.skin.service.IThemeSkinOption;

public class OptionFactory implements IOptionFactory {
    @Override
    public int defaultTheme() {
        return 0;
    }

    @Override
    public IThemeSkinOption requireOption(int theme) {
        switch (theme) {
            case 1:
                return new NightOption();
            default:
                return null;
        }
    }
}
