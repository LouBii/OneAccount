package com.loubii.account.ui.avtivity;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
//
//    @BindView(R.id.lin_content)
//    LinearLayout mLinContent;
//    @BindView(R.id.bt_nav_bar)
//    BottomNavigationBar mBtNavBar;
////    @BindView(R.id.iv_add)
////    ImageView mIvAdd;
//
//    private FragmentBill mFragmentBill;
//    private FragmentChart mFragmentChart;
//    private FragmentAdd mFragmentAdd;
//    private FragmentCard mFragmentCard;
//    private FragmentCenter mFragmentCenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        initBottomNavigationBar();
//    }
//
//    private void initBottomNavigationBar() {
//        mBtNavBar.setMode(BottomNavigationBar.MODE_FIXED);
//        mBtNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        mBtNavBar.addItem(new BottomNavigationItem(R.mipmap.home_icon_day, R.string.tab_name_bill))
//                .addItem(new BottomNavigationItem(R.mipmap.home_icon_fm, R.string.tab_name_chart))
//                .addItem(new BottomNavigationItem(R.mipmap.home_icon_fm, R.string.tab_name_add))
//                .addItem(new BottomNavigationItem(R.mipmap.home_icon_rank, R.string.tab_name_card))
//                .addItem(new BottomNavigationItem(R.mipmap.home_icon_rank, R.string.tab_name_me))//依次添加item,分别icon和名称
//                .setFirstSelectedPosition(0)//设置默认选择item
//                .initialise();//初始化
//        mBtNavBar.setTabSelectedListener(this);
//        setDefaultFragment();
//    }
//
//    /**
//     * 设置默认的
//     */
//    private void setDefaultFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        mFragmentBill = FragmentBill.newInstance("First Fragment");
//        transaction.replace(R.id.lin_content, mFragmentBill);
//        transaction.commit();
//    }
//
//    @Override
//    public void onTabSelected(int position) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        switch (position) {
//            case 0:
//                if (mFragmentBill == null) {
//                    mFragmentBill = FragmentBill.newInstance("First Fragment");
//                }
//                transaction.replace(R.id.lin_content, mFragmentBill);
//                break;
//            case 1:
//                if (mFragmentChart == null) {
//                    mFragmentChart = FragmentChart.newInstance("Two Fragment");
//                }
//                transaction.replace(R.id.lin_content, mFragmentChart);
//                break;
//            case 2:
//                if (mFragmentAdd == null) {
//                    mFragmentAdd = FragmentAdd.newInstance("Third Fragment");
//                }
//                transaction.replace(R.id.lin_content, mFragmentAdd);
//                break;
//            case 3:
//                if (mFragmentCard == null) {
//                    mFragmentCard = FragmentCard.newInstance("Four Fragment");
//                }
//                transaction.replace(R.id.lin_content, mFragmentCard);
//                break;
//            case 4:
//                if (mFragmentCenter == null) {
//                    mFragmentCenter = FragmentCenter.newInstance("Five Fragment");
//                }
//                transaction.replace(R.id.lin_content, mFragmentCenter);
//                break;
//            default:
//                if (mFragmentBill == null) {
//                    mFragmentBill = FragmentBill.newInstance("First Fragment");
//                }
//                transaction.replace(R.id.lin_content, mFragmentBill);
//                break;
//        }
//        transaction.commit();
//    }
//
//    @Override
//    public void onTabUnselected(int position) {
//
//    }
//
//    @Override
//    public void onTabReselected(int position) {
//
//    }
}
