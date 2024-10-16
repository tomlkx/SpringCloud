package org.lkx.service.impl;

import org.lkx.dao.DepartRepository;
import org.lkx.dom.Depart;
import org.lkx.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartRepository departRepository;
    /**
     * 插入
     * @param depart
     * @return
     */
    @Override
    public boolean saveDepart(Depart depart) {
        /**
         * 对于save() 的参数,根据其id的不同,有一下三种情况：
         * depart 的 id 为 null                  ： 插入操作
         * depart 的 id 不为 null,且DB中该id存在   ： 修改操作
         * depart 的 id 不为 null,且DB中该id不存在 ： 执行插入操作
         *      但其传入的id值并不是这里指定的id,而是根据框架指定的id生成策略所生成的id
         */
        Depart save = departRepository.save(depart);
        return save != null ? true : false;
    }
    @Override
    public boolean removerDepartById(Integer id) {
        if (departRepository.existsById(id)) {
            //在DB中指定的ID若不存在,该方法会抛出异常
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean modifyDepart(Depart depart) {
        if(departRepository.existsById(depart.getId()) && depart.getId() != null){
            departRepository.save(depart);
            return true;
        }
        return false;
    }
    @Override
    public Depart getDepartById(Integer id) {
        if(departRepository.existsById(id)){
            return departRepository.findById(id).get();
        }
        return new Depart();
    }
    @Override
    public List<Depart> listAllDepart() {
        return StreamSupport.stream(departRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
}