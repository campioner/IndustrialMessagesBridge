package com.example.administrator.industrialmessagesbridge.myView;

import android.content.Context;
import android.util.AttributeSet;

import jp.wasabeef.richeditor.RichEditor;

public class CustomRichEditor extends RichEditor {
    public CustomRichEditor(Context context) {
        super(context);
    }

    public CustomRichEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRichEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNewLine() {
        exec("javascript:RE.prepareInsert();");
        exec("javascript:RE.insertHTML('<br></br>');");
    }
}
