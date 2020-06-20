package com.diabin.fastec.latte_ec.icon;



import com.joanzapata.iconify.Icon;



public enum  EcIcons implements Icon {

    icon_scan('\ue606'),
    icon_ali_pay('\ue606');
    private char character;

    EcIcons(char character){
        this.character= character;
    }
    @Override
    public String key() {
        return name().replace('-','-');
    }

    @Override
    public char character() {
        return character;
    }



}
