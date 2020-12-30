package com.hopeshop.item.api;

import com.hopeshop.common.pojo.PageResult;
import com.hopeshop.item.pojo.Sku;
import com.hopeshop.item.pojo.Spu;
import com.hopeshop.item.pojo.SpuBo;
import com.hopeshop.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsApi {


    /**
     * 分页查询商品
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", defaultValue = "true") Boolean saleable,
            @RequestParam(value = "key", required = false) String key);

    /**
     * 根据spu商品id查询详情
     * @param id
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    SpuDetail querySpuDetById(@PathVariable("id") Long id);

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    @GetMapping("/sku/list")
    public List<Sku> querySkuBySpuId(@RequestParam("id") Long id);

    /**
     * 根据spu的id查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    public Spu querySpuById(@PathVariable("id") Long id);

    @GetMapping("sku/{id}")
    public Sku querySkuById(@PathVariable("id")Long id);
}
