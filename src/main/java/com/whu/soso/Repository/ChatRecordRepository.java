package com.whu.soso.Repository;

import com.whu.soso.model.ChatRecord;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRecordRepository extends JpaRepository<ChatRecord,Integer> {

    List<ChatRecord> findChatRecordsBySenderPhoneAndReceiverPhoneOrderByCharTimeAsc(String senderPhone,
                                                                                 String receiverPhone);

    void deleteChatRecordsBySenderPhoneAndReceiverPhone(String senderPhone,
                                                    String receiverPhone);

}
