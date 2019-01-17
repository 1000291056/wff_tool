package com.wff.androidtool.rxjava;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wff.androidtool.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


public class RxJava2 {
    private static final String TAG = "RxJava2";

    @SuppressLint("CheckResult")
    public void rxjava2() {
        Observable.create(new ObservableOnSubscribe<MessageBean>() {
            @Override
            public void subscribe(ObservableEmitter<MessageBean> observableEmitter) throws Exception {
                observableEmitter.onNext(new MessageBean("1111"));
                observableEmitter.onNext(new MessageBean("2222"));
                observableEmitter.onComplete();
            }
        }).subscribe(new Observer<MessageBean>() {
            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(MessageBean messageBean) {
                Log.d(TAG, messageBean.getMessage());
                if (disposable != null) {//一旦调用 之后哦所有方法不会再被调用
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
        Log.d(TAG, "------------------------------------concat------------------------------------------- ");
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "concat :" + integer.intValue());
            }
        });
        Log.d(TAG, "------------------------------------zip------------------------------------------- ");
        Observable.zip(Observable.just(1, 2, 3), Observable.just("A", "B"), new BiFunction<Integer, String, MessageBean>() {
            @Override
            public MessageBean apply(Integer integer, String s) throws Exception {
                return new MessageBean(integer.intValue(), s);
            }
        }).subscribe(new Consumer<MessageBean>() {
            @Override
            public void accept(MessageBean messageBean) throws Exception {
                Log.d(TAG, "zip :" + messageBean.toString());
            }
        });

        Log.d(TAG, "------------------------------------flatMap------------------------------------------- ");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, "flatMap : accept : " + s + "\n");
            }
        });

        Log.d(TAG, "------------------------------------concatMap------------------------------------------- ");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, "flatMap : accept : " + s + "\n");
            }
        });
        Log.d(TAG, "------------------------------------distinct------------------------------------------- ");
        Observable.just(1, 1, 2, 2, 2, 2, 3, 3, 4).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "distinct : accept : " + integer.toString());
            }
        });

        Log.d(TAG, "------------------------------------filter------------------------------------------- ");
        Observable.just(1, 1, 2, 2, 2, 2, 3, 3, 4).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer.intValue() > 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "filter : accept : " + integer.toString());
            }
        });
        Log.d(TAG, "------------------------------------Single------------------------------------------- ");
        Single.just(1).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d(TAG, "Single : accept : " + integer.toString());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        Log.d(TAG, "------------------------------------merge------------------------------------------- ");
        Observable.merge(Observable.just(1, 2, 3, 4, 5, 6, 7), Observable.just(8, 9, 10)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "concat :" + integer.intValue());
            }
        });
    }

    @SuppressLint("CheckResult")
    public void rxjava1() {
        Flowable.just("hello the world").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d(s);
            }
        });
        Flowable.just(new MessageBean()).map(new Function<MessageBean, String>() {
            @Override
            public String apply(MessageBean messageBean) throws Exception {
                if (messageBean != null) {
                    return messageBean.getMessage();
                }
                return "";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d("map:" + s);
            }
        });
        Flowable.fromArray(new MessageBean()).flatMap(new Function<MessageBean, Flowable<String>>() {
            @Override
            public Flowable<String> apply(MessageBean messageBean) throws Exception {
                String[] msgs = new String[messageBean.getMessage().length()];
                return Flowable.just(messageBean).map(new Function<MessageBean, String>() {
                    @Override
                    public String apply(MessageBean messageBean) throws Exception {
                        return messageBean.getMessage();
                    }
                });
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d("doOnNext:" + s);
            }
        }).doAfterNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d("doAfterNext:" + s);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d("flatMap:" + s);
            }
        });

    }
}
