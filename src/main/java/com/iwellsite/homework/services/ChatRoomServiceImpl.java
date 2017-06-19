package com.iwellsite.homework.services;

import com.iwellsite.homework.domain.model.ChatRoom;
import com.iwellsite.homework.domain.repo.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mshields on 2017-06-16.
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private ChatRoomRepository chatRoomRepository;

    @Autowired
    public void setChatRoomRepository(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public Iterable<ChatRoom> listAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public ChatRoom getChatRoomById(Integer id) {
        return chatRoomRepository.findOne(id);
    }

    @Override
    public ChatRoom saveChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public void deleteChatRoom(Integer id) {
        chatRoomRepository.delete(id);
    }


}