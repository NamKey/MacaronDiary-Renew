package com.keydo.macarondiary.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.billingclient.api.*
import com.keydo.macarondiary.R

private lateinit var billingClient: BillingClient
class BillingActivity : AppCompatActivity() , PurchasesUpdatedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)
        connectBillingClient()
    }

    override fun onPurchasesUpdated(billingresult: BillingResult, purchaselist: MutableList<Purchase>?) {
        if(billingresult.responseCode == BillingClient.BillingResponseCode.OK && purchaselist != null){
            for (purchase in purchaselist){

            }
        }else if (billingresult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED){

        }else{

        }
    }

//    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
//        // * 구매 완료시 호출
//        Log.d("Setting", "productid : $productId")
//        Log.d("Setting", "detail : $details")
//        // productId: 구매한 sku (ex) no_ads)
//        // details: 결제 관련 정보
//    }
//
//    override fun onBillingInitialized() {
//        // * 처음에 초기화됬을때.
//    }
//
//    override fun onPurchaseHistoryRestored() {
//        // * 구매 정보가 복원되었을때 호출
//    }
//
//    override fun onBillingError(errorCode: Int, error: Throwable?) {
//        // * 구매 오류시 호출
////        val errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
//        Log.d("Setting",errorCode.toString())
//
//    }

    private fun connectBillingClient(){
        billingClient = BillingClient.newBuilder(applicationContext).enablePendingPurchases().setListener(this).build()
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
                    billingClient.querySkuDetailsAsync(params.build()
                    ) { billingresult, skuDetailsList ->
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK&& skuDetailsList != null) {
                            Log.d("Setting",skuDetailsList.count().toString()+"billing")
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
                                    }
                                    "noads" -> {
                                        Log.d("Setting",sku)
                                        Log.d("Setting",price)
                                        val flowParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(skuDetails)
                                            .build()
                                        val responseCode = billingClient.launchBillingFlow(Activity(), flowParams)
                                        Log.d("Setting",responseCode.toString())
                                        Log.d("Setting",responseCode.debugMessage)
                                    }
                                    else -> {
                                        Log.d("Setting","fail")
                                    }
                                }
                            }
                        }
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