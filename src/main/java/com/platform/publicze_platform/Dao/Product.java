package com.platform.publicze_platform.Dao;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Document(indexName ="company_products")
@Data
public class Product {
    @Field(name = "_id")
    public String id;

    @Field(name = "trade")
    public String trade;

    @Field(name = "sub_trade")
    public String subTrade;

    @Field(name = "company_no")
    public String companyNo;

    @Field(name = "company_name")
    public String companyName;

    @Field(name = "product_no")
    public String productNo;

    @Field(name = "product_name")
    public String productName;

    @Field(name = "product_des")
    public String productDes;

    @Field(name = "img_src")
    public List<String> imgSrc;

    @Field(name = "pro_param")
    public String proParam;
}
