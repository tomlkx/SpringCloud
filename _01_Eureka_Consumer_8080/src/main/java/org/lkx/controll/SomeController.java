package org.lkx.controll;

import org.lkx.dom.Depart;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class SomeController {

    @Autowired
    private RestTemplate restTemplate;

    //直接连接提供者
    //final private String  SERVICE_PROVIDER = "http://localhost:8081";
    //要使用为服务名称来从eureka server查找提供者
    private static final String SERVICE_PROVIDER = "http://provider-depart";
    @PostMapping("/")
    public boolean saveHandler(@RequestBody Depart depart) {
        System.out.println(depart.getName());
        String url = SERVICE_PROVIDER + "/provider/depart/";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @DeleteMapping("/{id}")
    public void deleteHandler(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/")
    public void updateHandler(Depart depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/";
        restTemplate.put(url, depart);
    }

    @GetMapping("/{id}")
    public Depart getByIdHandler(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/"+id;
        return restTemplate.getForObject(url, Depart.class);
    }

    @GetMapping
    public List<Depart> listHandler() {
        String url = SERVICE_PROVIDER + "/provider/depart/";
        return restTemplate.getForObject(url, List.class);
    }

}
