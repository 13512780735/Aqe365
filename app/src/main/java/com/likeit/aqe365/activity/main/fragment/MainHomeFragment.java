package com.likeit.aqe365.activity.main.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.ZxingActivity;
import com.likeit.aqe365.activity.home.activity.CouponActivity;
import com.likeit.aqe365.activity.home.adapter.HomeAdapter;
import com.likeit.aqe365.activity.home.adapter.HomeHorGoodsAdapter;
import com.likeit.aqe365.activity.people.activity.FootprintActivity;
import com.likeit.aqe365.activity.sort.filter.GoodListActivity;
import com.likeit.aqe365.activity.sort.goods.GoodsDetailsActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.GoodCategory.GoodsCategoryModel;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.CheckPermissionUtils;
import com.likeit.aqe365.utils.ImageUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.view.MyGridView;
import com.likeit.aqe365.view.custom_scrollview.HorizontalPageLayoutManager;
import com.likeit.aqe365.view.custom_scrollview.MyRecyclerView;
import com.likeit.aqe365.view.custom_scrollview.PagingScrollHelper;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscriber;

import static com.likeit.aqe365.Interface.BaseInterface.KEY_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    //@BindView(R.id.banner)
    ConvenientBanner mBanner;
    //@BindView(R.id.MyGridView)
    MyGridView mGrideView;
    //@BindView(R.id.notice_msg_tv)
    TextView tvNotice;
    //@BindView(R.id.GridView)
    MyGridView mGridView01;
    @BindView(R.id.userinfo_img)
    ImageView mUserinfoImg;

    private static final int PAGE_SIZE = 6;//为什么是6呢？
    private int pageNum = 1;
    private boolean isErr;
    private boolean mLoadMoreEndGone = false; //是否加载更多完毕
    private int mCurrentCounter = 0;
    int TOTAL_COUNTER = 0;
    /**
     * banner
     */
    private List<MainListItemsModel.BannerBean> networkImage = new ArrayList<>();
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    //  private String[] images = {"http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg", "http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg"};

    private int[] icon = {R.mipmap.icon_home_01, R.mipmap.icon_home_02,
            R.mipmap.icon_home_03, R.mipmap.icon_home_04, R.mipmap.icon_home_05, R.mipmap.icon_home_06, R.mipmap.icon_home_07, R.mipmap.icon_home_08, R.mipmap.icon_home_09, R.mipmap.icon_home_10};
    private String[] iconName = {"牙科商城", "医用耗材", "感控产品", "积分商城", "活动专场", "新人福利", "领劵中心", "试用中心", "我的足迹", "推荐有礼"};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    /**
     * 品牌精选
     */

    private int[] icon01 = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private List<Map<String, Object>> dataList01;
    private SimpleAdapter simpleAdapter01;
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private HomeHorGoodsAdapter mHorAdapter;
    private MainListItemsModel mainListItemsModel;
    private String notice;
    /**
     * 商品列表
     */
    private List<MainListItemsModel.GoodsgroupBean> goodsList;
    private MyRecyclerView mHorRecyclerView;
    private HorizontalPageLayoutManager horizontalPageLayoutManager = null;
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private TextView mHorGoodsTitle;
    private TextView mHorGoodsMore;
    private List<MainListItemsModel.RecommendBean.GoodsBean> recommendData = null;

    public void initUI() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_home_header_items, null);
        mBanner = header.findViewById(R.id.banner);
        mGrideView = header.findViewById(R.id.MyGridView);
        mHorRecyclerView = header.findViewById(R.id.horizontal_RecyclerView);

        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mHorRecyclerView.getLayoutParams();
        params.width = w_screen;
        params.height = w_screen / 3 + 150;
        mHorRecyclerView.setLayoutParams(params);


        mHorGoodsTitle = header.findViewById(R.id.goodlist_title);
        mHorGoodsMore = header.findViewById(R.id.goodlist_more);
        tvNotice = header.findViewById(R.id.notice_msg_tv);
        mBanner.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getActivity().getWindowManager().getDefaultDisplay().getHeight() / 4));

        mSwipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        mRecyclerView = findViewById(R.id.RecyclerView);
        // mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initHorRecyclerView();
        initAdapter();
        mAdapter.addHeaderView(header);
        mUserinfoImg.setOnClickListener(new ButtonOnClickListener(mUserinfoImg.getId()));
        mBanner.startTurning(4000);

        dataList = new ArrayList<>();
        getData();
        String[] from = {"img", "name"};
        final int[] to = {R.id.iv_people_avatar, R.id.tv_people_name};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_people_gridview_items, from, to);
        //配置适配器
        mGrideView.setAdapter(simpleAdapter);
        mGrideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //牙科商城
                        startFrameActivity(Constants.FRAGMENT_HOME_DENTISTRY);
                        break;
                    case 1:// 医用耗材
                        startFrameActivity(Constants.FRAGMENT_HOME_Medical);
                        break;
                    case 2://感控产品
                        startFrameActivity(Constants.FRAGMENT_HOME_INFECTION_CONTROL);
                        break;
                    case 3://积分商城
                        break;
                    case 4://活动专场
                        break;
                    case 5://新人福利
                        break;
                    case 6://领劵中心
                        toActivity(CouponActivity.class);
                        break;
                    case 7://试用中心
                        break;
                    case 8://我的足迹
                        startActivity(new Intent(getActivity(), FootprintActivity.class));
                        break;
                    case 9://推荐有礼
                        break;

                }
            }
        });


//        /**
//         * 精品
//         */
//        dataList01 = new ArrayList<>();
//        getData01();
//        String[] from01 = {"img", "name"};
//        final int[] to01 = {R.id.iv_people_avatar};
//        simpleAdapter01 = new SimpleAdapter(getActivity(), dataList01, R.layout.layout_brand_gridview_items, from01, to01);
//        //配置适配器
//        mGridView01.setAdapter(simpleAdapter01);
//        mGridView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                showProgress("点击了！");
//            }
//        });

    }

    private void initAdapter() {
        mAdapter = new HomeAdapter(R.layout.home_goods_list_items, goodsList);
        // mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        //mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initData();

    }

    private void initHorRecyclerView() {

        horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 3);
        //滚动adapter
        mHorAdapter = new HomeHorGoodsAdapter(R.layout.home_goods_list_items_gridview_items, recommendData);
        mHorRecyclerView.setAdapter(mHorAdapter);
        mHorAdapter.notifyDataSetChanged();
        mHorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainListItemsModel.RecommendBean.GoodsBean goodsBean = recommendData.get(position);
                String cid = goodsBean.getId();
                LogUtils.d("id--》" + cid);
                Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    List<MainListItemsModel.BannerBean> adList;

    private void initData() {
        LoaddingShow();
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        LogUtils.d("5555" + token + "<-->" + signature + "<-->" + newtime + "<-->" + random);
        RetrofitUtil.getInstance().MainList(token, signature, newtime, random, new Subscriber<BaseResponse<MainListItemsModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LoaddingDismiss();
            }

            @Override
            public void onNext(BaseResponse<MainListItemsModel> baseResponse) {
                //  LogUtils.d("HomeFragment-->" + baseResponse.getCode());
                LoaddingDismiss();
                if (baseResponse.code == 200) {
                    mainListItemsModel = baseResponse.getData();
                    adList = mainListItemsModel.getBanner();
                    if (adList != null && adList.size() > 0) {
                        networkImage = adList;

                    }
                    recommendData = mainListItemsModel.getRecommend().get(0).getGoods();
                    initBanner();
                    mHorAdapter.setNewData(recommendData);
                    mHorAdapter.notifyDataSetChanged();
                    scrollHelper.setUpRecycleView(mHorRecyclerView);
                    RecyclerView.LayoutManager layoutManager = null;
                    layoutManager = horizontalPageLayoutManager;
                    if (layoutManager != null) {
                        mHorRecyclerView.setLayoutManager(layoutManager);
                        scrollHelper.updateLayoutManger();
                    }
                    mHorGoodsTitle.setText(mainListItemsModel.getRecommend().get(0).getModuletitle());
                    notice = mainListItemsModel.getNotice().get(0).getTitle();
                    tvNotice.setSelected(true);
                    tvNotice.setText(notice);
                    goodsList = mainListItemsModel.getGoodsgroup();
                    mAdapter.setNewData(goodsList);
                    mAdapter.notifyDataSetChanged();
                }

            }
        });
    }


    private void initBanner() {
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, adList).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setScrollDuration(1500);
    }


    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }


    @OnClick(R.id.userinfo_img)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.userinfo_img:
                break;
        }
    }


    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);//禁止加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mAdapter.setNewData(data);
                initData();
                isErr = false;
                //    mCurrentCounter = PAGE_SIZE;
                pageNum = 1;//页数置为1 才能继续重新加载
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(true);//启用加载
            }
        }, 2000);
    }


    public class NetworkImageHolderView implements Holder<MainListItemsModel.BannerBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, MainListItemsModel.BannerBean data) {
            Glide.with(getActivity()).load(data.getImgurl()).into(imageView);
        }
    }


    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        // initDate();
        initPermission();
        addListeners();
        initData();
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(getActivity());
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    jumpToUrl();
                    LogUtils.d("11111");
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                    LogUtils.d("22222");
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(getActivity(), uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                            LogUtils.d("33333");
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                            LogUtils.d("44444");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(getActivity(), "从设置页面返回...", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void jumpToUrl() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("https://www.baidu.com/");
        intent.setData(content_url);
        startActivity(intent);
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    /**
     * EsayPermissions接管权限处理逻辑
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onClick(viewId);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    /**
     * 按钮点击监听
     */
    class ButtonOnClickListener implements View.OnClickListener {

        private int buttonId;

        public ButtonOnClickListener(int buttonId) {
            this.buttonId = buttonId;
        }

        @Override
        public void onClick(View v) {
            //showProgress("1234");
            cameraTask(buttonId);
        }
    }

    /**
     * 按钮点击事件处理逻辑
     *
     * @param buttonId
     */
    private void onClick(int buttonId) {
        switch (buttonId) {
            case R.id.userinfo_img:
                Intent intent = new Intent(getActivity(), ZxingActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(getActivity(), "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getActivity(), "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(getActivity()).setTitle("权限申请").setPositiveButton("确认").setNegativeButton("取消")
                    .build().show();
        }
    }

}
