package com.issac.icu.lombok;

import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
@ToString(
        includeFieldNames = false,
//        exclude = "field1",
//        of = "field1",
        doNotUseGetters = false
)
public class ToStringTest {
    @Setter
    private String field1;
    @Setter
    private String field2;

    public String getField2() {
        System.out.println("调用get方法");
        return field2;
    }

    @Test
    public void test() {
        ToStringTest toStringTest = new ToStringTest();
        toStringTest.setField1("issac");
        toStringTest.setField2("young");
        System.out.println(toStringTest);
    }
}
