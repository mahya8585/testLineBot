package jp.maaya.linebot.test.controller;


import jp.maaya.linebot.test.domain.model.dto.replyMessage.ReplyMessage;
import jp.maaya.linebot.test.domain.model.dto.webhook.Webhook;
import jp.maaya.linebot.test.domain.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 接続テストクラス
 * Created by maaya
 */
@RestController
@RequestMapping("/line")
public class RequestParamCheckController {
    private static final Logger logger = LoggerFactory.getLogger(RequestParamCheckController.class);

    @Autowired
    private ReplyService replyService;

    /**
     * リクエストパラメータ確認
     */
    @PostMapping(value = "/checkPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkPost(@RequestBody Webhook hookInfo) {
        logger.debug("postチェックAPI");

        ReplyMessage requestBody = replyService.createReplyBody(hookInfo);

        return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }

    /**
     * get疎通確認
     */
    @GetMapping(value = "/checkGet")
    String checkGet() {
        logger.debug("getチェックAPI");
        return "return GET.";
    }

}
