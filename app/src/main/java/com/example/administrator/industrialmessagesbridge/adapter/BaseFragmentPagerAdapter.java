package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import java.util.List;

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;
    /**下面两个值用来保存Fragment的位置信息，用以判断该位置是否需要更新*/
    private SparseArray<String> mFragmentPositionMapAfterUpdate;
    private SparseArray<String> mFragmentPositionMap;
    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragmentList = fragments;
        mFragmentManager = fm;
        mFragmentList = fragments;
        mFragmentPositionMap = new SparseArray<>();
        mFragmentPositionMapAfterUpdate = new SparseArray<>();
        setFragmentPositionMap();
        setFragmentPositionMapForUpdate();
    }
    //   * 保存更新之前的位置信息，用<hashCode, position>的键值对结构来保存
    private void setFragmentPositionMap() {
        mFragmentPositionMap.clear();
        for (int i = 0; i < mFragmentList.size(); i++) {
            mFragmentPositionMap.put(Long.valueOf(getItemId(i)).intValue(), String.valueOf(i));
        }
    }
    //*保存更新之后的位置信息，用<hashCode, position>的键值对结构来保存
    private void setFragmentPositionMapForUpdate() {
        mFragmentPositionMapAfterUpdate.clear();
        for (int i = 0; i < mFragmentList.size(); i++) {
            mFragmentPositionMapAfterUpdate.put(Long.valueOf(getItemId(i)).intValue(),  String.valueOf(i));
        }
    }
    //* 在此方法中找到需要更新的位置返回POSITION_NONE，否则返回POSITION_UNCHANGED即可
    @Override
    public int getItemPosition(Object object) {
        int hashCode = object.hashCode();
                 //查找object在更新后的列表中的位置
                 String position = mFragmentPositionMapAfterUpdate.get(hashCode);
                 //更新后的列表中不存在该object的位置了
                 if (position == null) {
                         return POSITION_NONE;
                     } else {
                         //如果更新后的列表中存在该object的位置, 查找该object之前的位置并判断位置是否发生了变化
                         int size = mFragmentPositionMap.size();
                         for (int i = 0; i < size ; i++) {
                                 int key = mFragmentPositionMap.keyAt(i);
                                 if (key == hashCode) {
                                         String index = mFragmentPositionMap.get(key);
                                         if (position.equals(index)) {
                                                 //位置没变依然返回POSITION_UNCHANGED
                                                 return POSITION_UNCHANGED;
                                             } else {
                                                 //位置变了
                                                 return POSITION_NONE;
                                             }
                                     }
                            }
                    }
                 return POSITION_UNCHANGED;
    }
    /**
     79      * 将指定的Fragment替换/更新为新的Fragment
     80      * @param oldFragment 旧Fragment
     81      * @param newFragment 新Fragment
     82      */
      public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
                 int position = mFragmentList.indexOf(oldFragment);
                 if (position == -1) {
                         return;
                     }
                 //从Transaction移除旧的Fragment
                 removeFragmentInternal(oldFragment);
                 //替换List中对应的Fragment
                 mFragmentList.set(position, newFragment);
                 //刷新Adapter
                 notifyItemChanged();
             }
    /**
     97      * 将指定位置的Fragment替换/更新为新的Fragment，同{@link #replaceFragment(BaseFragment oldFragment, BaseFragment newFragment)}
     98      * @param position    旧Fragment的位置
     99      * @param newFragment 新Fragment
     100      */
     public void replaceFragment(int position, Fragment newFragment) {
            Fragment oldFragment = mFragmentList.get(position);
                 removeFragmentInternal(oldFragment);
                 mFragmentList.set(position, newFragment);
                 notifyItemChanged();
             }

             /**
 109      * 移除指定的Fragment
 110      * @param fragment 目标Fragment
 111      */
             public void removeFragment(Fragment fragment) {
                 //先从List中移除
                 mFragmentList.remove(fragment);
                 //然后从Transaction移除
                 removeFragmentInternal(fragment);
                 //最后刷新Adapter
                 notifyItemChanged();
             }

             /**
 122      * 移除指定位置的Fragment，同 {@link #removeFragment(BaseFragment fragment)}
 123      * @param position
 124      */
             public void removeFragment(int position) {
                 Fragment fragment = mFragmentList.get(position);
                 //然后从List中移除
                 mFragmentList.remove(fragment);
                 //先从Transaction移除
                 removeFragmentInternal(fragment);
                 //最后刷新Adapter
                 notifyItemChanged();
             }

             /**
 136      * 添加Fragment
 137      * @param fragment 目标Fragment
 138      */
             public void addFragment(Fragment fragment) {
                 mFragmentList.add(fragment);
                 notifyItemChanged();
             }

             /**
 145      * 在指定位置插入一个Fragment
 146      * @param position 插入位置
 147      * @param fragment 目标Fragment
 148      */
             public void insertFragment(int position, Fragment fragment) {
                 mFragmentList.add(position, fragment);
                 notifyItemChanged();
             }

             private void notifyItemChanged() {
                 //刷新之前重新收集位置信息
                 setFragmentPositionMapForUpdate();
                 notifyDataSetChanged();
                 setFragmentPositionMap();
             }

             /**
 162      * 从Transaction移除Fragment
 163      * @param fragment 目标Fragment
 164      */
             private void removeFragmentInternal(Fragment fragment) {
                 FragmentTransaction transaction = mFragmentManager.beginTransaction();
                 transaction.remove(fragment);
                 transaction.commitNow();
             }
             /**
       * 此方法不用position做返回值即可破解fragment tag异常的错误
       */
             @Override
     public long getItemId(int position) {
                 // 获取当前数据的hashCode，其实这里不用hashCode用自定义的可以关联当前Item对象的唯一值也可以，只要不是直接返回position
                 return mFragmentList.get(position).hashCode();
             }

             @Override
             public Fragment getItem(int position) {
                 return mFragmentList.get(position);
             }

               @Override
             public int getCount() {
                 return mFragmentList.size();
             }

             public List<Fragment> getFragments() {
                 return mFragmentList;
             }
}
