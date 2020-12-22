package com.dili.uid.glossary;

/**
 * <B>Description</B>
 * 本软件源代码版权归农丰时代及其团队所有,未经许可不得任意复制与传播
 * <B>农丰时代科技有限公司</B>
 *
 * @author wm
 * @createTime 2020/2/5 18:05
 */
public enum EnabledStateEnum {

    ENABLED(true, "启用"),
    DISABLED(false, "禁用")
    ;

    private String name;
    private Boolean code ;

    EnabledStateEnum(Boolean code, String name){
        this.code = code;
        this.name = name;
    }

    public static EnabledStateEnum getEnabledState(Integer code) {
        for (EnabledStateEnum anEnum : EnabledStateEnum.values()) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return null;
    }

    public Boolean getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
