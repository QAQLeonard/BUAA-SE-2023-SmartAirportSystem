package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.Product;
import top.leonardsaikou.backend.mapper.ProductMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
    private final ProductMapper productMapper;

    public ProductController(ProductMapper productMapper)
    {
        this.productMapper = productMapper;
    }

    @ApiOperation("获取全部商品信息")
    @GetMapping("/product")
    public String getProducts() throws JsonProcessingException
    {
        List<Product> productList = productMapper.selectList(null);
        //System.out.println(productList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(productList);
        return "{"+"\"TotalNumber\":" + productList.size() + ",\"productData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个商品信息")
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable String id) throws JsonProcessingException
    {
        Product product = productMapper.selectById(id);
        //System.out.println(product);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(product);
        return "{"+"\"TotalNumber\":" + 1 + ",\"productData\":"+ json + "}";
    }

    @ApiOperation("插入商品信息")
    @PostMapping("/product")
    public String addProduct(@RequestBody Product product)
    {
        int i = productMapper.insert(product);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新商品信息")
    @PutMapping("/product")
    public String updateProduct(@RequestBody Product product)
    {
        int i = productMapper.updateById(product);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除商品信息")
    @DeleteMapping("/product/{id}")
    public String deleteProductById(@PathVariable String id)
    {
        int i = productMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }


}
