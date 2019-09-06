package com.cesar.petpost.mvp.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cesar.petpost.activities.MainActivity
import com.cesar.petpost.adapter.PetPostAdapter
import com.cesar.petpost.mvp.model.PetPost
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainView(val mainActivity: MainActivity) {

    lateinit var petPostAdapter: PetPostAdapter
    lateinit var listPosts: List<PetPost?>
    private var indexStart: Int = 0
    private var indexEnd: Int = 0
    private var orderSelected = 0


    fun showListPost(posts: ArrayList<PetPost>) {

        listPosts = posts
        indexStart = 0
        indexEnd = 10

        val subList = ArrayList(posts.subList(indexStart, indexEnd))
        petPostAdapter = PetPostAdapter(subList as ArrayList<PetPost?>, mainActivity)
        mainActivity.petPostsRecyclerView.adapter = petPostAdapter
        mainActivity.petPostsRecyclerView.layoutManager = LinearLayoutManager(mainActivity)

        sortByViews()

        val scrollListener = object : RecyclerView.OnScrollListener() {

            @SuppressLint("CheckResult")
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val visibleItemCount = recyclerView.childCount
                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val firstVisibleItem =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItem) >= totalItemCount && firstVisibleItem >= 0) {
                    Completable.create { emitter ->
                        emitter.onComplete()
                    }
                        .subscribeOn(Schedulers.io())
                        .delay(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onLoadMore()
                        }
                }
            }

        }
        mainActivity.petPostsRecyclerView.addOnScrollListener(scrollListener)

        initSpinnerListener()
    }

    fun onLoadMore() {

        val subList: ArrayList<PetPost?>
        if (listPosts.size - 1 > indexEnd + 10) {
            indexStart += 10
            indexEnd += 10

            subList = ArrayList(listPosts.subList(indexStart, indexEnd))

            insertElements(subList)

            petPostAdapter.notifyItemRangeChanged(indexStart, indexEnd)

            if (orderSelected == 0) {
                sortByLikes()
            } else {
                sortByViews()
            }

        } else if (indexEnd < listPosts.size) {
            subList = ArrayList(listPosts.subList(indexEnd, listPosts.size))
            indexEnd += 10
            insertElements(subList)
            petPostAdapter.notifyItemRangeChanged(indexEnd, listPosts.size)

            if (orderSelected == 0) {
                sortByLikes()

            } else {
                sortByViews()
            }
        }
    }

    private fun insertElements(subList: ArrayList<PetPost?>) {
        petPostAdapter.petPostList.remove(petPostAdapter.petPostList.last())
        subList.forEach {
            petPostAdapter.petPostList.add(it)

        }
    }

    private fun initSpinnerListener() {
        mainActivity.sortSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        orderSelected = 0
                        sortByLikes()

                    }
                    1 -> {
                        orderSelected = 1
                        sortByViews()
                    }
                }

            }
        }
    }

    private fun sortByViews() {
        petPostAdapter.petPostList.remove(null)
        petPostAdapter.petPostList.sortWith(Comparator { p1, p2 ->
            if (p1 != null && p2 != null) {
                when {
                    p1.views < p2.views -> 1
                    p1.views == p2.views -> 0
                    else -> -1
                }
            } else {
                return@Comparator -1
            }
        })
        notifySort()
    }

    private fun sortByLikes() {
        petPostAdapter.petPostList.remove(null)
        petPostAdapter.petPostList.sortWith(Comparator { p1, p2 ->
            if (p1 != null && p2 != null) {
                when {
                    p1.likes < p2.likes -> 1
                    p1.likes == p2.likes -> 0
                    else -> -1
                }
            } else {
                return@Comparator -1
            }
        })
        notifySort()
    }

    private fun notifySort() {
        if (petPostAdapter.petPostList.size < listPosts.size) {
            petPostAdapter.petPostList.add(null)
        }
        petPostAdapter.notifyDataSetChanged()
    }


}