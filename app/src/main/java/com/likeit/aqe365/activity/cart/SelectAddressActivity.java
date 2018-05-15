package com.likeit.aqe365.activity.cart;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectAddressActivity extends BaseActivity {

    @BindView(R.id.lv_address)
    ListView mLvAddress;
    @BindView(R.id.tv_add_address)
    TextView mTvAddAddress;
    private AddressAdapter mAddressAdapter;
    private ArrayList<AddressBean> mAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        initUI();
        initData();
    }

    private void initData() {
        GeneratedData();
        mLvAddress.setAdapter(mAddressAdapter = new AddressAdapter(mContext, mAddresses));
    }

    private void GeneratedData() {
        mAddresses = new ArrayList<>();
        mAddresses.add(new AddressBean("1", "王晓萌", "18710990897", "1", "广东省广州市XXX"));
        mAddresses.add(new AddressBean("2", "王二狗", "18710990896", "0", "广东省广州市XXX"));
        mAddresses.add(new AddressBean("3", "小飞飞", "18710990895", "0", "广东省广州市XXX"));
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
                Bundle bundle=new Bundle();
                bundle.putInt("flag",0);
                toActivity(EditAddressActivity.class,bundle);
                break;
        }
    }

    class AddressAdapter extends BaseAdapter {
        private Context mContext;
        private List<AddressBean> addresses;

        public AddressAdapter(Context mContext, List<AddressBean> mAddresses) {
            this.mContext = mContext;
            this.addresses = mAddresses;
            sortData();
        }

        /**
         * 排序
         */
        private void sortData() {
            //对list进行排序，优先级 是否是默认助理、id
            Collections.sort(addresses, new Comparator<AddressBean>() {

                @Override
                public int compare(AddressBean lhs, AddressBean rhs) {
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
            viewHolder.tvAddress.setText(addresses.get(position).getAddress());
            viewHolder.tvName.setText(addresses.get(position).getName());
            viewHolder.tvPhone.setText(addresses.get(position).getMobilePhone());
            viewHolder.cbSelected.setChecked(addresses.get(position).getDefaultFlag() == "1");
            if (addresses.size() > 0) {
                if (position == 0) {
                    viewHolder.cbSelected.setClickable(false);
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
                    AddressAdapter.this.notifyDataSetChanged();
                }
            });
            viewHolder.ivEditAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Bundle bundle=new Bundle();
//                    bundle.putString("name",addresses.get(position).getName());
//                    bundle.putString("phone",addresses.get(position).getMobilePhone());
//                    bundle.putString("name",addresses.get(position).getName());
                    Bundle bundle=new Bundle();
                    bundle.putInt("flag",1);
                    toActivity(EditAddressActivity.class,bundle);
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

    class ViewHolder {
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPhone;
        private CheckBox cbSelected;
        private ImageView ivEditAddress;
    }


}
