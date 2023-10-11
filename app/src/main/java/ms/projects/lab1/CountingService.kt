package ms.projects.lab1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class CountingService : Service() {
    private val unsubscribe = PublishSubject.create<Any>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val observable = Observable.interval(0, 1, TimeUnit.SECONDS)

            val observer = object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("CountingService", "SERVICE STARTED SUCCESSFULLY")
                }

                override fun onNext(value: Long) {
                    Log.d("CountingService", value.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("CountingService", "ERROR STARTING THE SERVICE")
                }

                override fun onComplete() {
                }
            }
            observable.takeUntil(unsubscribe).subscribe(observer)
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        unsubscribe.onNext(0)
    }
}
