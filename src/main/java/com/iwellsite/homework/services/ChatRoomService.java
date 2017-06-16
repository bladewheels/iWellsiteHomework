package com.iwellsite.homework.services;

import com.iwellsite.homework.domain.ChatRoom;

/**
 * Created by mshields on 2017-06-16.
 */
public interface ChatRoomService {

    Iterable<ChatRoom> listAllChatRooms();

    ChatRoom getChatRoomById(Integer id);

    ChatRoom saveChatRoom(ChatRoom chatRoom);

    void deleteChatRoom(Integer id);
}
