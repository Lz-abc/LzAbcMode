package com.abc.lzabcmode.greendao;

import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import org.greenrobot.greendao.annotation.Generated;
import org.jetbrains.annotations.NonNls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @name lz
 * @time 2019/11/7 18:14
 */
@Entity()
public class LuckyNumberData {

    @Id(autoincrement = true)
    public long id;

    @NotNull
    public String time;

    @NotNull
    public String IssueNumber;

    @NotNull
    public String luckyArrays;

    public List<String> getLuckyList(){
        if (TextUtils.isEmpty(luckyArrays))return new ArrayList<>();
        return Arrays.asList(luckyArrays.split(","));
    }

    @Generated(hash = 274907660)
    public LuckyNumberData(long id, @NotNull String time,
            @NotNull String IssueNumber, @NotNull String luckyArrays) {
        this.id = id;
        this.time = time;
        this.IssueNumber = IssueNumber;
        this.luckyArrays = luckyArrays;
    }

    @Generated(hash = 181833389)
    public LuckyNumberData() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLuckyArrays() {
        return this.luckyArrays;
    }

    public void setLuckyArrays(String luckyArrays) {
        this.luckyArrays = luckyArrays;
    }

    public String getIssueNumber() {
        return this.IssueNumber;
    }

    public void setIssueNumber(String IssueNumber) {
        this.IssueNumber = IssueNumber;
    }
}
