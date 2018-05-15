package com.likeit.aqe365.activity.people.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoqi.actionsheet.ActionSheet;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.cart.EditAddressActivity;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;
import com.likeit.aqe365.utils.photo.PhotoUtils;
import com.likeit.aqe365.view.CircleImageView;
import com.likeit.aqe365.view.CustomPopWindow;
import com.likeit.aqe365.view.custom.GridViewAddImgesAdpter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener, ActionSheet.OnActionSheetSelected, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_bind)
    TextView mTvBind;
    @BindView(R.id.rl_bind_phone)
    RelativeLayout mRlBindPhone;
    @BindView(R.id.ed_nickName)
    EditText mEdNickName;
    @BindView(R.id.ll_nickName)
    LinearLayout mLlNickName;
    @BindView(R.id.ed_weiXin)
    EditText mEdWeiXin;
    @BindView(R.id.ll_weiXin)
    LinearLayout mLlWeiXin;
    @BindView(R.id.ed_companyName)
    EditText mEdCompanyName;
    @BindView(R.id.ll_companyName)
    LinearLayout mLlCompanyName;
    @BindView(R.id.tv_company_nature)
    TextView mTvCompanyNature;
    @BindView(R.id.ll_company_nature)
    LinearLayout mLlCompanyNature;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ed_details_address)
    EditText mEdDetailsAddress;
    @BindView(R.id.ll_details_address)
    LinearLayout mLlDetailsAddress;
    @BindView(R.id.mGridView)
    GridView mGridView;
    @BindView(R.id.tv_edit)
    TextView mTvEdit;
    private CustomPopWindow mCustomPopWindow;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter;
    /**
     * 图片
     */
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private List<Map<String, Object>> datas;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        datas = new ArrayList<>();//图片
        initUI();
        addListeners();
        initData();
        PhotoUtils.getInstance().init(this, true, new PhotoUtils.OnSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                if (status == 0) {
                    Glide.with(UserInfoActivity.this).load(outputUri).into(mIvAvatar);
                } else if (status == 1) {
                    photoPath(outputFile.getAbsolutePath());
                }

            }
        });
    }

    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_member_title));
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvEdit);
        textChangeListener.addAllEditText(mEdNickName, mEdWeiXin, mEdCompanyName, mEdDetailsAddress);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvEdit.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    mTvEdit.setOnClickListener(UserInfoActivity.this);
                } else {
                    mTvEdit.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                    // tv_register.setClickable(false);
                }
            }
        });

        /**
         * 图片
         */
        gridViewAddImgesAdpter = new GridViewAddImgesAdpter(datas, this);
        mGridView.setAdapter(gridViewAddImgesAdpter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                status = 1;
                ActionSheet.showSheet(UserInfoActivity.this, UserInfoActivity.this, null);
            }
        });

    }

    public void initData() {

    }

    public void addListeners() {

    }

    @OnClick({R.id.iv_avatar, R.id.rl_bind_phone, R.id.ll_nickName, R.id.ll_weiXin, R.id.ll_company_nature})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_avatar:
                status = 0;
                ActionSheet.showSheet(UserInfoActivity.this, UserInfoActivity.this, null);
                break;
            case R.id.rl_bind_phone:
                toActivity(BindPhoneActivity.class);
                break;
            case R.id.ll_nickName:
                break;
            case R.id.ll_weiXin:
                break;
            case R.id.ll_company_nature:
                showPopMenu();
                break;
            case R.id.tv_edit:
                break;
        }
    }

    private void showPopMenu() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu2, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(mTvCompanyNature, 0, 20);
    }

    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.menu1:
                        mTvCompanyNature.setText("请选择单位性质");
                        break;
                    case R.id.menu2:
                        mTvCompanyNature.setText("诊所");
                        break;
                    case R.id.menu3:
                        mTvCompanyNature.setText("其他");
                        break;
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
    }

    public void photoPath(String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", path);
        //upload(path);
        datas.add(map);
        gridViewAddImgesAdpter.notifyDataSetChanged();
    }

    String[] takePhotoPerms = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA};
    String[] selectPhotoPerms = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};

    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case ActionSheet.CHOOSE_PICTURE:
                //相册
                checkPermission(selectPhotoPerms, 2);
                break;
            case ActionSheet.TAKE_PICTURE:
                //拍照
                checkPermission(takePhotoPerms, 1);
                break;
            case ActionSheet.CANCEL:
                //取消
                break;
        }
    }

    private void checkPermission(String[] perms, int requestCode) {
        if (EasyPermissions.hasPermissions(this, perms)) {//已经有权限了
            switch (requestCode) {
                case 1:
                    PhotoUtils.getInstance().takePhoto();
                    break;
                case 2:
                    PhotoUtils.getInstance().selectPhoto();
                    break;
            }
        } else {//没有权限去请求
            EasyPermissions.requestPermissions(this, "权限", requestCode, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {//设置成功
        switch (requestCode) {
            case 1:
                PhotoUtils.getInstance().takePhoto();
                break;
            case 2:
                PhotoUtils.getInstance().selectPhoto();
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限设置")
                    .setPositiveButton("设置")
                    .setRationale("当前应用缺少必要权限,可能会造成部分功能受影响！请点击\"设置\"-\"权限\"-打开所需权限。最后点击两次后退按钮，即可返回")
                    .build()
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtils.getInstance().bindForResult(requestCode, resultCode, data);
    }
}
