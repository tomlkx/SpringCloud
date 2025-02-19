package com.atguigu.cloud.service;

import java.util.List;
import com.atguigu.cloud.entities.Pay;

public interface PayService {
    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();
}
