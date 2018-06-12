package com.likeit.aqe365.activity.people.activity;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.people.bean.GoodAttentionBean;
import com.likeit.aqe365.activity.people.bean.ShopAttentionBean;
import com.likeit.aqe365.base.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopAttentionActivity extends BaseActivity {

    @BindView(R.id.back_view)
    LinearLayout mBackView;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.toolbar_righ_tv)
    TextView mToolbarRighTv;
    @BindView(R.id.right_view)
    LinearLayout mRightView;
    @BindView(R.id.titleBar)
    RelativeLayout mTitleBar;
    @BindView(R.id.lv_data)
    ListView lv_data;
    @BindView(R.id.che_all)
    CheckBox che_all;
    @BindView(R.id.btn_delete)
    Button btn_delete;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    //定义控件
    //声明一个集合（数据源）
    private List<ShopAttentionBean> data;
    //定义自定义适配器
    private ShopAttentionAdapter mAdapter;
    boolean flag;
    private ArrayList<CheckBox> checkBoxList;

    //给数据源添加数据
    private void initdata() {
        data = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            data.add(new ShopAttentionBean("XXX旗舰店" + i, "http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg", "" + i, false));
        }
    }

    //返回数据给MyAdapter使用
    public List<ShopAttentionBean> getData() {
        return this.data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_attention);
        //初始化数据源
        initdata();
        //实例化自定义适配器，把listview传到自定义适配器中
        mAdapter = new ShopAttentionAdapter(this, lv_data);
        checkBoxList = new ArrayList<CheckBox>();
        //绑定适配器
        lv_data.setAdapter(mAdapter);
        //初始化事件监听
        initUI();
        initlistener();
    }

    private void initUI() {
        mTitle.setText("商品关注");
        mToolbarRighTv.setText("编辑");
        mRlBottom.setVisibility(View.GONE);
    }

    private void initlistener() {
        /**
         * 全选复选框设置事件监听
         */
        che_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (data.size() != 0) {//判断列表中是否有数据
                    if (isChecked) {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(true);
                        }
                        //通知适配器更新UI
                        mAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(false);
                        }
                        //通知适配器更新UI
                        mAdapter.notifyDataSetChanged();
                    }
                } else {//若列表中没有数据则隐藏全选复选框
                    che_all.setVisibility(View.GONE);
                }
            }
        });
        //删除按钮点击事件
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个要删除内容的集合，不能直接在数据源data集合中直接进行操作，否则会报异常
                List<ShopAttentionBean> deleSelect = new ArrayList<ShopAttentionBean>();

                //把选中的条目要删除的条目放在deleSelect这个集合中
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getChecked()) {
                        deleSelect.add(data.get(i));
                    }
                }
                //判断用户是否选中要删除的数据及是否有数据
                if (deleSelect.size() != 0 && data.size() != 0) {
                    //从数据源data中删除数据
                    data.removeAll(deleSelect);
                    //把deleSelect集合中的数据清空
                    deleSelect.clear();
                    //把全选复选框设置为false
                    che_all.setChecked(false);
                    //通知适配器更新UI
                    mAdapter.notifyDataSetChanged();
                } else if (data.size() == 0) {
                    Toast.makeText(ShopAttentionActivity.this, "没有要删除的数据", Toast.LENGTH_SHORT).show();
                } else if (deleSelect.size() == 0) {
                    Toast.makeText(ShopAttentionActivity.this, "请选中要删除的数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.back_view, R.id.right_view})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_view:
                onBackPressed();
                break;
            case R.id.right_view:
                flag = !flag;
                if (flag) {
                    mToolbarRighTv.setText("完成");
                    mRlBottom.setVisibility(View.VISIBLE);
                    //shoppingCartAdapter.isShow(false);
                    for (int i = 0; i < checkBoxList.size(); i++) {
                        checkBoxList.get(i).setVisibility(View.VISIBLE);
                    }
                } else {
                    mToolbarRighTv.setText("编辑");
                    mRlBottom.setVisibility(View.GONE);
                    for (int i = 0; i < checkBoxList.size(); i++) {
                        checkBoxList.get(i).setVisibility(View.GONE);
                    }
                }

                break;
        }
    }

    class ShopAttentionAdapter extends BaseAdapter {
        private ListView lv_data;
        //定义一个数据源的引用
        private List<ShopAttentionBean> data;
        private Context context;

        public ShopAttentionAdapter(Context context, ListView lv_data) {
            if (context instanceof ShopAttentionActivity) {
                this.context = context;
                this.lv_data = lv_data;
                data = ((ShopAttentionActivity) this.context).getData();
            }
        }

        /**
         * 获取当前子view的id（就是listview中的每一个条目的位置）
         *
         * @param position
         * @return 返回当前id
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 获取当前子view对应的值
         *
         * @param position 当前子view（条目）的id（位置）
         * @return 返回当前对应的值 该值为object类型
         */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        /**
         * 定义coverView的Recyler(缓存)，该类名自定义的
         */
        class ViewHodler {
            ImageView ivPicture;
            TextView tvName;
            TextView tvNumber;
            CheckBox ch_delete;
        }

        /**
         * 核心代码
         *
         * @param position    当前子view的id
         * @param convertView 缓存布局（该view与子view保持一致）
         * @param parent      父容器（即当前listview）
         * @return 返回当前子view（包含布局及具体的数据）
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //布局生成器(抽象类)
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            //声明缓存
            ViewHodler viewHodler = null;
            //重新创建布局及缓存
            if (convertView == null) {
                //创建缓存布局
                convertView = layoutInflater.inflate(R.layout.layout_shopattention_items, parent, false);
                //产生缓存
                viewHodler = new ViewHodler();
                viewHodler.ivPicture = (ImageView) convertView.findViewById(R.id.tv_goods_picture);
                viewHodler.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                viewHodler.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
                viewHodler.ch_delete = (CheckBox) convertView.findViewById(R.id.ch_delete);
                //把缓存的布局放在converview中，避免重复获取布局，提升效率
                checkBoxList.add(viewHodler.ch_delete);
                convertView.setTag(viewHodler);
            } else {
                //使用缓存的中的布局
                viewHodler = (ViewHodler) convertView.getTag();
            }
            //为缓存的布局ViewHodler控件设置新的数据
            final ShopAttentionBean currItem = data.get(position);
            ImageLoader.getInstance().displayImage(currItem.getUrl(), viewHodler.ivPicture);
            viewHodler.tvName.setText(currItem.getName());
            viewHodler.tvNumber.setText("共" + currItem.getNumber() + "件商品");
            viewHodler.ch_delete.setChecked(currItem.getChecked());
            viewHodler.ch_delete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //   currItem.setChoosed(((CheckBox) v).isChecked());
                            currItem.setChecked(((CheckBox) v).isChecked());
                        }
                    }
            );
            //listView单个条目事件监听
            lv_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    data.get(position).setChecked(true);
                    notifyDataSetChanged();
                }
            });
            //返回多次生成的子View给适配器
            return convertView;
        }

        /**
         * 获取数据中要在listview中显示的条目
         *
         * @return 返回数据的条目
         */
        @Override
        public int getCount() {
            return this.data != null ? this.data.size() : 0;
        }
    }
}
