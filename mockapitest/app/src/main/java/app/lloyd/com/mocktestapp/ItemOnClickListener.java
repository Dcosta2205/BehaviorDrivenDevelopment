package app.lloyd.com.mocktestapp;

import android.view.View;

public interface ItemOnClickListener<T> {

    void onItemClicked(int position, T object, View view);

}
