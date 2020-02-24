package com.ifeng_tech.spotmall.zifubao.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/6/8.
 *
 *
 */

public class OrderInfoBean implements Serializable {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"input":"<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}\">","page":"<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=RPVUV6J%2FgEo%2FYuU1BguUd4t5%2FX71l3g3bdeLRGOLId5YhN5VCkPzN1eZxlDOYL0DFQrA2icGKAOFpXrJoF8MB4EE1rKx1Nl46aJ3v11YDwNKDAACEZx22sfdFuGTHp6YTliPefFr8kUBove70w0vLQtFiPLum2t05%2FLX55uxB4qn%2FDejSX2Ej2ttk4aWNQXHm0x0XspDfdBYQYOLoG7YA%2BW5su2vkHA%2FvwhH%2B87ythK4EikAymkeHLUxjv8NBSJ9J%2Bj57RJIp0l26vCDxHSuWoqwZMS8gyjAA%2BKgRmGNp%2FPReycXS3wPSGV25nv4Li3s037DBsClU24fxujB4ODJVg%3D%3D&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&version=1.0&app_id=2016091500514662&sign_type=RSA2&timestamp=2018-06-07+19%3A02%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\">\n<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}\">\n<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n<\/form>\n<script>document.forms[0].submit();<\/script>","form":"<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=RPVUV6J%2FgEo%2FYuU1BguUd4t5%2FX71l3g3bdeLRGOLId5YhN5VCkPzN1eZxlDOYL0DFQrA2icGKAOFpXrJoF8MB4EE1rKx1Nl46aJ3v11YDwNKDAACEZx22sfdFuGTHp6YTliPefFr8kUBove70w0vLQtFiPLum2t05%2FLX55uxB4qn%2FDejSX2Ej2ttk4aWNQXHm0x0XspDfdBYQYOLoG7YA%2BW5su2vkHA%2FvwhH%2B87ythK4EikAymkeHLUxjv8NBSJ9J%2Bj57RJIp0l26vCDxHSuWoqwZMS8gyjAA%2BKgRmGNp%2FPReycXS3wPSGV25nv4Li3s037DBsClU24fxujB4ODJVg%3D%3D&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&version=1.0&app_id=2016091500514662&sign_type=RSA2&timestamp=2018-06-07+19%3A02%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\">\n<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}\">\n<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n<\/form>","value":"{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}","url":"\"https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=RPVUV6J%2FgEo%2FYuU1BguUd4t5%2FX71l3g3bdeLRGOLId5YhN5VCkPzN1eZxlDOYL0DFQrA2icGKAOFpXrJoF8MB4EE1rKx1Nl46aJ3v11YDwNKDAACEZx22sfdFuGTHp6YTliPefFr8kUBove70w0vLQtFiPLum2t05%2FLX55uxB4qn%2FDejSX2Ej2ttk4aWNQXHm0x0XspDfdBYQYOLoG7YA%2BW5su2vkHA%2FvwhH%2B87ythK4EikAymkeHLUxjv8NBSJ9J%2Bj57RJIp0l26vCDxHSuWoqwZMS8gyjAA%2BKgRmGNp%2FPReycXS3wPSGV25nv4Li3s037DBsClU24fxujB4ODJVg%3D%3D&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&version=1.0&app_id=2016091500514662&sign_type=RSA2&timestamp=2018-06-07+19%3A02%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\""}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * input : <input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
         * page : <form name="punchout_form" method="post" action="https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=RPVUV6J%2FgEo%2FYuU1BguUd4t5%2FX71l3g3bdeLRGOLId5YhN5VCkPzN1eZxlDOYL0DFQrA2icGKAOFpXrJoF8MB4EE1rKx1Nl46aJ3v11YDwNKDAACEZx22sfdFuGTHp6YTliPefFr8kUBove70w0vLQtFiPLum2t05%2FLX55uxB4qn%2FDejSX2Ej2ttk4aWNQXHm0x0XspDfdBYQYOLoG7YA%2BW5su2vkHA%2FvwhH%2B87ythK4EikAymkeHLUxjv8NBSJ9J%2Bj57RJIp0l26vCDxHSuWoqwZMS8gyjAA%2BKgRmGNp%2FPReycXS3wPSGV25nv4Li3s037DBsClU24fxujB4ODJVg%3D%3D&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&version=1.0&app_id=2016091500514662&sign_type=RSA2&timestamp=2018-06-07+19%3A02%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json">
         <input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
         <input type="submit" value="立即支付" style="display:none" >
         </form>
         <script>document.forms[0].submit();</script>
         * form : <form name="punchout_form" method="post" action="https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=RPVUV6J%2FgEo%2FYuU1BguUd4t5%2FX71l3g3bdeLRGOLId5YhN5VCkPzN1eZxlDOYL0DFQrA2icGKAOFpXrJoF8MB4EE1rKx1Nl46aJ3v11YDwNKDAACEZx22sfdFuGTHp6YTliPefFr8kUBove70w0vLQtFiPLum2t05%2FLX55uxB4qn%2FDejSX2Ej2ttk4aWNQXHm0x0XspDfdBYQYOLoG7YA%2BW5su2vkHA%2FvwhH%2B87ythK4EikAymkeHLUxjv8NBSJ9J%2Bj57RJIp0l26vCDxHSuWoqwZMS8gyjAA%2BKgRmGNp%2FPReycXS3wPSGV25nv4Li3s037DBsClU24fxujB4ODJVg%3D%3D&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&version=1.0&app_id=2016091500514662&sign_type=RSA2&timestamp=2018-06-07+19%3A02%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json">
         <input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
         <input type="submit" value="立即支付" style="display:none" >
         </form>
         * value : {&quot;out_trade_no&quot;:&quot;P00000486902018060719022863539885&quot;,&quot;total_amount&quot;:&quot;1000.0&quot;,&quot;subject&quot;:&quot;充值&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}
         * url : "https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=RPVUV6J%2FgEo%2FYuU1BguUd4t5%2FX71l3g3bdeLRGOLId5YhN5VCkPzN1eZxlDOYL0DFQrA2icGKAOFpXrJoF8MB4EE1rKx1Nl46aJ3v11YDwNKDAACEZx22sfdFuGTHp6YTliPefFr8kUBove70w0vLQtFiPLum2t05%2FLX55uxB4qn%2FDejSX2Ej2ttk4aWNQXHm0x0XspDfdBYQYOLoG7YA%2BW5su2vkHA%2FvwhH%2B87ythK4EikAymkeHLUxjv8NBSJ9J%2Bj57RJIp0l26vCDxHSuWoqwZMS8gyjAA%2BKgRmGNp%2FPReycXS3wPSGV25nv4Li3s037DBsClU24fxujB4ODJVg%3D%3D&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&version=1.0&app_id=2016091500514662&sign_type=RSA2&timestamp=2018-06-07+19%3A02%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json"
         */

        private String input;
        private String page;
        private String form;
        private String value;
        private String url;

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
