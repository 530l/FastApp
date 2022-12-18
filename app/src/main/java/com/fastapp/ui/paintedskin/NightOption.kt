package com.fastapp.ui.paintedskin

import com.fastapp.config.NIGHT_SKIN_PACK_NAME
import com.fastapp.config.SKIN_PACK_PATH
import com.fastapp.config.SPRING_FESTIVAL_SKIN_PACK_NAME
import com.fastapp.config.USE_SPRING_FESTIVAL_SKIN
import org.alee.component.skin.service.IThemeSkinOption

/**
 * 夜色模式配置
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal class NightOption : IThemeSkinOption {

    override fun getStandardSkinPackPath(): LinkedHashSet<String> {
        return LinkedHashSet<String>().apply {
            // 基础黑夜皮肤包
            add(SKIN_PACK_PATH + NIGHT_SKIN_PACK_NAME)
        }.apply {
            // 春节皮肤包
//            if (USE_SPRING_FESTIVAL_SKIN.loadBoolean(false)) {
//                add(SKIN_PACK_PATH + SPRING_FESTIVAL_SKIN_PACK_NAME)
//            }
        }
    }
}