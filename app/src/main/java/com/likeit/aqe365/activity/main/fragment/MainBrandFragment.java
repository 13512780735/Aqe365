package com.likeit.aqe365.activity.main.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.brand.adapter.brandAdapter01;
import com.likeit.aqe365.activity.brand.adapter.brandAdapter02;
import com.likeit.aqe365.activity.brand.adapter.brandListAdapter;
import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.likeit.aqe365.activity.brand.model.BrandRefreshModel;
import com.likeit.aqe365.activity.brand.model.LogoBean;
import com.likeit.aqe365.activity.sort.goods.GoodsDetailsActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LoaddingDialog;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.ToastUtils;
import com.likeit.aqe365.view.custom_scrollview.HorizontalPageLayoutManager;
import com.likeit.aqe365.view.custom_scrollview.MyRecyclerView;
import com.likeit.aqe365.view.custom_scrollview.PagingScrollHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainBrandFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    ConvenientBanner mBanner;
    MyRecyclerView mRecyclerView01;
    RecyclerView mRecyclerView02;
    /**
     * banner
     */
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private brandListAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private BrandModel brandModel;
    private List<BrandModel.BannerBean> networkImage = new ArrayList<>();
    private List<BrandModel.LogocategoryBean> recommendList;
    private List<LogoBean> dataList;
    private brandAdapter01 mBrandAdapter01;
    private brandAdapter02 mBrandAdapter02;
    private List<BrandModel.LogocategoryBean> dataList01;

    PagingScrollHelper scrollHelper = new PagingScrollHelper();

    int num = 1;//品牌刷新数，
    private int pageNum = 1;
    private boolean isErr;
    /**
     * 品牌精选
     */


    /**
     * 推荐品牌
     */

    public void initUI() {
        setTitle("品牌专区");


        View header = LayoutInflater.from(getActivity()).inflate(R.layout.main_brand_header_view, null);
        mBanner = header.findViewById(R.id.banner);
        mRecyclerView01 = header.findViewById(R.id.mRecyclerView01);
        //GridLayoutManager gridLayoutManager01 = new GridLayoutManager(getActivity(), 4);
        // gridLayoutManager01.setAutoMeasureEnabled(true);
        mRecyclerView02 = header.findViewById(R.id.mRecyclerView02);

        GridLayoutManager gridLayoutManager02 = new GridLayoutManager(getActivity(), 2);
        // gridLayoutManager01.setAutoMeasureEnabled(true);
        mRecyclerView02.setLayoutManager(gridLayoutManager02);
        mBanner.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getActivity().getWindowManager().getDefaultDisplay().getHeight() / 4));
        mSwipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        mRecyclerView = findViewById(R.id.RecyclerView);
        // mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        //   LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        initAdapter();
        mAdapter.addHeaderView(header);
    }

    private void initAdapter() {
        mAdapter = new brandListAdapter(R.layout.brand_recomend_items, dataList01);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initData();

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

    public class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(getActivity()).load(data).into(imageView);
        }
    }

    List<BrandModel.BannerBean> adList;

    public void initData() {
        LoaddingShow();
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        LogUtils.d("token-->" + token);
        RetrofitUtil.getInstance().getBrands(token, signature, newtime, random, new Subscriber<BaseResponse<BrandModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LoaddingDismiss();
                LogUtils.d("错误--》" + e);
            }

            @Override
            public void onNext(BaseResponse<BrandModel> baseResponse) {
                LoaddingDismiss();
                if (baseResponse.code == 200) {
                    brandModel = baseResponse.getData();
                    adList = brandModel.getBanner();
                    if (adList != null && adList.size() > 0) {
                        networkImage = adList;
                    }
                    initBanner();
                    dataList = brandModel.getLogo();
                    dataList01 = brandModel.getLogocategory();
                    //recommendList = brandModel.getLogocategory();
                    mAdapter.setNewData(dataList01);
                    mAdapter.notifyDataSetChanged();
                    initbrand01();
                    initBrand();
                }
            }
        });
    }

    private void initBrand() {
        /**
         * 推荐
         */

        mBrandAdapter02 = new brandAdapter02(getActivity(), dataList01);
        //配置适配器
        mRecyclerView02.setAdapter(mBrandAdapter02);
        mBrandAdapter02.notifyDataSetChanged();
    }

    private void brandRefresh() {
        LoaddingShow();
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().brandsRefresh(token, signature, newtime, random, String.valueOf(num), new Subscriber<BaseResponse<BrandRefreshModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LoaddingDismiss();
            }

            @Override
            public void onNext(BaseResponse<BrandRefreshModel> baseResponse) {
                LogUtils.d("初始化");
                LoaddingDismiss();
                if (baseResponse.code == 200) {
                    // List<BrandRefreshModel.DataBean> dataList02 = baseResponse.getData().getData();
                    LogUtils.d("data-->" + baseResponse.getData().getData().get(0).getAdvimg());
                    LogUtils.d("data22-->" + baseResponse.getData().getNum());
                    Integer num01 = Integer.valueOf(baseResponse.getData().getNum());
                    num = num01+1;
                    LogUtils.d("data-->" + num);
                    if (dataList.size() > 0) {
                        dataList.clear();
                        dataList = baseResponse.getData().getData();
                        mBrandAdapter01.setNewData(dataList);
                        mBrandAdapter01.notifyDataSetChanged();
                        initbrand01();
                    }

                }
            }
        });
    }

    private void initbrand01() {
        View footView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_brand_gridview_items, null);
        ImageView imageView = footView.findViewById(R.id.iv_people_avatar);
        imageView.setBackground(getResources().getDrawable(R.mipmap.refresh));

        /**
         * 精品
         */
        // mRecyclerView01.addHeaderView(footView);
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(2, 4);
        mRecyclerView01.setLayoutManager(horizontalPageLayoutManager);

        mBrandAdapter01 = new brandAdapter01(R.layout.layout_brand_gridview_items, dataList);
        mRecyclerView01.setAdapter(mBrandAdapter01);
        scrollHelper.setUpRecycleView(mRecyclerView01);
        RecyclerView.LayoutManager layoutManager = null;
        layoutManager = horizontalPageLayoutManager;
        if (layoutManager != null) {
            mRecyclerView01.setLayoutManager(layoutManager);
            scrollHelper.updateLayoutManger();
        }
        mBrandAdapter01.addFooterView(footView);
        mBrandAdapter01.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brandRefresh();
            }
        });
    }


    private void initBanner() {
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView1>() {
            @Override
            public NetworkImageHolderView1 createHolder() {
                return new NetworkImageHolderView1();
            }
        }, adList).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setScrollDuration(1500);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_brand;
    }

    @Override
    protected void lazyLoad() {
        dataList = new ArrayList<>();
        dataList01 = new ArrayList<>();
        initUI();
    }


    public class NetworkImageHolderView1 implements Holder<BrandModel.BannerBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, BrandModel.BannerBean data) {
            Glide.with(getActivity()).load(data.getThumb()).into(imageView);
        }
    }

}
