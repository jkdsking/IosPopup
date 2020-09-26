# IosPopup
Ios样式弹窗
![image](https://github.com/jkdsking/IosPopup/blob/master/png/main.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/message.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/message_ver.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/select.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/input.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/wait.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/warn.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/buttom.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/buttom_menu.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/error.jpg)![image](https://github.com/jkdsking/IosPopup/blob/master/png/share.jpg)
gradle接入

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.jkdsking:IosPopup:1.0.1'
	}
 
  具体使用：
  	 	
		//消息
                MessageDialog.show(MainActivity.this, "标题", "这是一条消息", "确定").setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
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
		  MessageDialog.show(MainActivity.this, "标题", "你要做什么事", "确定", "取消").setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {

                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
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
                          //等待

                WaitDialog.show(MainActivity.this, "请稍后...").setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    }
                });
                WaitDialog.dismiss(2500);
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
                   //警告
                TipDialog.show(MainActivity.this, "警告", TipDialog.TYPE.WARNING).setTipTime(2500).setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {


                    }
                });
                  //底部菜单
                BottomMenu.show(MainActivity.this, new String[]{"菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
                  //底部菜单加标题
                BottomMenu.show(MainActivity.this, new String[]{"菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3","菜单1", "菜单2", "菜单3"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }).setTitle("标题");
               //错误
                TipDialog.show(MainActivity.this, "错误", TipDialog.TYPE.ERROR).setTipTime(2500).setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {


                    }
                });
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
                
