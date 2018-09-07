package com.likeit.aqe365.activity.cart.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.AddressBean;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.model.goods.AddressModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class SelectAddressActivity extends BaseActivity {

    @BindView(R.id.lv_address)
    ListView mLvAddress;
    @BindView(R.id.tv_add_address)
    TextView mTvAddAddress;
    private AddressAdapter mAddressAdapter;
    private List<AddressModel.ListBean> mAddresses = null;
    private String province, city, area, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        mAddresses = new ArrayList<>();
        mAddressAdapter = new AddressAdapter(mContext, mAddresses);
        initData();
        initUI();
    }


    private void initData() {
        loaddingDialog.show();
        String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().getAddressList(token, signature, newtime, random, new Subscriber<BaseResponse<AddressModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<AddressModel> baseResponse) {
                loaddingDialog.dismiss();
                LogUtils.d("data-->" + baseResponse.getData());
                if (baseResponse.code == 200) {
                    AddressModel mAddressesModel = baseResponse.getData();
                    List<AddressModel.ListBean> listBeans = mAddressesModel.getList();
                    // mAddresses = listBeans;
                    mAddresses.addAll(listBeans);
                } else {
                    showProgress(baseResponse.getMsg());
                }
                //mAddressAdapter = new AddressAdapter(mContext, mAddresses);
                mLvAddress.setAdapter(mAddressAdapter);
                mAddressAdapter.notifyDataSetChanged();
            }
        });
    }


    private void initUI() {
        setBackView();
        setTitle("选择地址");
    }

    @OnClick(R.id.tv_add_address)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_add_address:
                Intent intent = new Intent(this, EditAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("flag", 0);
                bundle.putInt("position", 0);
                bundle.putString("id", "");
                bundle.putString("address", "");
                bundle.putString("realname", "");
                bundle.putString("mobile", "");
                bundle.putString("province", "");
                bundle.putString("city", "");
                bundle.putString("area", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1000);
                // toActivity(EditAddressActivity.class, bundle);

                break;
        }
    }

    class AddressAdapter extends BaseAdapter {
        private Context mContext;
        private List<AddressModel.ListBean> addresses;

        public AddressAdapter(Context mContext, List<AddressModel.ListBean> mAddresses) {
            this.mContext = mContext;
            this.addresses = mAddresses;
            sortData();
        }

        /**
         * 排序
         */
        private void sortData() {
            //对list进行排序，优先级 是否是默认助理、id
            Collections.sort(addresses, new Comparator<AddressModel.ListBean>() {

                @Override
                public int compare(AddressModel.ListBean lhs, AddressModel.ListBean rhs) {
                    if (lhs.getDefaultFlag().compareToIgnoreCase(rhs.getDefaultFlag()) < 0) {
                        return 1;
                    } else if (lhs.getDefaultFlag().compareToIgnoreCase(rhs.getDefaultFlag()) == 0) {
                        return lhs.getId().compareToIgnoreCase(rhs.getId());
                    } else {
                        return -1;
                    }
                }
            });
        }

        @Override
        public int getCount() {
            return addresses.size();
        }

        @Override
        public Object getItem(int position) {
            return addresses.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * @param position
         * @param convertView
         * @param parent
         * @return
         */


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                //--
                convertView = View.inflate(mContext, R.layout.layout_address_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tvAddress = convertView.findViewById(R.id.tv_address);
                viewHolder.tvName = convertView.findViewById(R.id.tv_name);
                viewHolder.tvPhone = convertView.findViewById(R.id.tv_phone);
                viewHolder.cbSelected = convertView.findViewById(R.id.cb_selected);
                viewHolder.ivEditAddress = convertView.findViewById(R.id.iv_edit_address);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //  final AddressModel.ListBean addressModel = (AddressModel.ListBean) getItem(position);
            province = addresses.get(position).getProvince();
            city = addresses.get(position).getCity();
            area = addresses.get(position).getArea();
            address = addresses.get(position).getAddress();
            viewHolder.tvAddress.setText(province + city + area + address);
            viewHolder.tvName.setText(addresses.get(position).getRealname());
            viewHolder.tvPhone.setText(addresses.get(position).getMobile());
            viewHolder.cbSelected.setChecked(addresses.get(position).getDefaultFlag() == "1");
            if (addresses.size() > 0) {
                if (position == 0) {
                    viewHolder.cbSelected.setClickable(false);
                    viewHolder.cbSelected.setChecked(true);
                }
            } else {
                return null;
            }
            viewHolder.cbSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (viewHolder.cbSelected.isChecked()) {
                        if (addresses.get(0).getDefaultFlag() == "1") {//之前第一个助理是默认助理
                            addresses.get(0).setDefaultFlag("0");
                        }
                        addresses.get(position).setDefaultFlag("1");
                    } else {
                        addresses.get(position).setDefaultFlag("0");
                    }
                    String id = addresses.get(position).getId();
                    setDefaultAddress(id);
                    AddressAdapter.this.notifyDataSetChanged();
                }
            });
            viewHolder.ivEditAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectAddressActivity.this, EditAddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("flag", 1);
                    bundle.putString("id", addresses.get(position).getId());
                    bundle.putInt("position", position);
                    bundle.putString("address", addresses.get(position).getAddress());
                    bundle.putString("realname", addresses.get(position).getRealname());
                    bundle.putString("mobile", addresses.get(position).getMobile());
                    bundle.putString("province", addresses.get(position).getProvince());
                    bundle.putString("city", addresses.get(position).getCity());
                    bundle.putString("area", addresses.get(position).getArea());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 1000);
                }
            });
            return convertView;

        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            sortData();
        }
    }

    private void setDefaultAddress(String id) {
        final String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().setDefaultAddress(token, signature, newtime, random, id, new Subscriber<BaseResponse<EmptyEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                if (baseResponse.code == 200) {
                    showToast(baseResponse.getMsg());
                } else {
                    showToast(baseResponse.getMsg());
                }
            }
        });
    }

    class ViewHolder {
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPhone;
        private CheckBox cbSelected;
        private ImageView ivEditAddress;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            String result_value = data.getStringExtra("result");
            int position = data.getIntExtra("position", 0);
            if ("1".equals(result_value)) {
                if (mAddresses != null) {
                    mAddresses.clear();
                }
                initData();
            } else if ("2".equals(result_value)) {
                mAddresses.remove(position);
                mAddressAdapter.notifyDataSetChanged();
            }
        }
    }
}
