package com.likeit.aqe365.network.model.pay;

public class AlipayPayAModel {

    /**
     * appalipay : {"code":200,"data":"alipay_sdk=alipay-sdk-php-20161101&app_id=2018031902408105&biz_content=%7B%22subject%22%3A%22%E6%BE%B3%E6%B3%89%E5%8C%BB%E9%94%80%E7%BD%91%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%22252%22%2C%22total_amount%22%3A%2220%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2210m%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Faoquan.maimaitoo.com%2Faddons%2Fewei_shopv2%2Fpayment%2Fapp_alipay%2Fapp_notify.php&sign_type=RSA2&timestamp=2018-09-06+16%3A33%3A22&version=1.0&sign=lJQmcdHFzFytSae9Fzt9dkVtzID6QrRVwmH8LjR5Or7S7nttKPLT%2BjMDmXyiP0Z9j7DgDgS0hNiwdSAAXkgaOVZVcE1vwcSNsqOIeqmMtTMeJS73G3zd%2FIpCqpXxCX0b0Qw39Qdb5zV%2B%2FCEpoaZSOG3thApUIXQMsQ%2BrOCGa%2FUulc0nQNlSI%2BAhlzQ8dIXnVNSMjqlpJdo2HtzA1YsyvUZ6stlG6LhF8B2bE%2FM9MwIAV4VEf%2FhVJoipCzj%2BLHCX6CVr%2FUn1DbIJuA96hM7ObshZX76gdhkPfZt%2FRsFCxA1sufqL5rYPVwTR44niVpj6zkiSc5INeFNtxBnttBhctUw%3D%3D"}
     */

    private String appalipay;

    public String getAppalipay() {
        return appalipay;
    }

    public void setAppalipay(String appalipay) {
        this.appalipay = appalipay;
    }
}
