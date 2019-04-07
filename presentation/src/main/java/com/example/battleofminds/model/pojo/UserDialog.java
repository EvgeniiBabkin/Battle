package com.example.battleofminds.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDialog {

    @SerializedName("dialog_name")
    @Expose
    private String dialogName;

    public String getDialogName() {
        return dialogName;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }
}
