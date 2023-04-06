package dv.kinash.hw7_rest.service;

import dv.kinash.hw7_rest.entity.Shop;
import dv.kinash.hw7_rest.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository repository;
    public ShopService() {
    }

    public Shop getById(Integer id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found by id="+id));
    }
    public List<Shop> getList(){
        List<Shop> shopList = new ArrayList<>();
        repository.findAll().forEach(shopList::add);
        return shopList;
    }
    public Shop add(Shop shop){
        shop.setId(null);
        repository.save(shop);
        return shop;
    }
    public Shop updateById(Integer id, Shop shop){
        if (repository.findById(id).isEmpty())
            return null;
        shop.setId(id);
        repository.save(shop);
        return shop;
    }
    public Boolean deleteById(Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
