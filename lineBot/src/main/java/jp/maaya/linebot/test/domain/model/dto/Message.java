package jp.maaya.linebot.test.domain.model.dto;

import lombok.Data;

/**
 * Created by user on 2016/10/07.
 */
@Data
public class Message {
    String id;
    String type;
    String text;
}
