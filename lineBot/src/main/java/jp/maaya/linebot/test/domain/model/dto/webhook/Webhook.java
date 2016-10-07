package jp.maaya.linebot.test.domain.model.dto.webhook;

import lombok.Data;

/**
 * WebHook bodyエントリ
 * Created by user on 2016/10/07.
 */
@Data
public class Webhook {
    Event [] events;
}
