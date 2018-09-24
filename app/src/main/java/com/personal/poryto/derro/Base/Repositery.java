package com.personal.poryto.derro.Base;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Pory Sovann on 9/22/2018.
 */
public interface Repositery<T> {
    LiveData<T> get(int id);
    LiveData<List<T>> getAll();
}
