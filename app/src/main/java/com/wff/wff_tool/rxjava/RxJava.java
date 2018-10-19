package com.wff.wff_tool.rxjava;

import android.content.Context;


import com.orhanobut.logger.Logger;
import com.wff.wff_tool.bean.MessageBean;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action0;
//import rx.functions.Action1;
//import rx.functions.Action3;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;

public class RxJava {
    private Context context;
//    Action0 action0;
//    Action1 action1;
//    Action3 action3;

    public RxJava(Context context) {
        this.context = context;
    }

    public RxJava() {
    }

    public void rxjava() {
        subscriber();
        Observable.just(1,2,3).concatWith(Observable.just(4,5,6)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

            }
        });
//        Observable.from(new String[]{"a", "b"}).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Logger.d("Observable.from:" + s);
//            }
//        });
        MessageBean[] messageBeans = new MessageBean[]{new MessageBean("m_a"), new MessageBean("m_b"), new MessageBean("m_c")};
//        Observable.from(messageBeans).map(new Func1<MessageBean, String>() {
//            @Override
//            public String call(MessageBean messageBean) {
//                return messageBean.getMessage();
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Logger.d("Observable.map:" + s);
//            }
//        });
        Observable.from(messageBeans).flatMap(new Func1<MessageBean, Observable<String>>() {

            @Override
            public Observable<String> call(final MessageBean messageBean) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(messageBean.getMessage());
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.d("Observable.flatMap:" + s);
            }
        });

    }

    private void subscriber() {
//
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Logger.d("Observe onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Logger.d(s);
//            }
//        };
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//        };
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                try {
//                    Thread.sleep(6000);
//                    for (int i = 0; i < 3; i++) {
//                        subscriber.onNext("aa");
//                    }
//
//                    subscriber.onCompleted();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        observable.subscribeOn(Schedulers.io()).observeOn((AndroidSchedulers.mainThread())).subscribe(observer);
    }
}
