package com.hopeshop.item.controller;

import com.hopeshop.common.pojo.PageResult;
import com.hopeshop.item.pojo.Brand;
import com.hopeshop.item.service.BrandService;
import com.hopeshop.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;



    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false) String key){

        PageResult<Brand> result =
                this.brandService.queryBrandByPageAndSort(page,rows,sortBy,desc,key);

        if(result == null || result.getItems().size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        this.brandService.saveBrand(brand,cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 编辑品牌
     * @param brand
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> editBrand(Brand brand) {
        this.brandService.editBrand(brand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("del/{bid}")
    public ResponseEntity<Brand> deleteBrand(Brand brand,@PathVariable("bid") Long bid){
        this.brandService.deleteBrand(bid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 根据分类查询品牌
     * @param cid
     * @return
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategory(@PathVariable("cid") Long cid) {
        List<Brand> list = this.brandService.queryBrandByCategory(cid);
        if(list == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据多个id查询品牌
     * @param ids
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        List<Brand> list = this.brandService.queryBrandByIds(ids);
        if(list == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
