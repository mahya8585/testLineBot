package jp.maaya.linebot.test.domain.model.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * LINE API 共通処理
 * Created by user on 2016/10/07.
 */
public class CommonLineApiService {
    private final String authPrefix = "Bearer ";
    private final String auth = authPrefix + "【your auth key】";

    /**
     * Httpヘッダーの作成
     * @return ヘッダー情報
     */
    public HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Authorization", auth);

        return headers;
    }
}
