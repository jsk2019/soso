package com.whu.soso.Controller;


import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.ChatRecordRepository;
import com.whu.soso.model.ChatRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatRecordController {

    @Autowired
    private ChatRecordRepository chatRecordRepository;

    /**
     * 存储离线聊天记录
     */
    @PostMapping(value = "/storeRecord", produces = "application/json")
    public ResponseEntity<String> storeRecord(ChatRecord chatRecord) {
        chatRecordRepository.save(chatRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body("存储成功");
    }

    /**
     * 删除聊天记录
     */
    @DeleteMapping(value = "/deleteRecord")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecord(@RequestParam("senderPhone") String sp, @RequestParam("receiverPhone")
            String rp ){

        chatRecordRepository.deleteChatRecordsBySenderPhoneAndReceiverPhone(sp, rp);
    }


    /**
     *查询聊天记录
     */

    @PostMapping(value = "/queryRecords")
    public JSONObject queryRecords(String senderPhone, String receiverPhone) {
        List<ChatRecord> records = chatRecordRepository.findChatRecordsBySenderPhoneAndReceiverPhoneOrderByCharTimeAsc(
                senderPhone, receiverPhone);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("records",records);
        return jsonObject;

    }

}
