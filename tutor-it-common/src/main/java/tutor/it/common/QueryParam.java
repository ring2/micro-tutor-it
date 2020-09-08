package tutor.it.common;

import lombok.Data;

/**
 * @author :     ring2
 * @date :       2020/4/19 20:14
 * description:
 **/
@Data
public class QueryParam {
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
}
