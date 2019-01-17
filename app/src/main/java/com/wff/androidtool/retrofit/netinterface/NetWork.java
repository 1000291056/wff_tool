package com.wff.androidtool.retrofit.netinterface;

import com.orhanobut.logger.Logger;
import com.wff.androidtool.retrofit.Bean;
import com.wff.androidtool.retrofit.HttpUtils;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetWork {
    public static void searchBook() {
        Call<Bean> call = HttpUtils.instance().create(Book.class).searchBooks("金瓶梅", null, 0, 1);
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });
    }

    public static void rxSearchBook() {
        Flowable<Bean> flowable = HttpUtils.instance().create(RxBook.class).searchBooks("金瓶梅", null, 0, 1);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<Bean>() {
                               @Override
                               public void onSubscribe(Subscription s) {
                                   s.request(Integer.MAX_VALUE);
                               }

                               @Override
                               public void onNext(Bean bean) {
                                   Logger.d(bean.toString());
                               }

                               @Override
                               public void onError(Throwable t) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           }
//                        new Subscriber<Bean>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(Bean bean) {
//                        Logger.d(bean.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                }
                );
    }
}
