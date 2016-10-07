package jp.maaya.linebot.test.domain.model.service;

import jp.maaya.linebot.test.domain.model.dto.Message;
import jp.maaya.linebot.test.domain.model.dto.replyMessage.ReplyMessage;
import jp.maaya.linebot.test.domain.model.dto.webhook.Event;
import jp.maaya.linebot.test.domain.model.dto.webhook.Webhook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by user on 2016/10/07.
 */
@Component
public class ReplyService {
    //ReplyAPI
    private final String replyUrl = "https://api.line.me/v2/bot/message/reply";

    //reply発動対象イベント名
    private final String eventType = "message";
    //メッセージタイプ
    private final String messageType = "text";
    //返信メッセージ
    private final String returnMessage = "なるほど。";

    /**
     * LINE replyAPIリクエストボディデータの作成
     *
     * @param hookInfo LINE event webhookから送られてきたデータ
     * @return LINE replyAPI用リクエストボディデータ
     */
    public ReplyMessage createReplyBody(Webhook hookInfo) {
        ReplyMessage replyBody = new ReplyMessage();

        //TODO リクエストヘッダーの確認を商用の場合は行うべき
        //X-Line-Signature

        //とりあえずテストなので最初の1イベントにだけ対応
        Event targetEvent = hookInfo.getEvents()[0];
        replyBody.setReplyToken(targetEvent.getReplyToken());
        replyBody.setMessages(createMessage(targetEvent.getType(), targetEvent.getMessage().getText()));

        return replyBody;
    }

    /**
     * メッセージの決定
     * @param requestEventType イベントタイプ
     * @param eventMessage イベントタイプに紐づくメッセージ
     * @return replyしたいメッセージ
     */
    private Message[] createMessage(String requestEventType, String eventMessage) {
        Message message = new Message();
        message.setType(messageType);

        if (requestEventType.equals(eventType)) {
            message.setText("「" + eventMessage + "」か。" + returnMessage);
        } else {
            message.setText(returnMessage);
        }

        Message messages[] = {message};
        return messages;
    }

    /**
     * LINE replyAPIの実行
     *
     * @param requestBody
     */
    public void sendMessage(ReplyMessage requestBody) {
        //Httpヘッダーの作成
        HttpHeaders headers = new CommonLineApiService().createHttpHeaders();

        HttpEntity<ReplyMessage> request = new HttpEntity<>(requestBody, headers);

        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        rt.postForLocation(replyUrl, request);
    }
}
