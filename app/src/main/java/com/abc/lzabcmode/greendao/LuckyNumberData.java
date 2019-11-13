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
    public Long id;

    @NotNull
    public String time;

    @NotNull
    public String issueNumber;

    @NotNull
    public String luckyArrays;

    @NotNull
    public int number1;

    @NotNull
    public int number2;

    @NotNull
    public int number3;

    @NotNull
    public int number4;

    @NotNull
    public int number5;

    @NotNull
    public int number6;

    @NotNull
    public int number7;

    @Generated(hash = 1809541758)
    public LuckyNumberData(Long id, @NotNull String time, @NotNull String issueNumber,
            @NotNull String luckyArrays, int number1, int number2, int number3, int number4,
            int number5, int number6, int number7) {
        this.id = id;
        this.time = time;
        this.issueNumber = issueNumber;
        this.luckyArrays = luckyArrays;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
        this.number7 = number7;
    }


    @Generated(hash = 181833389)
    public LuckyNumberData() {
    }

    public List<String> getLuckyList() {
        return Arrays.asList(getLuckyArrays().split(","));
    }


    public String getLuckyArrays(){
        return number1+","+number2+","+number3+","+number4+","+number5+","+number6+","+number7;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getIssueNumber() {
        return this.issueNumber;
    }


    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }


    public void setLuckyArrays(String luckyArrays) {
        this.luckyArrays = luckyArrays;
        if (TextUtils.isEmpty(luckyArrays)){
            return;
        }
        String[] list=luckyArrays.split(",");
        for (int i = 0; i <list.length ; i++) {
            int number= Integer.parseInt(list[i]);
            switch (i){
                case 0:
                    setNumber1(number);
                    break;
                case 1:
                    setNumber2(number);
                    break;
                case 2:
                    setNumber3(number);
                    break;
                case 3:
                    setNumber4(number);
                    break;
                case 4:
                    setNumber5(number);
                    break;
                case 5:
                    setNumber6(number);
                    break;
                case 6:
                    setNumber7(number);
                    break;
            }
        }
    }


    public int getNumber1() {
        return this.number1;
    }


    public void setNumber1(int number1) {
        this.number1 = number1;
    }


    public int getNumber2() {
        return this.number2;
    }


    public void setNumber2(int number2) {
        this.number2 = number2;
    }


    public int getNumber3() {
        return this.number3;
    }


    public void setNumber3(int number3) {
        this.number3 = number3;
    }


    public int getNumber4() {
        return this.number4;
    }


    public void setNumber4(int number4) {
        this.number4 = number4;
    }


    public int getNumber5() {
        return this.number5;
    }


    public void setNumber5(int number5) {
        this.number5 = number5;
    }


    public int getNumber6() {
        return this.number6;
    }


    public void setNumber6(int number6) {
        this.number6 = number6;
    }


    public int getNumber7() {
        return this.number7;
    }


    public void setNumber7(int number7) {
        this.number7 = number7;
    }

}
