package jp.maaya.linebot.test.controller;


import jp.maaya.linebot.test.domain.model.dto.replyMessage.ReplyMessage;
import jp.maaya.linebot.test.domain.model.dto.webhook.Webhook;
import jp.maaya.linebot.test.domain.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接続テストクラス
 * Created by maaya
 */
@RestController
@RequestMapping("/line")
public class ReplyController {
    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;

    /**
     * replyAPI実行
     */
    @PostMapping(value = "/reply", produces = MediaType.APPLICATION_JSON_VALUE)
    public String reply(@RequestBody Webhook hookInfo) {
        logger.debug("返信API");

        //post情報の作成
        ReplyMessage requestBody = replyService.createReplyBody(hookInfo);

        //メッセージ送信
        replyService.sendMessage(requestBody);

        return "done.";
    }

}
