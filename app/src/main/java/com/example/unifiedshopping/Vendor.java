package com.example.unifiedshopping;

import android.widget.Switch;
import android.widget.ToggleButton;

public class Vendor {
    String name;
    Switch aSwitch;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    Boolean state = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Switch getSwitch() {
        return aSwitch;
    }

    public void setToggle(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }

    public Vendor(String name, Switch aSwitch) {
        this.name = name;
        this.aSwitch = aSwitch;
    }
}
