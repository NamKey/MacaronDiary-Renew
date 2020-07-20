package com.keydo.macarondiary.fragment


import android.app.Activity
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.billingclient.api.*

import com.keydo.macarondiary.R
import kotlinx.android.synthetic.main.fragment_setting.*

private lateinit var billingClient: BillingClient

class SettingFragment : Fragment(), PurchasesUpdatedListener, View.OnClickListener {
//    val activity : Activity =
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "Setting"
        initializebtnListener()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {

        super.onDestroyView()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_donatedeveloper->{
                Log.d("Setting","donate")
                connBillingClient()
//            startActivity(Intent(activity, BillingActivity::class.java))
            }
            R.id.btn_noads->{
                Log.d("Setting","noads")
                connBillingClient()
            }
        }
    }

    private fun initializebtnListener(){
        btn_donatedeveloper.setOnClickListener(this)
        btn_noads.setOnClickListener(this)
    }

    override fun onPurchasesUpdated(billingresult: BillingResult, purchaselist: MutableList<Purchase>?) {
        if(billingresult.responseCode == BillingClient.BillingResponseCode.OK && purchaselist != null){
            for (purchase in purchaselist){

            }
        }else if (billingresult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED){

        }else{

        }
    }

    private fun connBillingClient(){
        billingClient = BillingClient.newBuilder(activity?.applicationContext!!).enablePendingPurchases().setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    Log.d("Setting","Billing Ready")
//                    showPurchaseDialog(billingClient)//Custom
                    val skuList = ArrayList<String>()
                    skuList.add("donate")
                    skuList.add("noads")
                    val params = SkuDetailsParams.newBuilder()
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
                    billingClient.querySkuDetailsAsync(params.build()) { billingresult, skuDetailsList ->
                        Log.d("Setting", skuDetailsList?.size.toString())
                        Log.d("Setting",billingresult.responseCode.toString())
                        if (billingresult.responseCode == BillingClient.BillingResponseCode.OK&& skuDetailsList != null) {
                            for (skuDetails in skuDetailsList) {
                                val sku = skuDetails.sku
                                val price = skuDetails.price
                                when (sku) {
                                    "donate" -> {
                                        Log.d("Setting",sku)
                                        Log.d("Setting",price)
                                        val flowParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(skuDetails)
                                            .build()
                                        val responseCode = billingClient.launchBillingFlow(Activity(), flowParams)
                                        Log.d("Setting",responseCode.toString())
                                        Log.d("Setting",responseCode.debugMessage)
                                    }//switch - donate end
                                    "noads" -> {
                                        Log.d("Setting",sku)
                                        Log.d("Setting",price)
                                        val flowParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(skuDetails)
                                            .build()
                                        val responseCode = billingClient.launchBillingFlow(Activity(), flowParams)
                                        Log.d("Setting",responseCode.toString())
                                        Log.d("Setting",responseCode.debugMessage)
                                    }//switch - noads end
                                    else -> {
                                        Log.d("Setting","fail")
                                    }//switch - else end
                                }//when end
                            }//skuDetailsList for end
                        }//billing response if end
                    }
                }
            }
            override fun onBillingServiceDisconnected() {
                    Log.d("Setting","disconnected")
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }
}



