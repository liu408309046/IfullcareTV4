package com.kunyuanzhihui.ifullcaretv.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.adapter.StepViewpagerAdapter;
import com.kunyuanzhihui.ifullcaretv.adapter.UserListAdapter;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.bean.NewCharacter;
import com.kunyuanzhihui.ifullcaretv.fragment.BaseFragment;
import com.kunyuanzhihui.ifullcaretv.widget.MyHorizontalScrollView;
import com.kunyuanzhihui.ifullcaretv.widget.NoScrollViewPager;
import com.kunyuanzhihui.ifullcaretv.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/15- 14:58.
 */
public class AddCharacterActivity extends BaseActivity {

    private Button btn_previous_step, btn_next_step;
    private LinearLayout viewpoint;
    private List<View> viewList;
    private ImageView img_home,img_head;
    private NoScrollViewPager viewPager;
    private ImageView[] imageViews;
    private int cur_position;
    private View step1, step2, step3, step4, step5;
    private NewCharacter newCharacter;
    private boolean ischeck_step1, isInput_step2, isInput_step3, isInput_step4, isInput_step5;
    private  EditText et_name,et_nick,et_height,et_weight,et_age,et_phone;
    private TextView tv_name,tv_nick,tv_height,tv_weight,tv_age,tv_phone,tv_mark;

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_add_character;
    }

    @Override
    protected void init() {
        newCharacter = new NewCharacter();
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront();
                    mainUpView.setFocusView(newFocus, oldFocusView, 1.1f);
                }
                oldFocusView = newFocus;
            }
        });

        initViewPager();
        btn_previous_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cur_position > 0) {
                    viewPager.setCurrentItem(cur_position - 1);
                } else {

                }
            }
        });
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cur_position < viewList.size() - 1) {

                }
                switch (cur_position) {
                    case 0:
                        if (ischeck_step1) {
                            viewPager.setCurrentItem(cur_position + 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "请选择标签", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("---new character---",newCharacter.getMark()+newCharacter.getHead_res());
                        break;
                    case 1:

                        String name=et_name.getText().toString();
                        String nick=et_nick.getText().toString();

                        if (name!= null &&!name.equals("")&& nick!= null&&!nick.equals("")) {
                            newCharacter.setName(et_name.getText().toString());
                            newCharacter.setNick(et_name.getText().toString());
                            isInput_step2 = true;
                        }

                        if (isInput_step2) {
                            viewPager.setCurrentItem(cur_position + 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "请输入姓名和昵称", Toast.LENGTH_SHORT).show();
                        }


                        break;
                    case 2:

                        String height=et_height.getText().toString();
                        String weight=et_weight.getText().toString();
                        String age=et_age.getText().toString();

                        if (height!= null &&!height.equals("")&& weight!= null&&!weight.equals("")&& age!= null&&!age.equals("")) {
                            newCharacter.setHeight(height);
                            newCharacter.setWeight(weight);
                            newCharacter.setAge(age);
                            isInput_step3 = true;
                        }

                        if (isInput_step3) {
                            viewPager.setCurrentItem(cur_position + 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "请输入身高、体重和年龄", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        String phone=et_phone.getText().toString();

                        if(phone!=null&&!phone.equals("")){
                            newCharacter.setPhone_num(phone);
                            isInput_step4=true;
                        }
                        if (isInput_step4) {
                            viewPager.setCurrentItem(cur_position + 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
                        }
                        img_head.setBackgroundResource(newCharacter.getHead_res());
                        tv_age.setText("年龄："+newCharacter.getAge()+"岁");
                        tv_weight.setText("体重："+newCharacter.getWeight()+"公斤");
                        tv_nick.setText(newCharacter.getNick());
                        tv_name.setText("姓名："+newCharacter.getName());
                        tv_height.setText("身高："+newCharacter.getHeight()+"cm");
                        tv_phone.setText("手机号码："+newCharacter.getPhone_num());
                        tv_mark.setText(newCharacter.getMark());
                        break;
                    case 4:
                        if (isInput_step5) {

                        }
                        break;
                }
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    private void initViewPager() {
        LayoutInflater inflater = getLayoutInflater();
        viewList = new ArrayList<View>();

        step1 = inflater.inflate(R.layout.step1, null);
        step2 = inflater.inflate(R.layout.step2, null);
        step3 = inflater.inflate(R.layout.step3, null);
        step4 = inflater.inflate(R.layout.step4, null);
        step5 = inflater.inflate(R.layout.step5, null);

        viewList.add(step1);
        viewList.add(step2);
        viewList.add(step3);
        viewList.add(step4);
        viewList.add(step5);
        creatpoints(viewList);
        load_step1(step1);
        load_step2(step2);
        load_step3(step3);
        load_step4(step4);
        load_step5(step5);

        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new StepViewpagerAdapter(getApplicationContext(), viewList));
        viewPager.setFocusable(false);
        viewPager.setScrollble(false);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                cur_position = position;

                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[position].setBackgroundResource(R.drawable.common_circle_orange);
                    //不是当前选中的page，其小圆点设置为未选中的状态
                    if (position != i) {
                        imageViews[i].setBackgroundResource(R.drawable.common_circle_gray);
                    }
                }
                if(cur_position==viewList.size()-1){
                    btn_next_step.setText("提交");
                    btn_previous_step.setText("修改");
                }else {
                    btn_next_step.setText("下一步");
                    btn_previous_step.setText("上一步");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }


    private void creatpoints(List<View> viewList) {
        imageViews = new ImageView[this.viewList.size()];
        for (int i = 0; i < this.viewList.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(20, 20));//创建一个宽高均为20 的布局
            imageView.setPadding(30, 30, 30, 30);

            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.common_circle_orange);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.common_circle_gray);
            }
            viewpoint.addView(imageViews[i]);
        }
    }


    private void load_step1(View step1) {
        final RecyclerView recyclerView = (RecyclerView) step1.findViewById(R.id.identity_list);
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 4);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30, 0, 30, 0));
        recyclerView.setFocusable(false);

        final List<GalleryBean> user_list = new ArrayList<>();
        user_list.add(new GalleryBean(R.mipmap.pic_4, "外公"));
        user_list.add(new GalleryBean(R.mipmap.pic_2, "外婆"));
        user_list.add(new GalleryBean(R.mipmap.pic_1, "爸爸"));
        user_list.add(new GalleryBean(R.mipmap.pic_3, "妈妈"));
        user_list.add(new GalleryBean(R.mipmap.pic_1, "大儿子"));
        user_list.add(new GalleryBean(R.mipmap.pic_5, "小儿子"));
        user_list.add(new GalleryBean(R.mipmap.pic_2, "大女儿"));
        user_list.add(new GalleryBean(R.mipmap.pic_6, "小女儿"));
        user_list.add(new GalleryBean(R.mipmap.pic_4, "外公"));
        user_list.add(new GalleryBean(R.mipmap.pic_2, "外婆"));
        user_list.add(new GalleryBean(R.mipmap.pic_1, "叔叔"));
        user_list.add(new GalleryBean(R.mipmap.pic_3, "婶婶"));
        final UserListAdapter adapter = new UserListAdapter(getApplicationContext(), user_list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemSelectListener(new UserListAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {

            }
        });
        adapter.setOnItemClickListener(new UserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for (int i = 0; i < user_list.size(); i++) {
                    recyclerView.getChildAt(i).setBackgroundResource(R.mipmap.btn_bg);
                }
                view.setBackgroundResource(R.mipmap.btn_bg_click);
                ischeck_step1 = true;
                newCharacter.setHead_res(user_list.get(position).getHeadRes());
                newCharacter.setMark(user_list.get(position).getHeadText());
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }


    private void load_step2(View step2) {
         et_name = (EditText) step2.findViewById(R.id.et_name);
        et_nick = (EditText) step2.findViewById(R.id.et_nick);
    }

    private void load_step3(View step3) {
        et_height = (EditText) step3.findViewById(R.id.et_height);
         et_weight = (EditText) step3.findViewById(R.id.et_weight);
         et_age = (EditText) step3.findViewById(R.id.et_age);
    }

    private void load_step4(View step4) {
        et_phone= (EditText) step4.findViewById(R.id.et_phone);

    }

    private void load_step5(View step5) {
            img_head= (ImageView) step5.findViewById(R.id.iv_head);
            tv_age= (TextView) step5.findViewById(R.id.tv_age);
            tv_name= (TextView) step5.findViewById(R.id.tv_name);
            tv_nick= (TextView) step5.findViewById(R.id.tv_nick);
            tv_mark= (TextView) step5.findViewById(R.id.tv_mark);
            tv_height= (TextView) step5.findViewById(R.id.tv_height);
            tv_weight= (TextView) step5.findViewById(R.id.tv_weight);
            tv_phone= (TextView) step5.findViewById(R.id.tv_phone);
    }

}
