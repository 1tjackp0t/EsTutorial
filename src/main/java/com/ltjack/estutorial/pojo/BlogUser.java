package com.ltjack.estutorial.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 用户pojo类
 * @author: ltjack
 * @createTime: 2020-10-09 16:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "bloguser")
public class BlogUser implements Serializable {

    // esId
    private String id;
    // 对象名称
    private String name;
    // 对象名称
    private List<String> hobby;

}
