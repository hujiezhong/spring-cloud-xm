package com.whkobe.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whkobe.advice.CommonExceptionHandle;
import com.whkobe.api.PowerbankApi;
import com.whkobe.pojo.Product;
import com.whkobe.pojo.Product1;
import net.bytebuddy.description.field.FieldList;
import org.apache.ibatis.javassist.runtime.Desc;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.Highlighter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductSearchServiceImpl implements ProductSearchService {
    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private PowerbankApi powerbankApi;

    @Override
    public String deleteAll(){
        try {
            //Query query = new SimpleQuery("*:*");
            //template.delete("test"，query);
            solrClient.deleteByQuery("product","*:*");
            solrClient.commit("product");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @Override
    public List<Product1> tiaojianc(Product product,String tiaojian) {
        List<Product> products = powerbankApi.findAll();
        solrTemplate.saveBeans("product",products);
        solrTemplate.commit("product");
        HighlightQuery query = new SimpleHighlightQuery(new SimpleStringCriteria("*:*"));
        Criteria cri = new Criteria("keywords").is(product.getPname());
        if (product.getRateid()==1) {
            cri= cri.and("rateid").between(2,10);
            System.out.println("成功了");
        }
        if (product.getInventory()==1) {
            cri=cri.and("inventory").between(1,10000);
        }
        if (tiaojian.equals("jianjia")) {
            query.addSort(new Sort(Sort.Direction.ASC, "shopprice"));
        } else if (tiaojian.equals("comment")){
        }else {
            query.addSort(new Sort(Sort.Direction.DESC, tiaojian));
        }
        query.addCriteria(cri);
        HighlightOptions ho = new HighlightOptions();
        ho.addField("pname");   //高亮域，可以多个
        ho.setSimplePrefix("<span style='color:red'>");
        ho.setSimplePostfix("</span>");
        //为查询对象设置高亮选项
        query.setHighlightOptions(ho);
        //返回高亮页对象
        HighlightPage<Product1> hp = solrTemplate.queryForHighlightPage("product",query,Product1.class);
        //此时hp.getContent()还是原生内容，没有高亮
        System.out.println(hp.getContent().size());
        //高亮入口集合（每条记录的高亮入口）
        List<HighlightEntry<Product1>> list = hp.getHighlighted();
        for (HighlightEntry<Product1> entry : list){
            //获取高亮域列表（高亮域的个数）
            List<HighlightEntry.Highlight> hl = entry.getHighlights();
            for (HighlightEntry.Highlight h : hl){
                //每个域可以有多值
                List<String> ls = h.getSnipplets();
            }

            if(hl.size()>0 && hl.get(0).getSnipplets().size()>0){
                Product1 e = entry.getEntity();
                //假设只一个高亮域
                e.setPname(hl.get(0).getSnipplets().get(0));
            }
        }
       /* map.put("rows",hp.getContent());
        System.out.println("马勒戈壁");*/
        return hp.getContent();
    }

    @Override
    public List<Product1> search(String keywords){
        List<Product> products = powerbankApi.findAll();
        solrTemplate.saveBeans("product",products);
        solrTemplate.commit("product");
        Criteria cri = new Criteria("keywords").is(keywords);
        HighlightQuery query = new SimpleHighlightQuery(new SimpleStringCriteria("*:*"));  //query.addCriteria(cri);
        //高亮选项
        query.addCriteria(cri);
        HighlightOptions ho = new HighlightOptions();
        ho.addField("pname");   //高亮域，可以多个
        ho.setSimplePrefix("<span style='color:#DAA520'>");
        ho.setSimplePostfix("</span>");
        //为查询对象设置高亮选项
        query.setHighlightOptions(ho);
        //返回高亮页对象
        HighlightPage<Product1> hp = solrTemplate.queryForHighlightPage("product",query,Product1.class);
        //此时hp.getContent()还是原生内容，没有高亮
        System.out.println(hp.getContent().size());
        //高亮入口集合（每条记录的高亮入口）
        List<HighlightEntry<Product1>> list = hp.getHighlighted();
        for (HighlightEntry<Product1> entry : list){
            //获取高亮域列表（高亮域的个数）
            List<HighlightEntry.Highlight> hl = entry.getHighlights();
            for (HighlightEntry.Highlight h : hl){
                //每个域可以有多值
                List<String> ls = h.getSnipplets();
            }

            if(hl.size()>0 && hl.get(0).getSnipplets().size()>0){
                Product1 e = entry.getEntity();
                //假设只一个高亮域
                e.setPname(hl.get(0).getSnipplets().get(0));
            }
        }
        return hp.getContent();

    }

}
