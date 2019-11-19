package com.issac.webflux.router.bean;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-05
 * @desc:
 */
@Data
@Document(collection = "t_student")
public class Student {

    @Id
    private String id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Range(min = 10,max = 55,message = "年龄必须在{min}-{max}")
    private Integer age;

}
