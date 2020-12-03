package com.jkds.iospopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jkds.dialog.interfaces.OnDialogButtonClickListener;
import com.jkds.dialog.interfaces.OnDismissListener;
import com.jkds.dialog.interfaces.OnInputDialogButtonClickListener;
import com.jkds.dialog.interfaces.OnMenuItemClickListener;
import com.jkds.dialog.util.BaseDialog;
import com.jkds.dialog.util.InputInfo;
import com.jkds.dialog.v3.BottomMenu;
import com.jkds.dialog.v3.InputDialog;
import com.jkds.dialog.v3.MessageDialog;
import com.jkds.dialog.v3.ShareDialog;
import com.jkds.dialog.v3.TipDialog;
import com.jkds.dialog.v3.WaitDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_msg,btn_vermsg,btn_select,btn_input,btn_wait,btn_wait_tips,btn_wait_warning,btn_bottom_menu,btn_bottom_menu_title,btn_wait_error,btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_msg=findViewById(R.id.btn_msg);
        btn_vermsg=findViewById(R.id.btn_vermsg);
        btn_select=findViewById(R.id.btn_select);
        btn_input=findViewById(R.id.btn_input);
        btn_wait=findViewById(R.id.btn_wait);
        btn_wait_tips=findViewById(R.id.btn_wait_tips);
        btn_wait_warning=findViewById(R.id.btn_wait_warning);
        btn_bottom_menu=findViewById(R.id.btn_bottom_menu);
        btn_bottom_menu_title=findViewById(R.id.btn_bottom_menu_title);
        btn_wait_error=findViewById(R.id.btn_wait_error);
        btn_share=findViewById(R.id.btn_share);
        btn_msg.setOnClickListener(this);
        btn_select.setOnClickListener(this);
        btn_input.setOnClickListener(this);
        btn_wait.setOnClickListener(this);
        btn_wait_tips.setOnClickListener(this);
        btn_wait_warning.setOnClickListener(this);
        btn_bottom_menu.setOnClickListener(this);
        btn_bottom_menu_title.setOnClickListener(this);
        btn_wait_error.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_vermsg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_msg:
                //消息
                MessageDialog.show(MainActivity.this, "标题", "这是一条消息", "确定").setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                break;

            case R.id.btn_vermsg:
                //纵向消息
                MessageDialog
                        .show(MainActivity.this, "纵向排列", "这是一条纵向消息", "确定", "取消", "其他")
                        .setButtonOrientation(LinearLayout.VERTICAL).setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }).setOnOtherButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        Toast.makeText(MainActivity.this, "点击其他", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                break;

            case R.id.btn_select:
                //选择
                MessageDialog.show(MainActivity.this, "温馨提示", "确定切换城市到北京市", "确定", "取消").setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {

                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                break;
            case R.id.btn_input:
                //输入
                InputDialog.build(MainActivity.this)
                        //.setButtonTextInfo(new TextInfo().setFontColor(Color.GREEN))
                        .setTitle("提示").setMessage("请输入密码（123456）")
                        .setInputText("111111")
                        .setOkButton("确定", new OnInputDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                                if (inputStr.equals("123456")) {
                                    TipDialog.show(MainActivity.this, "成功！", TipDialog.TYPE.SUCCESS);
                                    return false;
                                } else {
                                    TipDialog.show(MainActivity.this, "密码错误", TipDialog.TYPE.ERROR);
                                    return true;
                                }
                            }
                        })
                        .setCancelButton("取消")
                        .setHintText("请输入密码")
                        .setInputInfo(new InputInfo()
                                        .setMAX_LENGTH(6)
                                        .setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                                //.setTextInfo(new TextInfo()
                                //        .setFontColor(Color.RED)
                                //)
                        )
                        .setCancelable(true)
                        .show();

                break;

            case R.id.btn_wait:

                //等待

                WaitDialog.show(MainActivity.this, "请稍后...").setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    }
                });
                WaitDialog.dismiss(2500);

                break;
            case R.id.btn_wait_tips:
                //等待加提示

                WaitDialog.show(MainActivity.this, "请稍候...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TipDialog.show(MainActivity.this, "成功！", TipDialog.TYPE.SUCCESS).setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismiss() {

                            }
                        });

                    }
                }, 2500);

                break;

            case R.id.btn_wait_warning:
                //警告
                TipDialog.show(MainActivity.this, "警告", TipDialog.TYPE.WARNING).setTipTime(2500).setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {


                    }
                });
                break;

            case R.id.btn_bottom_menu:
                //底部菜单
                BottomMenu.show(MainActivity.this, new String[]{"菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            case R.id.btn_bottom_menu_title:
                //底部菜单加标题
                BottomMenu.show(MainActivity.this, new String[]{"菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }).setTitle("标题");
                break;

            case R.id.btn_wait_error:
                //错误
                TipDialog.show(MainActivity.this, "错误", TipDialog.TYPE.ERROR).setTipTime(2500).setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {


                    }
                });
                break;

            case R.id.btn_share:
                //分享
                List<ShareDialog.Item> itemList = new ArrayList<>();
                itemList.add(new ShareDialog.Item(MainActivity.this ,R.mipmap.img_email_ios,"邮件"));
                itemList.add(new ShareDialog.Item(MainActivity.this ,R.mipmap.img_qq_ios,"QQ"));
                itemList.add(new ShareDialog.Item(MainActivity.this ,R.mipmap.img_wechat_ios,"微信"));
                itemList.add(new ShareDialog.Item(MainActivity.this ,R.mipmap.img_weibo_ios,"微博"));
                itemList.add(new ShareDialog.Item(MainActivity.this ,R.mipmap.img_memorandum_ios,"添加到“备忘录”"));
                itemList.add(new ShareDialog.Item(MainActivity.this ,R.mipmap.img_remind_ios,"提醒事项"));
                ShareDialog.show(MainActivity.this, itemList, new ShareDialog.OnItemClickListener() {
                    @Override
                    public boolean onClick(ShareDialog shareDialog, int index, ShareDialog.Item item) {
                        Toast.makeText(MainActivity.this, "点击了：" + item.getText(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                break;




        }

    }
}