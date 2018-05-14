package com.vitali.mykotlinreactiveprogramming

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vitali.mykotlinapp.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class OperatorsAndObservableActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oberators_and_observable)

        init()
    }

    private fun init()
    {
        //map()

        //mapAndFilter()

        rxCalls()
    }

    private fun rxCalls()
    {
        NetworkHandler.getSearch("sentence",0,1, 15)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map{
                    heavyComputeFun()
                    it
                }
                .subscribe{
                    Logger.logDebug()
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
    }

    private fun heavyComputeFun()
    {
        Thread.sleep(3000)
    }

    private fun mapAndFilter()
    {
        Observable.just(1, 5, 10, 20).map(object:Function<Int,Int>{
            override fun apply(it: Int): Int
            {
                return it * 3
            }

        })
                .filter(object:Predicate<Int>{
                    override fun test(inputInt: Int): Boolean
                    {
                        return inputInt % 2 == 0
                    }

                })
                .subscribe()
                {
                    Logger.logDebug("item", it.toString())
                }
    }

    private fun map()
    {
        Observable.just(1, 5, 10, 20).map(object: Function<Int, String>{
            override fun apply(item: Int): String
            {
                val result = item * 3
                Logger.logDebug("item = ", item.toString())
                return "My favorite number is: $result"
            }

        })
                .subscribe()
                { item ->
                    Logger.logDebug("item * 3 = ", item)
                }
    }
}
