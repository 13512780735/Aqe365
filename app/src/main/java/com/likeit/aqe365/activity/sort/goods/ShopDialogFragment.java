package com.likeit.aqe365.activity.sort.goods;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.cart.activity.ConfirmOrderActivity;
import com.likeit.aqe365.activity.sort.adapter.GoodsSalesAdapter;
import com.likeit.aqe365.activity.sort.view.ChooseGoodsSales.CustomLinearLayoutManager;
import com.likeit.aqe365.listener.OnSalesSelectListener;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.network.model.goods.GoodsSalesModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LoaddingDialog;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.StatusBarUtil;
import com.likeit.aqe365.utils.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopDialogFragment extends DialogFragment {


    private TextView tvMoney;
    private GoodDetailModel goodDetailModel;
    private RecyclerView mRecyclerView;
    private ImageView ivImage;
    private LinearLayout llClose;
    private ImageView ivSub;//购买数量加减
    private ImageView ivAdd;
    private EditText etGoodNum;
    private TextView tvLeaveNumber;
    private int sum = 0;//购买商品库存
    private int countNum = 1;//购买商品数量
    private Button btnSure;
    private GoodsSalesAdapter chooseGoodsAdapter;
    private HashMap<Integer, String> map;
    private TextView tvChoose;
    private String imgUrl;
    private String sales;
    private String goodsId;
    private String keys;
    private OnSalesSelectListener mOnSalesSelectListener;
    private String token;
    private LoaddingDialog loaddingDialog;
    private int goodSNumber;
    private Button btn_sure;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    public void setOnLoginInforCompleted(OnSalesSelectListener onSalesSelectListener) {
        mOnSalesSelectListener = onSalesSelectListener;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int color = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(getActivity(), color, 0);
        StatusBarUtil.setLightMode(getActivity());
        View view = inflater.inflate(R.layout.fragment_shop_dialog, container, false);
        token = SharedPreferencesUtils.getString(getActivity(), "token");
        keys = getArguments().getString("keys");//1.加入购物车 2.立即购买
        goodDetailModel = (GoodDetailModel) getArguments().getSerializable("goodDetailModel");
        goodsSalesModel = (GoodsSalesModel) getArguments().getSerializable("goodsSalesModel");
        loaddingDialog = new LoaddingDialog(getActivity());
//        sales = SharedPreferencesUtils.getString(getActivity(), "sales");
//        goodsId = SharedPreferencesUtils.getString(getActivity(), "goodsId");
//        countNum = SharedPreferencesUtils.getInt(getActivity(), "countNum");
//        id = SharedPreferencesUtils.getInt(getActivity(), "id");
        initView(view);
        return view;


    }


    @Override
    public void onStart() {
        super.onStart();

        DisplayMetrics dm = new DisplayMetrics();

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;

        window.setAttributes(windowParams);
    }


    private GoodsSalesModel goodsSalesModel = null;

    private void initView(View view) {
        map = new HashMap<>();
        ivImage = view.findViewById(R.id.iv_image);
        btn_sure = view.findViewById(R.id.btn_sure);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        tvLeaveNumber = view.findViewById(R.id.tv_leave_number);
        tvMoney = view.findViewById(R.id.tv_money);
        llClose = view.findViewById(R.id.ll_close);
        ivSub = view.findViewById(R.id.iv_sub);
        etGoodNum = view.findViewById(R.id.et_good_num);
        ivAdd = view.findViewById(R.id.iv_add);
        btnSure = view.findViewById(R.id.btn_sure);
        tvChoose = view.findViewById(R.id.tv_have_choose);
        tvChoose.setText(sales);


        ImageLoader.getInstance().displayImage(goodDetailModel.getDetail_tab().getThumb().get(0).getImgurl(), ivImage);
        tvMoney.setText("¥ " + goodDetailModel.getDetail_tab().getMarketprice());
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(getActivity());
        mLayoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        chooseGoodsAdapter = new GoodsSalesAdapter();
        mRecyclerView.setAdapter(chooseGoodsAdapter);
        if (goodsSalesModel != null) {
            chooseGoodsAdapter.addData(goodsSalesModel.getList());
            //****************************初始化数据**********************************/


            for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                    goodsSalesModel.getList().get(i).getSpec().get(j).setCanSelect(true);
                }
            }
            //******************开始匹配前再把所有没货的id挑出来(开始)******************/
            List<String> allIDList = new ArrayList<>();
            List<String> allIDList1 = new ArrayList<>();
            List<String> allList = new ArrayList<>();
            for (int n = 0; n < goodsSalesModel.getPrice().size(); n++) {
                for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                    for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                        allList.add(goodsSalesModel.getList().get(i).getSpec().get(j).getId());
                        if (goodsSalesModel.getPrice().get(n).getSpecs().contains(goodsSalesModel.getList().get(i).getSpec().get(j).getId())) {
                            allIDList.add(goodsSalesModel.getPrice().get(n).getSpecs());
                        }
                    }
                }
            }
            for (int i = 0; i < allIDList.size(); i++) {
                List<String> list = Arrays.asList(allIDList.get(i).split("_"));
                for (String s : list) {
                    allIDList1.add(s);
                }
            }
            allList.removeAll(allIDList1);
            for (int n = 0; n < allList.size(); n++) {
                for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                    for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                        if (allList.get(n).contains(goodsSalesModel.getList().get(i).getSpec().get(j).getId())) {
                            goodsSalesModel.getList().get(i).getSpec().get(j).setCanSelect(false);
                        }
                    }
                }
            }
            //****************开始匹配前再把所有没货的id挑出来(结束)*********************/
            chooseGoodsAdapter.setTagItemOnClickListener(new GoodsSalesAdapter.TagItemOnClick() {
                @Override
                public void onItemClick(View view, int positions, int position) {
                    //*********************布局切换相关(开始)*********************/
                    if (goodsSalesModel.getList().get(position).getSpec().get(positions).isCanSelect()) {
                        if (goodsSalesModel.getList().get(position).getSpec().get(positions).isSelect()) {
                            map.remove(position);
                            goodsSalesModel.getList().get(position).getSpec().get(positions).setSelect(false);
                        } else {
                            map.put(position, goodsSalesModel.getList().get(position).getSpec().get(positions).getId());
                            for (int i = 0; i < goodsSalesModel.getList().get(position).getSpec().size(); i++) {
                                goodsSalesModel.getList().get(position).getSpec().get(i).setSelect(false);
                            }
                            goodsSalesModel.getList().get(position).getSpec().get(positions).setSelect(true);
                            LogUtils.d("ImgUrl222--》" + goodsSalesModel.getList().get(position).getSpec().get(positions).getThumb());

                            imgUrl = goodsSalesModel.getList().get(position).getSpec().get(positions).getThumb();
                            if (imgUrl != null) {
                                ImageLoader.getInstance().displayImage(imgUrl, ivImage);
                                //  ImageLoader.getInstance().displayImage(goodDetailModel.getDetail_tab().getThumb().get(0).getImgurl().trim(), ivImage);
                            }

                        }
                        //*********************布局切换相关(结束)*********************/
                        if (map.size() == chooseGoodsAdapter.getItemCount()) {
                            Log.v("规格", "开始匹配");
                            String ids = "";
                            String ids1 = "";
                            for (int i = 0; i < map.size(); i++) {
                                ids += map.get(i) + "_";
                            }
                            if (map.size() >= 1) {
                                for (int i = map.size() - 1; i >= 0; i--) {
                                    ids1 += map.get(i) + "_";
                                }
                            }
                            boolean isHave = false;
                            for (int i = 0; i < goodsSalesModel.getPrice().size(); i++) {
                                String idString = goodsSalesModel.getPrice().get(i).getSpecs();
                                if (ids.contains(idString) || ids1.contains(idString)) {
                                    isHave = true;
                                    Log.v("规格", "匹配到了");
//                                Utils.setImageViewSigle( goodsSalesModel.getPrice().get(i).getPic(), ivImage, mContext);
                                    tvMoney.setText("¥ " + goodsSalesModel.getPrice().get(i).getMarketprice());
                                    goodSNumber = Integer.valueOf(goodsSalesModel.getPrice().get(i).getStock());
                                    if (goodSNumber == 0) {
                                        btn_sure.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                                        btn_sure.setClickable(false);
                                    } else {
                                        btn_sure.setBackgroundColor(getActivity().getResources().getColor(R.color.apply));
                                        btn_sure.setClickable(true);
                                    }
                                    tvLeaveNumber.setText("库存" + goodSNumber + "件");
                                    sales = goodsSalesModel.getPrice().get(i).getTitle();
                                    goodsId = goodsSalesModel.getPrice().get(i).getId();
                                    tvChoose.setText("已选择 " + sales);
                                    sum = Integer.valueOf(goodsSalesModel.getPrice().get(i).getStock());

                                }
                            }
//                            if (!isHave) {
//                                tvMoney.setText("¥ " + goodDetailModel.getDetail_tab().getMarketprice());
//                                goodSNumber = 0;
//                                tvLeaveNumber.setText("库存" + goodSNumber + "件");
//                                tvChoose.setText("已选择");
//                                sum = 0;
//                                Toast.makeText(getActivity(), "该规格没库存了", Toast.LENGTH_SHORT).show();
//
//                            }
                        }
                        //****************开始匹配********************/
                        for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                            for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                                goodsSalesModel.getList().get(i).getSpec().get(j).setCanSelect(true);

                            }
                        }
                        List<String> idValue = new ArrayList<>();//所选的id在所有可选规格里的可能id
                        List<String> idList = new ArrayList<>();//把这些id拆开，装里面
                        List<String> allIdList = new ArrayList<>();//所有的可能id
                        boolean isMath = true;//是否开始进行匹配
                        //******************如果所有配对都有货就不进行以下所有的匹配了(开始)**********/
                        int totalType = 1;
                        for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                            totalType = totalType * goodsSalesModel.getList().get(i).getSpec().size();
                        }
                        if (totalType == goodsSalesModel.getPrice().size()) {
                            isMath = false;
                        }
                        //******************如果所有配对都有货就不进行以下所有的匹配了(结束)**********/
                        for (int i = 0; i < goodsSalesModel.getPrice().size(); i++) {
                            for (Map.Entry<Integer, String> m : map.entrySet()) {
                                if (goodsSalesModel.getPrice().get(i).getSpecs().contains(m.getValue())) {
                                    idValue.add(goodsSalesModel.getPrice().get(i).getSpecs());
                                }
                            }
                        }
                        if (map.size() == 0) {//如果现在取消刚在选中的就当前没有一个被选中就不匹配了
                            isMath = false;
                        }
                        if (isMath) {
                            for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                                for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                                    allIdList.add(goodsSalesModel.getList().get(i).getSpec().get(j).getId());
                                }
                            }
                            for (int i = 0; i < idValue.size(); i++) {
                                List<String> list = Arrays.asList(idValue.get(i).split(","));
                                for (String s : list) {
                                    idList.add(s);
                                }
                            }
                            allIdList.removeAll(idList);
                            for (int n = 0; n < allIdList.size(); n++) {
                                for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                                    for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                                        if (allIdList.get(n).contains(goodsSalesModel.getList().get(i).getSpec().get(j).getId())) {
                                            goodsSalesModel.getList().get(i).getSpec().get(j).setCanSelect(false);
                                        }
                                    }
                                }
                            }
                            for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
                                for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
                                    Log.v("规格打印", goodsSalesModel.getList().get(i).getSpec().get(j).getId() + ":" + goodsSalesModel.getList().get(i).getSpec().get(j).isCanSelect() + "");
                                }
                            }
                        }
                    }
                    //***********************匹配结束******************************/
                    chooseGoodsAdapter.notifyDataSetChanged();
                    Log.v("规格", "" + goodsSalesModel.getList().get(position).getSpec().get(positions).isSelect());
                    Log.v("规格", position + "," + map.get(position));
                }
            });
        } else {
            if (goodDetailModel.getDetail_tab().getStock() != null) {
                btn_sure.setBackgroundColor(getActivity().getResources().getColor(R.color.apply));
                btn_sure.setClickable(true);
                goodsId = "";
            } else {
                btn_sure.setBackgroundColor(getActivity().getResources().getColor(R.color.apply));
                btn_sure.setClickable(true);
            }
        }


        //****************************选择商品规格（结束）****************************************//
        etGoodNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etGoodNum.setText(s.subSequence(0, 1));
                        etGoodNum.setSelection(1);
                        return;
                    }
                }
                if (s.length() > 0) {
                    if (Integer.parseInt(s.toString().trim()) > sum) {
                        countNum = 1;
                        etGoodNum.setText("1");
                        Toast.makeText(getActivity(), "库存不够", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(s.toString().trim()) < 1) {
                        countNum = 1;
                        etGoodNum.setText("1");
                        Toast.makeText(getActivity(), "不能再减啦!", Toast.LENGTH_SHORT).show();
                    } else {
                        countNum = Integer.parseInt(s.toString().trim());
                    }
                } else {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        llClose.setOnClickListener(new View.OnClickListener() {//关闭弹框
            @Override
            public void onClick(View v) {
//                for (int i = 0; i < goodsSalesModel.getList().size(); i++) {
//                    for (int j = 0; j < goodsSalesModel.getList().get(i).getSpec().size(); j++) {
//                        goodsSalesModel.getList().get(i).getSpec().get(j).setSelect(false);
//                        goodsSalesModel.getList().get(i).getSpec().get(j).setCanSelect(true);
//                    }
//                }
                getDialog().dismiss();
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countNum < sum) {
                    countNum = countNum + 1;
                    etGoodNum.setText(countNum + "");
                } else {
                    Toast.makeText(getActivity(), "不能再加啦!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countNum > 1) {
                    countNum = countNum - 1;
                    etGoodNum.setText(countNum + "");
                } else {
                    Toast.makeText(getActivity(), "不能再减啦!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//确定提交订单
//                LogUtils.d("111-->" + chooseGoodsAdapter.getItemCount());
//                LogUtils.d("2222->" + map.size());
                tvChoose.setText("已选择 " + sales);
                btn_sure.setBackgroundColor(getActivity().getResources().getColor(R.color.apply));
                btn_sure.setClickable(true);
                if ("0".equals(keys)) {
                    LogUtils.d("ids-->" + sales + "goodsId-->" + goodsId + "countNum-->" + countNum);
//                    SharedPreferencesUtils.put(getActivity(), "sales", sales);
//                    SharedPreferencesUtils.put(getActivity(), "goodsId", goodsId);
//                    SharedPreferencesUtils.put(getActivity(), "countNum", countNum);
//                    SharedPreferencesUtils.put(getActivity(), "id", id);
                    mOnSalesSelectListener.selectSalesListener(sales, goodsId, String.valueOf(countNum));
                    getDialog().dismiss();
                } else {
                    if (goodsSalesModel != null) {
                        int id = 0;
                        if (map.size() == chooseGoodsAdapter.getItemCount()) {
                            String ids = "";
                            for (int i = 0; i < map.size(); i++) {
                                ids += map.get(i) + "_";
                            }
                            for (int i = 0; i < goodsSalesModel.getPrice().size(); i++) {
                                if (ids.contains(goodsSalesModel.getPrice().get(i).getSpecs())) {
                                    id = Integer.valueOf(goodsSalesModel.getPrice().get(i).getId());
                                    sum = Integer.valueOf(goodsSalesModel.getPrice().get(i).getStock());
                                }
                            }
                            Log.v("规格1map.size", map.size() + "，" + ids);
                            Log.v("规格2id", id + "");
                            if (id != 0) {
//                            SharedPreferencesUtils.put(getActivity(), "sales", sales);
//                            SharedPreferencesUtils.put(getActivity(), "goodsId", goodsId);
//                            SharedPreferencesUtils.put(getActivity(), "countNum", countNum);
//                            SharedPreferencesUtils.put(getActivity(), "id", id);

                                if (sum < Integer.parseInt(etGoodNum.getText().toString().trim())) {
                                    etGoodNum.setText("1");
                                }
                                getDialog().dismiss();
                                if ("1".equals(keys)) {
                                    //ToastUtils.showToast(getActivity(), "加入购物车成功");
                                    //getDialog().dismiss();
                                    addCrat();
                                    //添加购物车
                                } else if ("2".equals(keys)) {
                                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", goodDetailModel.getDetail_tab().getId());
                                    bundle.putString("optionid", goodsId);
                                    bundle.putString("total", String.valueOf(countNum));
                                    bundle.putString("indentFlag", "1");
                                    intent.putExtras(bundle);
                                    getActivity().startActivity(intent);
                                }
                            } else {
                                Toast.makeText(getActivity(), "请重新选择规格!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.v("规格", "请选择商品规格");
                            Toast.makeText(getActivity(), "请选择商品规格!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        getDialog().dismiss();
                        if ("1".equals(keys)) {
                            //ToastUtils.showToast(getActivity(), "加入购物车成功");
                            //getDialog().dismiss();
                            addCrat();
                            //添加购物车
                        } else if ("2".equals(keys)) {
                            Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id", goodDetailModel.getDetail_tab().getId());
                            bundle.putString("optionid", goodsId);
                            bundle.putString("total", String.valueOf(countNum));
                            bundle.putString("indentFlag", "1");
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
                        }
                    }
                }
            }
        });

    }

    private void addCrat() {
        loaddingDialog.show();
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        LogUtils.d("goodsId-->" + goodsId);
        LogUtils.d("id-->" + goodDetailModel.getDetail_tab().getId());
        LogUtils.d("countNum-->" + countNum);
        RetrofitUtil.getInstance().addcart(token, signature, newtime, random, goodDetailModel.getDetail_tab().getId(), String.valueOf(countNum), goodsId, new Subscriber<BaseResponse<EmptyEntity>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                loaddingDialog.dismiss();
                if (baseResponse.code == 200) {
                    ToastUtils.showToast(getActivity(), "加入购物车成功");
                    getDialog().dismiss();
                } else {
                    ToastUtils.showToast(getActivity(), baseResponse.getMsg());
                }
            }
        });
    }


}