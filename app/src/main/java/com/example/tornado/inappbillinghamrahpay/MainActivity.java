package com.example.tornado.inappbillinghamrahpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ir.devage.hamrahpay.HamrahPay;


//refer to (https://github.com/hamrahpay/androidStudioAAR) & (https://www.aparat.com/hamrahpay)

public class MainActivity extends AppCompatActivity {

    Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_pay=findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String yourSKU = "hp_5415e384f37bf802917441";   // شناسه کالای شما در سایت همراه پی
                new HamrahPay(MainActivity.this)                // اکتیویتی که می خواهید از آنجا پرداخت انجام شود
                        .sku(yourSKU)                               // اضافه کردن شناسه به صفحه پرداخت
                        .listener(new HamrahPay.Listener() {        // لیسنر برای آگاهی شما از موفق بودن یا نبودن پرداخت
                            @Override
                            public void onErrorOccurred(String status, String message) {
                                // مشکلی در پرداخت روی داده است یا کاربر پرداخت را انجام نداده است
                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                                Log.e("HamrahPay", status + ": " + message);
                            }

                            @Override
                            public void onPaymentSucceed(String payCode) {
                                Toast.makeText(MainActivity.this, "پرداخت انجام نشد!", Toast.LENGTH_SHORT).show();
                                // کاربر با موفقیت پرداخت را انجام داده است
                                Log.i("HamrahPay", "payCode: " + payCode);
                            }
                        })
                        .startPayment();


            }
        });

        if (HamrahPay.isPremium(MainActivity.this,"hp_5415e384f37bf802917441")) {        // چک کردن خرید با ورودی شناسه کالا
            btn_pay.setEnabled(false);                             // غیر فعال کردن دکمه خرید اگر پرداخت انجام شده باشد
        }

    }
}