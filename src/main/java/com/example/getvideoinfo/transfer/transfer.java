package com.example.getvideoinfo.transfer;

public class transfer {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20220607001240892";
    private static final String SECURITY_KEY = "uvSdFNqs2Ss4UHsCnCnp";

    public String transferEnglish(String transferEnglish) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = transferEnglish;
        return api.getTransResult(query, "en", "zh");
    }

}
