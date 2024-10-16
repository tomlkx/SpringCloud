package org.lkx.service;

import org.lkx.dom.Depart;

import java.util.List;

public interface DepartService {
    boolean saveDepart(Depart depart);
    boolean removerDepartById(Integer id);
    boolean modifyDepart(Depart depart);
    Depart getDepartById(Integer id);
    List<Depart> listAllDepart();
}
