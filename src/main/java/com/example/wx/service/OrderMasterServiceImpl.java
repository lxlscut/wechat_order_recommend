package com.example.wx.service;

import com.example.wx.dao.OrderMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterMapper omm;
    @Override
    public int ordernum() {
        int i = omm.orderno();
        return i;
    }
}
