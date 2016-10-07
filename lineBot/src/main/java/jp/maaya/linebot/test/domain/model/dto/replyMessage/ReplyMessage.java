package jp.maaya.linebot.test.domain.model.dto.replyMessage;

import jp.maaya.linebot.test.domain.model.dto.Message;
import lombok.Data;

/**
 * Created by user on 2016/10/07.
 */
@Data
public class ReplyMessage {
    String replyToken;
    Message[] messages;
}
