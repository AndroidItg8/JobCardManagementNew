package com.itg.jobcardmanagement.common;

import java.lang.ref.WeakReference;

/**
 * Created by swapnilmeshram on 28/07/17.
 */

public class BaseWeakPresenter<T> {
    private WeakReference<T> weakReference;

    public BaseWeakPresenter(T t) {
        this.weakReference=new WeakReference<T>(t);
    }

    public boolean hasView(){
        return weakReference != null;
    }

    public T getView(){
        return weakReference.get();
    }


    protected void detactView(){
        weakReference=null;
    }

}
