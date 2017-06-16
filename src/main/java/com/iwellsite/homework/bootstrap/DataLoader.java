package com.iwellsite.homework.bootstrap;

import com.iwellsite.homework.domain.ChatRoom;
import com.iwellsite.homework.domain.ChatRoomRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by mshields on 2017-06-16.
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ChatRoomRepository chatRoomRepository;

    private Logger log = Logger.getLogger(DataLoader.class);

    @Autowired
    public void setChatRoomRepository(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // TODO: NOT for Production use
        loadChatRoom("Organic", "Talk about organic petrol engine parts");
        loadChatRoom("Diesel", "Talk about diesel engine parts");
        loadChatRoom("Random", "Talk about random engine parts");
    }

    private void loadChatRoom(String name, String description) {

        ChatRoom aRoom = new ChatRoom();
        aRoom.setName(name);
        aRoom.setDescription(description);
        chatRoomRepository.save(aRoom);

        log.info("Saved ChatRoom - id: " + aRoom.getId());
    }
}