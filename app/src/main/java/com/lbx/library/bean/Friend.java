package com.lbx.library.bean;

import android.databinding.ObservableField;
import android.text.TextUtils;

import java.io.Serializable;

import lbx.xtoollib.phone.xLogUtil;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2019/2/18.
 */

public class Friend implements Serializable, Cloneable {

    private ObservableField<String> id = new ObservableField<>();
    private ObservableField<String> name = new ObservableField<>();

    public Friend() {
    }

    public Friend(String id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public Friend clone() throws CloneNotSupportedException {
        Friend friend = (Friend) super.clone();
        friend.setId(this.id.get());
        friend.setName(this.name.get());
        return friend;
    }

    public boolean checkAddFriend() {
        return !TextUtils.isEmpty(id.get()) && !TextUtils.isEmpty(name.get());
    }

    public String getItemListInfo() {
        xLogUtil.e("getItemListInfo:" + name.get() + " " + id.get());
        return name.get() + " " + id.get();
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Friend friend = (Friend) o;
        return id.get().equals(friend.id.get());

    }

    @Override
    public int hashCode() {
        return id.get().hashCode();
    }
}
