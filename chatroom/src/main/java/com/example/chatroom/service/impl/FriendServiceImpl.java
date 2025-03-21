package com.example.chatroom.service.impl;

import com.example.chatroom.entity.Friend;
import com.example.chatroom.entity.User;
import com.example.chatroom.entity.vo.AddFriendResponseVO;
import com.example.chatroom.mapper.FriendMapper;
import com.example.chatroom.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements IFriendService {
    @Autowired
    private FriendMapper friendMapper;

    public List<Friend> selectFriendList(int userId) {
        return friendMapper.selectFriendList(userId);
    }

    public List<Friend> searchFriendList(String username) {
        return friendMapper.searchFriendList(username);
    }

    public String selectFriendNameByUserId(int userId) {
        return friendMapper.selectFriendNameByUserId(userId);
    }

    public User selectFriendByUserId(int userId) {
        return friendMapper.selectFriendByUserId(userId);
    }

    public Integer addAddFriend(Integer fromId, Integer userId, String input) {
        return friendMapper.addAddFriend(fromId, userId, input);
    }

    public List<AddFriendResponseVO> getAddFriendList(Integer userId) {
        return friendMapper.getAddFriendList(userId);
    }


    public Integer deleteAddFriend(Integer fromId, Integer userId) {
        return friendMapper.deleteAddFriend(fromId, userId);
    }

    public Integer agreeAddFriend(Integer fromId, Integer userId) {
        return friendMapper.addFriend(fromId, userId);
    }
}
