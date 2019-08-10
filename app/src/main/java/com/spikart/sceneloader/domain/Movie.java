package com.spikart.sceneloader.domain;

import androidx.databinding.BaseObservable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName="movies")
public class Movie extends BaseObservable {

    @PrimaryKey() @ColumnInfo(name="id") @SerializedName(value="id")
    private Integer id;


}
