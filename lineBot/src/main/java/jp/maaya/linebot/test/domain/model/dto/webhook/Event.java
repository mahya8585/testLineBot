package jp.maaya.linebot.test.domain.model.dto.webhook;

import jp.maaya.linebot.test.domain.model.dto.Message;
import lombok.Data;

/**
 * Created by user on 2016/10/07.
 */
@Data
public class Event {
    String replyToken;
    String type;
    Long timestamp;
    Source source;
    Message message;
}
