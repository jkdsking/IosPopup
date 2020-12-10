package com.jkds.dialog.v3;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.jkds.dialog.R;
import com.jkds.dialog.interfaces.OnBackClickListener;
import com.jkds.dialog.interfaces.OnDismissListener;
import com.jkds.dialog.interfaces.OnShowListener;
import com.jkds.dialog.util.BaseDialog;
import java.lang.ref.WeakReference;

public class CustomDialog extends BaseDialog {
    private boolean fullScreen = false;
    private OnBindView onBindView;

    private CustomDialog() {
        log("装载自定义对话框: " + toString());
    }

    public static CustomDialog show(AppCompatActivity context, int layoutResId, OnBindView onBindView) {
        synchronized (CustomDialog.class) {
            CustomDialog customDialog = new CustomDialog();
            customDialog.context = new WeakReference<>(context);
            customDialog.onBindView = onBindView;
            customDialog.customView = LayoutInflater.from(context).inflate(layoutResId, null);
            customDialog.build(customDialog, layoutResId);
            customDialog.show();
            return customDialog;
        }
    }

    public static CustomDialog show(AppCompatActivity context, View customView, OnBindView onBindView) {
        synchronized (CustomDialog.class) {
            CustomDialog customDialog = new CustomDialog();
            customDialog.context = new WeakReference<>(context);
            customDialog.onBindView = onBindView;
            customDialog.customView = customView;
            customDialog.build(customDialog, R.layout.dialog_custom);
            customDialog.show();
            return customDialog;
        }
    }

    public static CustomDialog show(AppCompatActivity context, View customView) {
        synchronized (CustomDialog.class) {
            CustomDialog customDialog = new CustomDialog();
            customDialog.context = new WeakReference<>(context);
            customDialog.customView = customView;
            customDialog.build(customDialog, R.layout.dialog_custom);
            customDialog.show();
            return customDialog;
        }
    }

    public static CustomDialog build(AppCompatActivity context, int layoutResId, OnBindView onBindView) {
        synchronized (CustomDialog.class) {
            CustomDialog customDialog = new CustomDialog();
            customDialog.context = new WeakReference<>(context);
            customDialog.onBindView = onBindView;
            customDialog.customView = LayoutInflater.from(context).inflate(layoutResId, null);
            customDialog.build(customDialog, layoutResId);
            return customDialog;
        }
    }

    public static CustomDialog build(AppCompatActivity context, View customView, OnBindView onBindView) {
        synchronized (CustomDialog.class) {
            CustomDialog customDialog = new CustomDialog();
            customDialog.context = new WeakReference<>(context);
            customDialog.onBindView = onBindView;
            customDialog.customView = customView;
            customDialog.build(customDialog, R.layout.dialog_custom);
            return customDialog;
        }
    }

    public static CustomDialog build(AppCompatActivity context, View customView) {
        synchronized (CustomDialog.class) {
            CustomDialog customDialog = new CustomDialog();
            customDialog.context = new WeakReference<>(context);
            customDialog.customView = customView;
            customDialog.build(customDialog, R.layout.dialog_custom);
            return customDialog;
        }
    }

    private RelativeLayout boxCustom;

    @Override
    public void bindView(View rootView) {
        log("启动自定义对话框 -> " + toString());
        if (boxCustom != null) boxCustom.removeAllViews();
        boxCustom = rootView.findViewById(R.id.box_custom);
        if (boxCustom == null) {
            if (onBindView != null) onBindView.onBind(this, rootView);
        } else {
            boxCustom.removeAllViews();
            RelativeLayout.LayoutParams lp = customLayoutParams != null ? customLayoutParams : new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            boxCustom.addView(customView, lp);
            if (onBindView != null) onBindView.onBind(this, customView);
        }

        if (onShowListener != null) onShowListener.onShow(this);
    }

    private RelativeLayout.LayoutParams customLayoutParams;

    @Override
    public void refreshView() {

    }

    @Override
    public void show() {
        showDialog();
    }

    public void show(int styleResId) {
        showDialog(styleResId);
    }

    public interface OnBindView {
        void onBind(CustomDialog dialog, View v);
    }

    public OnDismissListener getOnDismissListener() {
        return onDismissListener == null ? new OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        } : onDismissListener;
    }

    public CustomDialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public OnShowListener getOnShowListener() {
        return onShowListener == null ? new OnShowListener() {
            @Override
            public void onShow(BaseDialog dialog) {

            }
        } : onShowListener;
    }

    public CustomDialog setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
        return this;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public CustomDialog setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
        return this;
    }

    public boolean getCancelable() {
        return cancelable == BOOLEAN.TRUE;
    }

    public CustomDialog setCancelable(boolean enable) {
        this.cancelable = enable ? BOOLEAN.TRUE : BOOLEAN.FALSE;
        if (dialog != null) dialog.get().setCancelable(cancelable == BOOLEAN.TRUE);
        return this;
    }

    public CustomDialog setAlign(ALIGN align) {
        this.align = align;
        switch (align) {
            case BOTTOM:
                customDialogStyleId = R.style.BottomDialog;
                break;
            case TOP:
                customDialogStyleId = R.style.TopDialog;
                break;
        }
        return this;
    }

    public ALIGN getAlign() {
        return align;
    }

    public CustomDialog setCustomDialogStyleId(int customDialogStyleId) {
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setTheme(...) 来修改对话框主题或风格。");
            return this;
        }
        this.customDialogStyleId = customDialogStyleId;
        return this;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    }

    public OnBackClickListener getOnBackClickListener() {
        return onBackClickListener;
    }

    public CustomDialog setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
        return this;
    }

    public RelativeLayout.LayoutParams getCustomLayoutParams() {
        return customLayoutParams;
    }

    public CustomDialog setCustomLayoutParams(RelativeLayout.LayoutParams customLayoutParams) {
        this.customLayoutParams = customLayoutParams;
        return this;
    }

}
