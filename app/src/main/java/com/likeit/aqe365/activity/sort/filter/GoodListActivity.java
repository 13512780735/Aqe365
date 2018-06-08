package com.likeit.aqe365.activity.sort.filter;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.adapter.GoodListAdapter;
import com.likeit.aqe365.activity.sort.filter.adapter.LeftAdapter;
import com.likeit.aqe365.activity.sort.filter.adapter.MiddleAdapter;
import com.likeit.aqe365.activity.sort.filter.adapter.RightAdapter;
import com.likeit.aqe365.activity.sort.filter.bean.ShopRightListBean;
import com.likeit.aqe365.activity.sort.filter.bean.ShopSortBean;
import com.likeit.aqe365.activity.sort.filter.bean.ShopSortItemBean;
import com.likeit.aqe365.activity.sort.filter.bean.ShopSortListBean;
import com.likeit.aqe365.activity.sort.goods.GoodsDetailsActivity;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.CaseEntity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.search_content_et)
    TextView mSearchContentEt;
    @BindView(R.id.search_layout)
    LinearLayout mSearchLayout;
    @BindView(R.id.ll_filter)
    LinearLayout mFilter;
    @BindView(R.id.message_img)
    ImageView mMessageImg;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.tv_synthesis_sort)
    TextView mTvSynthesisSort;
    @BindView(R.id.layout_synthesis_sort)
    RelativeLayout mLayoutSynthesisSort;
    @BindView(R.id.tv_sales_sort)
    TextView mTvSalesSort;
    @BindView(R.id.layout_sales_sort)
    RelativeLayout mLayoutSalesSort;
    @BindView(R.id.tv_sort_price)
    TextView mTvSortPrice;
    @BindView(R.id.layout_expert_service)
    RelativeLayout mLayoutExpertService;
    @BindView(R.id.tv_filter_sort)
    TextView mTvFilterSort;
    @BindView(R.id.layout_filter_sort)
    RelativeLayout mLayoutFilterSort;

    private TagFlowLayout mFlowLayout;
    private String[] mValues = new String[]
            {
                    "推荐商品", "新品上市", "热卖商品",
                    "促销商品", "卖家包邮", "限时抢购"
            };
    private TextView tvCancel, tvConfirm;


    private boolean synthesisFalg = false;
    private boolean salesFalg = false;
    private boolean priceFalg = false;
    private boolean filterFalg = false;


    private ArrayList<CaseEntity> data;
    private int pageNum = 1;
    private static final int PAGE_SIZE = 6;//为什么是6呢？
    private boolean isErr;
    private boolean mLoadMoreEndGone = false; //是否加载更多完毕
    private int mCurrentCounter = 0;
    int TOTAL_COUNTER = 0;
    private GoodListAdapter mAdapter;
    private View filterView;
    private PopupWindow filterPop;
    private RecyclerView mLeftRvRecyclerView, mRightRvRecyclerView, mMiddleRvRecyclerView;
    private List<ShopSortBean> shopSortBeanList;
    private List<ShopSortListBean> listBeanList;
    private LeftAdapter leftAdapter;
    private MiddleAdapter middleAdapter;
    private RightAdapter rightAdapter;
    private ArrayList<ShopSortItemBean> rightBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_list);
        initUI();
        initFilter();
    }

    private void initFilter() {
        // filterPop=new FilterPopupWindow(mFilter,this);
        filterView = getLayoutInflater().inflate(R.layout.good_filter_popwindows_items, null);
        filterPop = new PopupWindow(filterView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        filterPop.setBackgroundDrawable(null);
        filterPop.setFocusable(true);//可以试试设为false的结果
        filterPop.setOutsideTouchable(false); // 设置非PopupWindow区域可触摸
        tvCancel = (TextView) filterView.findViewById(R.id.tv_cancel);
        tvConfirm = (TextView) filterView.findViewById(R.id.tv_confirm);
        mFlowLayout = (TagFlowLayout) filterView.findViewById(R.id.item_main_right_taglayout);
        mLeftRvRecyclerView = (RecyclerView) filterView.findViewById(R.id.filter_left_rv);
        mRightRvRecyclerView = (RecyclerView) filterView.findViewById(R.id.filter_right_rv);
        mMiddleRvRecyclerView = (RecyclerView) filterView.findViewById(R.id.filter_middle_rv);
        final LayoutInflater mInflater = LayoutInflater.from(this);
        mFlowLayout.setAdapter(new TagAdapter<String>(mValues) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_filter_tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return super.setSelected(position, s);
            }
        });

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //Toast.makeText(getActivity(), mValues[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                // getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSortBeanList.clear();
                listBeanList.clear();
                rightBeanList.clear();
                filterPop.dismiss();
                filterFalg = true;
                mTvSynthesisSort.setTextColor(Color.parseColor("#333333"));
                mTvSalesSort.setTextColor(Color.parseColor("#333333"));
                mTvSortPrice.setTextColor(Color.parseColor("#333333"));
                mTvFilterSort.setTextColor(Color.parseColor("#333333"));
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSortBeanList.clear();
                listBeanList.clear();
                rightBeanList.clear();
                filterPop.dismiss();
                filterFalg = true;
                mTvSynthesisSort.setTextColor(Color.parseColor("#333333"));
                mTvSalesSort.setTextColor(Color.parseColor("#333333"));
                mTvSortPrice.setTextColor(Color.parseColor("#333333"));
                mTvFilterSort.setTextColor(Color.parseColor("#333333"));
            }
        });

        if (!filterFalg) {
            mTvFilterSort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    initRecycleView();
                    mTvSynthesisSort.setTextColor(Color.parseColor("#333333"));
                    mTvSalesSort.setTextColor(Color.parseColor("#333333"));
                    mTvSortPrice.setTextColor(Color.parseColor("#333333"));
                    mTvFilterSort.setTextColor(Color.parseColor("#595959"));
                    synthesisFalg = false;
                    salesFalg = false;
                    priceFalg = false;
                    filterFalg = true;
                    filterPop.showAsDropDown(mFilter);

                }
            });

        } else {

            filterPop.dismiss();
            mTvFilterSort.setTextColor(Color.parseColor("#333333"));
            filterFalg = false;
        }


    }

    private void initRecycleView() {
        initData01();
        leftAdapter = new LeftAdapter(R.layout.item_filter_listview_view, shopSortBeanList);
        middleAdapter = new MiddleAdapter(R.layout.item_filter_listview_view, listBeanList);
        rightAdapter = new RightAdapter(R.layout.item_filter_listview_view, rightBeanList);
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mMiddleRvRecyclerView.setAdapter(middleAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);
        mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMiddleRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ShopSortBean shopSortBean = shopSortBeanList.get(i);
                listBeanList.clear();
                listBeanList.addAll(shopSortBean.getmList());
                leftAdapter.setSelectPos(i);
                leftAdapter.notifyDataSetChanged();
                middleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
        mMiddleRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopSortListBean shopSortBean = listBeanList.get(position);
                rightBeanList.clear();
                rightBeanList.addAll(shopSortBean.getmList());
                middleAdapter.setSelectPos(position);
                middleAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRightRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rightAdapter.setSelectPos(position);
                rightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }

    private void initData01() {
        shopSortBeanList = new ArrayList<>();
        listBeanList = new ArrayList<>();
        rightBeanList = new ArrayList<>();

        ShopSortBean d1 = new ShopSortBean();
        d1.setTitle("牙科商城");
        ShopSortBean d2 = new ShopSortBean();
        d2.setTitle("医用耗材");
        ShopSortBean d3 = new ShopSortBean();
        d3.setTitle("感控产品");

        ShopSortListBean l1 = new ShopSortListBean();

        l1.setType("常用耗材");


        ShopSortListBean l2 = new ShopSortListBean();

        l2.setType("日常护理");
        ShopSortListBean l3 = new ShopSortListBean();

        l3.setType("预防保护");

        ShopSortItemBean b1 = new ShopSortItemBean();
        b1.setName("检查垫");
        ShopSortItemBean b2 = new ShopSortItemBean();
        b2.setName("器械盒");
        ShopSortItemBean b3 = new ShopSortItemBean();
        b3.setName("卫生敷料");
        ShopSortItemBean b4 = new ShopSortItemBean();
        b4.setName("一次性用品");


        List<ShopSortItemBean> list1 = new ArrayList<>();
        List<ShopSortItemBean> list2 = new ArrayList<>();
        List<ShopSortItemBean> list3 = new ArrayList<>();
        list1.add(b1);
        list1.add(b2);
        list1.add(b3);
        list2.add(b2);
        list2.add(b3);
        list3.add(b4);

        l1.setmList(list1);
        l2.setmList(list2);
        l3.setmList(list3);
        List<ShopSortListBean> li1 = new ArrayList<>();
        List<ShopSortListBean> li2 = new ArrayList<>();
        List<ShopSortListBean> li3 = new ArrayList<>();

        li1.add(l1);
        li1.add(l2);
        li1.add(l3);
        li2.add(l1);
        li2.add(l2);
        li3.add(l3);

        d1.setmList(li1);
        d2.setmList(li2);
        d3.setmList(li3);


        shopSortBeanList.add(d1);
        shopSortBeanList.add(d2);
        shopSortBeanList.add(d3);


        //listBeanList.addAll(shopSortBeanList.get(0).getmList());
    }

    private void initUI() {


        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        initData();
        initAdapter();

    }

    @OnClick({R.id.iv_back, R.id.message_img, R.id.tv_synthesis_sort, R.id.layout_synthesis_sort, R.id.tv_sales_sort, R.id.layout_sales_sort, R.id.tv_sort_price, R.id.layout_expert_service})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.message_img:
                break;

            case R.id.tv_synthesis_sort:
            case R.id.layout_synthesis_sort:
                if (filterPop != null) {
                    filterPop.dismiss();
                }
                mTvSalesSort.setTextColor(Color.parseColor("#333333"));
                mTvSortPrice.setTextColor(Color.parseColor("#333333"));
                mTvFilterSort.setTextColor(Color.parseColor("#333333"));
                salesFalg = false;
                priceFalg = false;
                filterFalg = false;
                if (!synthesisFalg) {
                    mTvSynthesisSort.setTextColor(Color.parseColor("#FF4081"));
                } else {
                    mTvSynthesisSort.setTextColor(Color.parseColor("#333333"));
                }
                break;
            case R.id.tv_sales_sort:
            case R.id.layout_sales_sort:
                if (filterPop != null) {
                    filterPop.dismiss();
                }
                mTvSynthesisSort.setTextColor(Color.parseColor("#333333"));
                mTvSortPrice.setTextColor(Color.parseColor("#333333"));
                mTvFilterSort.setTextColor(Color.parseColor("#333333"));
                synthesisFalg = false;
                priceFalg = false;
                filterFalg = false;
                if (!salesFalg) {
                    mTvSalesSort.setTextColor(Color.parseColor("#FF4081"));
                } else {
                    mTvSalesSort.setTextColor(Color.parseColor("#333333"));
                }
                break;
            case R.id.tv_sort_price:
            case R.id.layout_expert_service:
                if (filterPop != null) {
                    filterPop.dismiss();
                }
                mTvSynthesisSort.setTextColor(Color.parseColor("#333333"));
                mTvSalesSort.setTextColor(Color.parseColor("#333333"));
                mTvFilterSort.setTextColor(Color.parseColor("#333333"));
                synthesisFalg = false;
                salesFalg = false;
                filterFalg = false;
                if (!priceFalg) {
                    mTvSortPrice.setTextColor(Color.parseColor("#FF4081"));
                } else {
                    mTvSortPrice.setTextColor(Color.parseColor("#333333"));
                }
                break;

        }
    }

    private void initAdapter() {

        mAdapter = new GoodListAdapter(R.layout.good_listview_items, data);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mCurrentCounter = mAdapter.getData().size();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toActivity(GoodsDetailsActivity.class);
            }
        });
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            data.add(caseEntity);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        //  TOTAL_COUNTER = Integer.valueOf(myfollowModel.getTotal());
        if (mAdapter.getData().size() < PAGE_SIZE) {
            mAdapter.loadMoreEnd(true);
        } else {
            if (mCurrentCounter >= TOTAL_COUNTER) {
                mAdapter.loadMoreEnd(mLoadMoreEndGone);
            } else {
                if (isErr) {
                    pageNum += 1;
                    //  initDate(pageNum, true);
                    //    mAdapter.addData(data);
                    mCurrentCounter = mAdapter.getData().size();
                    mAdapter.loadMoreComplete();
                } else {
                    isErr = true;
                    // Toast.makeText(getContext(), "错误", Toast.LENGTH_LONG).show();
                    mAdapter.loadMoreFail();
                }
            }
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);//禁止加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mAdapter.setNewData(data);
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                pageNum = 1;//页数置为1 才能继续重新加载
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(true);//启用加载
            }
        }, 2000);
    }
}
