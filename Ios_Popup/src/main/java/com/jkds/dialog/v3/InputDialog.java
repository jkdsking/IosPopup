package com.jkds.dialog.v3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jkds.dialog.R;
import com.jkds.dialog.interfaces.OnBackClickListener;
import com.jkds.dialog.interfaces.OnDialogButtonClickListener;
import com.jkds.dialog.interfaces.OnDismissListener;
import com.jkds.dialog.interfaces.OnInputDialogButtonClickListener;
import com.jkds.dialog.interfaces.OnShowListener;
import com.jkds.dialog.util.BaseDialog;
import com.jkds.dialog.util.DialogSettings;
import com.jkds.dialog.util.InputInfo;
import com.jkds.dialog.util.TextInfo;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Author: @Kongzue
 * Github: https://github.com/kongzue/
 * Homepage: http://kongzue.com/
 * Mail: myzcxhh@live.cn
 * CreateTime: 2019/4/12 16:42
 */
public class InputDialog extends MessageDialog {
    
    private String inputText = "";
    private String hintText;
    
    private OnInputDialogButtonClickListener onOkButtonClickListener;
    private OnInputDialogButtonClickListener onCancelButtonClickListener;
    private OnInputDialogButtonClickListener onOtherButtonClickListener;
    
    private InputDialog() {
    }
    
    public static InputDialog build(@NonNull AppCompatActivity context) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = new InputDialog();
            inputDialog.log("装载对话框: " + inputDialog.toString());
            inputDialog.context = new WeakReference<>(context);
            
            inputDialog.okButtonDrawable = DialogSettings.okButtonDrawable;
            inputDialog.cancelButtonDrawable = DialogSettings.cancelButtonDrawable;
            inputDialog.otherButtonDrawable = DialogSettings.otherButtonDrawable;
            
            switch (inputDialog.style) {
                case STYLE_IOS:
                    inputDialog.build(inputDialog, R.layout.dialog_select_ios);
                    break;

            }
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, String title, String message) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(context, title, message, null, null, null);
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, int titleResId, int messageResId) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(context,
                    context.getString(titleResId),
                    context.getString(messageResId),
                    null, null, null
            );
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, String title, String message, String okButton) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(context, title, message, okButton, null, null);
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, int titleResId, int messageResId, int okButtonResId) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(context,
                    context.getString(titleResId),
                    context.getString(messageResId),
                    context.getString(okButtonResId),
                    null, null
            );
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, String title, String message, String okButton, String cancelButton) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(context, title, message, okButton, cancelButton, null);
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, int titleResId, int messageResId, int okButtonResId, int cancelButtonResId) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(
                    context,
                    context.getString(titleResId),
                    context.getString(messageResId),
                    context.getString(okButtonResId),
                    context.getString(cancelButtonResId),
                    null
            );
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, String title, String message, String okButton, String cancelButton, String otherButton) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = build(context);
            
            inputDialog.title = title;
            if (okButton != null) {
                inputDialog.okButton = okButton;
            }
            inputDialog.message = message;
            inputDialog.cancelButton = cancelButton;
            inputDialog.otherButton = otherButton;
            
            inputDialog.showDialog();
            return inputDialog;
        }
    }
    
    public static InputDialog show(@NonNull AppCompatActivity context, int titleResId, int messageResId, int okButtonResId, int cancelButtonResId, int otherButtonResId) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = show(
                    context,
                    context.getString(titleResId),
                    context.getString(messageResId),
                    context.getString(okButtonResId),
                    context.getString(cancelButtonResId),
                    context.getString(otherButtonResId)
            );
            return inputDialog;
        }
    }
    
    private LinearLayout materialCustomViewBox;
    private IBinder windowToken;
    
    @Override
    public void refreshView() {
        super.refreshView();
//        if (style == DialogSettings.STYLE.STYLE_MATERIAL) {
//            if (materialAlertDialog != null) {
//                if (txtInput == null) {
//                    //初始化的情况
//                    txtInput = new EditText(context.get());
//
////                    if (bkg!=null) {
////                        bkg.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
////                            @Override
////                            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
////                                Rect r = new Rect();
////                                //获取当前界面可视部分
////                                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
////                                //获取屏幕的高度
////                                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
////                                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
////                                int heightDifference = screenHeight - r.bottom;
////
////                                if (heightDifference <= 0 && mLastDiff > 0) {
////                                    dismiss();
////                                }
////                                mLastDiff = heightDifference;
////                            }
////                        });
////
////                    }
//
//
//                    txtInput.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) txtInput.getLayoutParams();
//                            p.setMargins(dip2px(20), 0, dip2px(20), 0);
//                            txtInput.requestLayout();
//                        }
//                    });
//                    txtInput.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (txtInput != null) {
//                                if (DialogSettings.autoShowInputKeyboard && txtInput.getVisibility() == View.VISIBLE) {
//                                    txtInput.setFocusable(true);
//                                    txtInput.setFocusableInTouchMode(true);
//                                    txtInput.requestFocus();
//                                    windowToken = txtInput.getWindowToken();
//                                    InputMethodManager imm = (InputMethodManager) context.get().getSystemService(Context.INPUT_METHOD_SERVICE);
//                                    imm.showSoftInput(txtInput, InputMethodManager.SHOW_IMPLICIT);
//                                }
//                            }
//                        }
//                    }, 100);
//
//                    if (buttonTextInfo != null) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            txtInput.setBackgroundTintList(ColorStateList.valueOf(buttonTextInfo.getFontColor()));
//                        }
//                    }
//                    if (customView == null) {
//                        materialAlertDialog.setView(txtInput);
//                    } else {
//                        if (boxCustom != null) {
//                            boxCustom.removeAllViews();
//                        }
//                        if (materialCustomViewBox != null) {
//                            materialCustomViewBox.removeAllViews();
//                        }
//                        materialCustomViewBox = new LinearLayout(context.get());
//                        materialCustomViewBox.setOrientation(LinearLayout.VERTICAL);
//                        materialCustomViewBox.addView(customView);
//                        materialCustomViewBox.addView(txtInput);
//
//                        if (onBindView != null) {
//                            onBindView.onBind(this, materialCustomViewBox);
//                        }
//
//                        materialAlertDialog.setView(materialCustomViewBox);
//                    }
//                    materialAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                        @Override
//                        public void onShow(DialogInterface dialog) {
//                            Button positiveButton = materialAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
//                            positiveButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    hideInputKeyboard();
//                                    if (onOkButtonClickListener != null) {
//                                        if (!onOkButtonClickListener.onClick(InputDialog.this, v, getInputText())) {
//                                            materialAlertDialog.dismiss();
//                                        }
//                                    } else {
//                                        materialAlertDialog.dismiss();
//                                    }
//                                }
//                            });
//                            useTextInfo(positiveButton, buttonPositiveTextInfo);
//
//                            Button negativeButton = materialAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//                            negativeButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    hideInputKeyboard();
//                                    if (onCancelButtonClickListener != null) {
//                                        if (!onCancelButtonClickListener.onClick(InputDialog.this, v, getInputText())) {
//                                            materialAlertDialog.dismiss();
//                                        }
//                                    } else {
//                                        materialAlertDialog.dismiss();
//                                    }
//                                }
//                            });
//                            useTextInfo(negativeButton, buttonTextInfo);
//
//                            if (otherButton != null) {
//                                Button otherButton = materialAlertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
//                                otherButton.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        hideInputKeyboard();
//                                        if (onOtherButtonClickListener != null) {
//                                            if (!onOtherButtonClickListener.onClick(InputDialog.this, v, getInputText())) {
//                                                materialAlertDialog.dismiss();
//                                            }
//                                        } else {
//                                            materialAlertDialog.dismiss();
//                                        }
//                                    }
//                                });
//                                useTextInfo(otherButton, buttonTextInfo);
//                            }
//                            try {
//                                Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
//                                mAlert.setAccessible(true);
//                                Object mAlertController = mAlert.get(dialog);
//
//                                if (titleTextInfo != null) {
//                                    Field mTitle = mAlertController.getClass().getDeclaredField("mTitleView");
//                                    mTitle.setAccessible(true);
//                                    TextView titleTextView = (TextView) mTitle.get(mAlertController);
//                                    useTextInfo(titleTextView, titleTextInfo);
//                                }
//
//                                if (messageTextInfo != null) {
//                                    Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
//                                    mMessage.setAccessible(true);
//                                    TextView messageTextView = (TextView) mMessage.get(mAlertController);
//                                    useTextInfo(messageTextView, messageTextInfo);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//                }
//            }
//        }
//        else {
            if (boxInput != null) {
                boxInput.setMaxHeight(dip2px(100));
            }
            if (btnSelectPositive != null) {
                btnSelectPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideInputKeyboard();
                        if (onOkButtonClickListener != null) {
                            if (!onOkButtonClickListener.onClick(InputDialog.this, v, getInputText())) {
                                doDismiss();
                            }
                        } else {
                            doDismiss();
                        }
                    }
                });
            }
            if (btnSelectNegative != null) {
                btnSelectNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideInputKeyboard();
                        if (onCancelButtonClickListener != null) {
                            if (!onCancelButtonClickListener.onClick(InputDialog.this, v, getInputText())) {
                                doDismiss();
                            }
                        } else {
                            doDismiss();
                        }
                    }
                });
            }
            if (btnSelectOther != null) {
                btnSelectOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideInputKeyboard();
                        if (onOtherButtonClickListener != null) {
                            if (!onOtherButtonClickListener.onClick(InputDialog.this, v, getInputText())) {
                                doDismiss();
                            }
                        } else {
                            doDismiss();
                        }
                    }
                });
            }
            if (txtInput != null) {
                txtInput.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (txtInput != null) {
                            if (DialogSettings.autoShowInputKeyboard && txtInput.getVisibility() == View.VISIBLE) {
                                txtInput.setFocusable(true);
                                txtInput.setFocusableInTouchMode(true);
                                txtInput.requestFocus();
                                windowToken = txtInput.getWindowToken();
                                InputMethodManager imm = (InputMethodManager) context.get().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(txtInput, InputMethodManager.SHOW_IMPLICIT);
                            }
                        }
                    }
                }, 100);
            }
//        }
        refreshTextViews();

    }
    
    @Override
    protected void refreshTextViews() {
        log(txtInput == null);
        super.refreshTextViews();
        if (txtInput != null) {
            txtInput.setText(inputText);
            txtInput.setSelection(inputText.length());
            txtInput.setVisibility(View.VISIBLE);
            
            if (theme == DialogSettings.THEME.DARK) {
                txtInput.setTextColor(Color.WHITE);
                txtInput.setHintTextColor(context.get().getResources().getColor(R.color.whiteAlpha30));
            }
            txtInput.setHint(hintText);
            if (inputInfo != null) {
                if (inputInfo.getMAX_LENGTH() != -1) {
                    txtInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(inputInfo.getMAX_LENGTH())});
                }
                int inputType = InputType.TYPE_CLASS_TEXT | inputInfo.getInputType();
                if (inputInfo.isMultipleLines()) {
                    inputType = inputType | InputType.TYPE_TEXT_FLAG_MULTI_LINE;
                }
                txtInput.setInputType(inputType);
                if (inputInfo.getTextInfo() != null) {
                    useTextInfo(txtInput, inputInfo.getTextInfo());
                }
                
                if (inputInfo.isSelectAllText()) {
                    txtInput.post(new Runnable() {
                        @Override
                        public void run() {
                            txtInput.selectAll();
                        }
                    });
                }
            }
        }
    }
    
    public void hideInputKeyboard() {
        if (windowToken != null) {
            InputMethodManager imm = (InputMethodManager) context.get().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }
    
    public String getInputText() {
        if (txtInput == null) {
            return inputText;
        } else {
            return txtInput.getText().toString();
        }
    }
    
    //其他设置
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public InputDialog setTitle(String title) {
        this.title = title;
        return this;
    }
    
    @Override
    public InputDialog setTitle(int titleResId) {
        this.title = context.get().getString(titleResId);
        return this;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public InputDialog setMessage(String content) {
        this.message = content;
        return this;
    }
    
    @Override
    public InputDialog setMessage(int contentResId) {
        this.message = context.get().getString(contentResId);
        return this;
    }
    
    @Override
    public String getOkButton() {
        return okButton;
    }
    
    @Override
    public InputDialog setOkButton(String okButton) {
        this.okButton = okButton;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setOkButton(int okButtonResId) {
        setOkButton(context.get().getString(okButtonResId));
        return this;
    }
    
    public InputDialog setOkButton(String okButton, OnInputDialogButtonClickListener
            onOkButtonClickListener) {
        this.okButton = okButton;
        this.onOkButtonClickListener = onOkButtonClickListener;
        refreshView();
        return this;
    }
    
    public InputDialog setOkButton(int okButtonResId, OnInputDialogButtonClickListener
            onOkButtonClickListener) {
        setOkButton(context.get().getString(okButtonResId), onOkButtonClickListener);
        return this;
    }
    
    public InputDialog setOkButton(OnInputDialogButtonClickListener onOkButtonClickListener) {
        this.onOkButtonClickListener = onOkButtonClickListener;
        refreshView();
        return this;
    }
    
    @Override
    public String getCancelButton() {
        return cancelButton;
    }
    
    @Override
    public InputDialog setCancelButton(String cancelButton) {
        this.cancelButton = cancelButton;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setCancelButton(int cancelButtonResId) {
        setCancelButton(context.get().getString(cancelButtonResId));
        return this;
    }
    
    public InputDialog setCancelButton(String cancelButton, OnInputDialogButtonClickListener
            onCancelButtonClickListener) {
        this.cancelButton = cancelButton;
        this.onCancelButtonClickListener = onCancelButtonClickListener;
        refreshView();
        return this;
    }
    
    public InputDialog setCancelButton(int cancelButtonResId, OnInputDialogButtonClickListener
            onCancelButtonClickListener) {
        setCancelButton(context.get().getString(cancelButtonResId), onCancelButtonClickListener);
        return this;
    }
    
    public InputDialog setCancelButton(OnInputDialogButtonClickListener
                                               onCancelButtonClickListener) {
        this.onCancelButtonClickListener = onCancelButtonClickListener;
        refreshView();
        return this;
    }
    
    @Override
    public String getOtherButton() {
        return otherButton;
    }
    
    @Override
    public InputDialog setOtherButton(String otherButton) {
        this.otherButton = otherButton;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setOtherButton(int otherButtonResId) {
        setCancelButton(context.get().getString(otherButtonResId));
        refreshView();
        return this;
    }
    
    public InputDialog setOtherButton(String otherButton, OnInputDialogButtonClickListener
            onOtherButtonClickListener) {
        this.otherButton = otherButton;
        this.onOtherButtonClickListener = onOtherButtonClickListener;
        refreshView();
        return this;
    }
    
    public InputDialog setOtherButton(int otherButtonResId, OnInputDialogButtonClickListener
            onOtherButtonClickListener) {
        setOtherButton(context.get().getString(otherButtonResId), onOtherButtonClickListener);
        return this;
    }
    
    public InputDialog setOtherButton(OnInputDialogButtonClickListener
                                              onOtherButtonClickListener) {
        this.onOtherButtonClickListener = onOtherButtonClickListener;
        refreshView();
        return this;
    }
    
    public OnInputDialogButtonClickListener getOnInputOkButtonClickListener() {
        return onOkButtonClickListener;
    }
    
    @Override
    @Deprecated
    public OnDialogButtonClickListener getOnOkButtonClickListener() {
        error("请使用 getOnInputOkButtonClickListener() 获取 onOkButtonClickListener");
        return null;
    }
    
    public InputDialog setOnOkButtonClickListener(OnInputDialogButtonClickListener
                                                          onOkButtonClickListener) {
        this.onOkButtonClickListener = onOkButtonClickListener;
        refreshView();
        return this;
    }
    
    public OnInputDialogButtonClickListener getOnInputCancelButtonClickListener() {
        return onCancelButtonClickListener;
    }
    
    @Override
    @Deprecated
    public OnDialogButtonClickListener getOnCancelButtonClickListener() {
        error("请使用 getOnInputCancelButtonClickListener() 获取 onCancelButtonClickListener");
        return null;
    }
    
    public InputDialog setOnCancelButtonClickListener(OnInputDialogButtonClickListener
                                                              onCancelButtonClickListener) {
        this.onCancelButtonClickListener = onCancelButtonClickListener;
        refreshView();
        return this;
    }
    
    public OnInputDialogButtonClickListener getOnInputOtherButtonClickListener() {
        return onOtherButtonClickListener;
    }
    
    @Override
    @Deprecated
    public OnDialogButtonClickListener getOnOtherButtonClickListener() {
        error("请使用 getOnInputOtherButtonClickListener() 获取 onOtherButtonClickListener");
        return null;
    }
    
    public InputDialog setOnOtherButtonClickListener(OnInputDialogButtonClickListener
                                                             onOtherButtonClickListener) {
        this.onOtherButtonClickListener = onOtherButtonClickListener;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setOkButtonDrawable(@DrawableRes int okButtonDrawableResId) {
        this.okButtonDrawable = ContextCompat.getDrawable(context.get(), okButtonDrawableResId);
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setOkButtonDrawable(Drawable okButtonDrawable) {
        this.okButtonDrawable = okButtonDrawable;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setCancelButtonDrawable(@DrawableRes int okButtonDrawableResId) {
        this.cancelButtonDrawable = ContextCompat.getDrawable(context.get(), okButtonDrawableResId);
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setCancelButtonDrawable(Drawable cancelButtonDrawable) {
        this.cancelButtonDrawable = cancelButtonDrawable;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setOtherButtonDrawable(@DrawableRes int okButtonDrawableResId) {
        this.otherButtonDrawable = ContextCompat.getDrawable(context.get(), okButtonDrawableResId);
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setOtherButtonDrawable(Drawable otherButtonDrawable) {
        this.otherButtonDrawable = otherButtonDrawable;
        refreshView();
        return this;
    }
    
    @Override
    public int getButtonOrientation() {
        return buttonOrientation;
    }
    
    @Override
    public InputDialog setButtonOrientation(int buttonOrientation) {
        this.buttonOrientation = buttonOrientation;
        refreshView();
        return this;
    }
    
    //其他
    @Override
    public OnDismissListener getOnDismissListener() {
        return onDismissListener == null ? new OnDismissListener() {
            @Override
            public void onDismiss() {
            
            }
        } : onDismissListener;
    }
    
    @Override
    public InputDialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }
    
    @Override
    public OnShowListener getOnShowListener() {
        return onShowListener == null ? new OnShowListener() {
            @Override
            public void onShow(BaseDialog dialog) {
            
            }
        } : onShowListener;
    }
    
    @Override
    public InputDialog setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
        return this;
    }
    
    @Override
    public DialogSettings.STYLE getStyle() {
        return style;
    }
    
    @Override
    public InputDialog setStyle(DialogSettings.STYLE style) {
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setStyle(...) 来修改对话框主题或风格。");
            return this;
        }
        
        this.style = style;
        switch (this.style) {
            case STYLE_IOS:
                build(this, R.layout.dialog_select_ios);
                break;

        }
        
        return this;
    }
    
    @Override
    public DialogSettings.THEME getTheme() {
        return theme;
    }
    
    @Override
    public InputDialog setTheme(DialogSettings.THEME theme) {
        
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setTheme(...) 来修改对话框主题或风格。");
            return this;
        }
        
        this.theme = theme;
        refreshView();
        return this;
    }
    
    @Override
    public boolean getCancelable() {
        return cancelable == BOOLEAN.TRUE;
    }
    
    @Override
    public InputDialog setCancelable(boolean enable) {
        this.cancelable = enable ? BOOLEAN.TRUE : BOOLEAN.FALSE;
        if (dialog != null) {
            dialog.get().setCancelable(cancelable == BOOLEAN.TRUE);
        }
        return this;
    }
    
    
    @Override
    public TextInfo getTitleTextInfo() {
        return titleTextInfo;
    }
    
    @Override
    public InputDialog setTitleTextInfo(TextInfo titleTextInfo) {
        this.titleTextInfo = titleTextInfo;
        refreshView();
        return this;
    }
    
    @Override
    public TextInfo getMessageTextInfo() {
        return messageTextInfo;
    }
    
    @Override
    public InputDialog setMessageTextInfo(TextInfo messageTextInfo) {
        this.messageTextInfo = messageTextInfo;
        refreshView();
        return this;
    }
    
    @Override
    public TextInfo getButtonTextInfo() {
        return buttonTextInfo;
    }
    
    @Override
    public InputDialog setButtonTextInfo(TextInfo buttonTextInfo) {
        this.buttonTextInfo = buttonTextInfo;
        refreshView();
        return this;
    }
    
    @Override
    public TextInfo getButtonPositiveTextInfo() {
        return buttonPositiveTextInfo;
    }
    
    @Override
    public InputDialog setButtonPositiveTextInfo(TextInfo buttonPositiveTextInfo) {
        this.buttonPositiveTextInfo = buttonPositiveTextInfo;
        refreshView();
        return this;
    }
    
    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }
    
    @Override
    public InputDialog setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        refreshView();
        return this;
    }
    
    public InputDialog setInputText(String inputText) {
        this.inputText = inputText;
        refreshView();
        return this;
    }
    
    public InputDialog setInputText(int inputTextResId) {
        this.inputText = context.get().getString(inputTextResId);
        refreshView();
        return this;
    }
    
    public String getHintText() {
        return hintText;
    }
    
    public InputDialog setHintText(String hintText) {
        this.hintText = hintText;
        refreshView();
        return this;
    }
    
    public InputDialog setHintText(int hintTextResId) {
        this.hintText = context.get().getString(hintTextResId);
        refreshView();
        return this;
    }
    
    public InputInfo getInputInfo() {
        return inputInfo;
    }
    
    public InputDialog setInputInfo(InputInfo inputInfo) {
        this.inputInfo = inputInfo;
        refreshView();
        return this;
    }
    
    @Override
    public View getCustomView() {
        return customView;
    }
    
    @Override
    public InputDialog setCustomView(View customView) {
        this.customView = customView;
        refreshView();
        return this;
    }
    
    private OnBindView onBindView;
    
    public InputDialog setCustomView(int customViewLayoutId, OnBindView onBindView) {
        customView = LayoutInflater.from(context.get()).inflate(customViewLayoutId, null);
        this.onBindView = onBindView;
        refreshView();
        return this;
    }
    
    public interface OnBindView {
        void onBind(InputDialog dialog, View v);
    }
    
    public EditText getEditTextView() {
        return txtInput;
    }
    
    @Override
    public int getBackgroundResId() {
        return backgroundResId;
    }
    
    @Override
    public InputDialog setBackgroundResId(int backgroundResId) {
        this.backgroundResId = backgroundResId;
        refreshView();
        return this;
    }
    
    @Override
    public InputDialog setCustomDialogStyleId(int customDialogStyleId) {
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setTheme(...) 来修改对话框主题或风格。");
            return this;
        }
        this.customDialogStyleId = customDialogStyleId;
        return this;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    }
    
    @Override
    public OnBackClickListener getOnBackClickListener() {
        return onBackClickListener;
    }
    
    @Override
    public InputDialog setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
        return this;
    }
    
    @Override
    public ALIGN getAlign() {
        return align;
    }
    
    @Override
    public InputDialog setAlign(ALIGN align) {
        this.align = align;
        return this;
    }
}