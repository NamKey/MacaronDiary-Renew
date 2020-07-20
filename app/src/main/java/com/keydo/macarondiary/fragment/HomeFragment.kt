package com.keydo.macarondiary.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keydo.macarondiary.R
import com.keydo.macarondiary.adapter.HomeSliderAdapter
import com.keydo.macarondiary.dataset.SliderItem
import com.google.android.gms.ads.*
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {
    private lateinit var homeSliderAdapter: HomeSliderAdapter
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "Home"
        //AdView Initialization
        adviewInitialize()
        //ShopCard Initialization
        imagesliderInitialize()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onResume() {
        adView.resume()
        super.onResume()
    }

    override fun onDestroyView() {
        adView.destroy()
        super.onDestroyView()
    }

    fun imagesliderInitialize(){
        homeSliderAdapter = HomeSliderAdapter(view?.context!!)

        val slider1 = SliderItem(R.drawable.macaronshop1,"메종드루즈","서울시 성북구 동선동2가 135-19")
        val slider2 = SliderItem(R.drawable.macaronshop2,"콩설기","인천시 부평구 부평동 606")
        val slider3 = SliderItem(R.drawable.macaronshop3,"더은교","서울시 관악구 봉천동 1632-6")
        val slider4 = SliderItem(R.drawable.macaronshop4,"오뗄두스","서울시 서초구 반포동 93-5")
        val slider5 = SliderItem(R.drawable.macaronshop5,"티에리스","서울시 서초구 방배동 456-2")
        val slider6 = SliderItem(R.drawable.macaronshop6,"기욤","서울시 강남구 청담동 88-37")
        val slider7 = SliderItem(R.drawable.macaronshop7,"미완성식탁","서울시 마포구 망원동 404-32")

        homeSliderAdapter.addItem(slider1)
        homeSliderAdapter.addItem(slider2)
        homeSliderAdapter.addItem(slider3)
        homeSliderAdapter.addItem(slider4)
        homeSliderAdapter.addItem(slider5)
        homeSliderAdapter.addItem(slider6)
        homeSliderAdapter.addItem(slider7)
        imageSlider.setSliderAdapter(homeSliderAdapter)
        imageSlider.startAutoCycle();
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.FILL)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    fun adviewInitialize(){
        var adView = AdView(activity?.applicationContext)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = getString(R.string.banner_ad_unit_id_for_test)
        MobileAds.initialize(activity?.applicationContext){}
        adView = view?.findViewById(R.id.adView)!!
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.d("@@@", "onAdLoaded");// Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                Log.d("@@@", "onAdFailedToLoad $errorCode");// Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }
}




