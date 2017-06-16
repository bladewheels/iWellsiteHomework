package com.iwellsite.homework.controllers;

import com.iwellsite.homework.domain.ChatRoom;
import com.iwellsite.homework.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mshields on 2017-06-16.
 */
@Controller
public class ChatRoomController {

    private ChatRoomService chatRoomService;

    @Autowired
    public void setChatRoomService(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @RequestMapping(value = "/chatRooms", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("chatRooms", chatRoomService.listAllChatRooms());
        return "chatRooms";
    }

    @RequestMapping("chatRoom/{id}")
    public String showChatRoom(@PathVariable Integer id, Model model){
        model.addAttribute("chatRoom", chatRoomService.getChatRoomById(id));
        return "chatRoomshow";
    }

    @RequestMapping("chatRoom/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("chatRoom", chatRoomService.getChatRoomById(id));
        return "chatRoomform";
    }

    @RequestMapping("chatRoom/new")
    public String newChatRoom(Model model){
        model.addAttribute("chatRoom", new ChatRoom());
        return "chatRoomform";
    }

    @RequestMapping(value = "chatRoom", method = RequestMethod.POST)
    public String saveChatRoom(ChatRoom chatRoom){
        chatRoomService.saveChatRoom(chatRoom);
        return "redirect:/chatRoom/" + chatRoom.getId();
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id){
        chatRoomService.deleteChatRoom(id);
        return "redirect:/chatRooms";
    }

}
