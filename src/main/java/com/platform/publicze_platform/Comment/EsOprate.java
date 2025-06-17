package com.platform.publicze_platform.Comment;

import com.platform.publicze_platform.Dao.Product;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.ByQueryResponse;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EsOprate {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /******
     *
     * @param product
     * @return
     * @throws Exception
     */
    public String save(Product product) throws Exception {
        Product newproduct= elasticsearchRestTemplate.save(product);
        return "success";
    }

    /**********
     *
     * @param query
     * @return
     * @throws Exception
     */
    public List<Product> search(String query) throws Exception{
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("_all", query)) // 根据_all字段进行匹配查询
                //.withPageable(PageRequest.of(0, 10)) // 分页，这里是从第0页开始，每页10条记录。
                .build();
        SearchHits<Product> hits = elasticsearchRestTemplate.search(searchQuery, Product.class); // 执行查询并获取结果。注意这里的YourEntity应与你的实体类对应。
        List<Product> products= hits.stream().map(SearchHit::getContent).collect(Collectors.toList()); // 提取结果列表。
        return products;
    }

    /******
     *
     * @param companyNo
     * @return
     * @throws Exception
     */
    public List<Product> searchByNos(String companyNo) throws Exception
    {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("company_no",companyNo))
                .build();
        SearchHits<Product> hits = elasticsearchRestTemplate.search(searchQuery, Product.class); // 执行查询并获取结果。注意这里的YourEntity应与你的实体类对应。
        List<Product> products= hits.stream().map(SearchHit::getContent).collect(Collectors.toList()); // 提取结果列表。
        return products;
    }
    /******
     * 根据公司和产品编号修改图片路径
     * @param compNoandProNo
     * @param imgPaths
     * @return
     */
    public String modifyImgPath(String compNoandProNo,List<String> imgPaths) throws Exception
    {
        String[] Nos = compNoandProNo.split("\\#");
        String compNo = Nos[0];
        String proNo = Nos[1];
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //设置更新的条件，companyNo
        boolQueryBuilder.must(QueryBuilders.matchQuery("company_no",compNo));
        boolQueryBuilder.must(QueryBuilders.matchQuery("product_no",proNo));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(boolQueryBuilder);
        // 创建更新脚本
        Map<String, Object> params = new HashMap<>();
        params.put("img_src", imgPaths);
        String script = "ctx._source.img_src = params.img_src";
        //UpdateByQueryRequest request = new UpdateByQueryRequest("company_products"); // 指定索引名
        // 构建UpdateQuery
        UpdateQuery query = UpdateQuery.builder(nativeSearchQuery)
                .withScript(script)
                .withScriptType(ScriptType.INLINE)
                //.withDocument(doc)
                .withParams(params)
                .build();
        //UpdateQuery query = UpdateQuery.builder(nativeSearchQuery).withDocument(doc).withRefreshPolicy(RefreshPolicy.IMMEDIATE).build();
        ByQueryResponse response = elasticsearchRestTemplate.updateByQuery(query,IndexCoordinates.of("company_products"));
        return "success";
    }
}
