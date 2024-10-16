package org.lkx.controll;

import org.lkx.dom.Depart;
import org.lkx.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/provider/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    @PostMapping("/")
    public boolean saveHandler(@RequestBody Depart depart){
        System.out.println(depart.getName());
        return departService.saveDepart(depart);
    }
    @DeleteMapping("/{id}")
    public boolean delHandler(@PathVariable("id")Integer id){
        return departService.removerDepartById(id);
    }
    @PutMapping("/")
    public boolean updateHandler(Depart depart){
        System.out.println(depart.getName());
        return departService.modifyDepart(depart);
    }
    @GetMapping("/{id}")
    public Depart getHandler(@PathVariable("id")Integer id){
        return departService.getDepartById(id);
    }
    @GetMapping("/")
    public List<Depart> listHandler(){
        return departService.listAllDepart();
    }
}
