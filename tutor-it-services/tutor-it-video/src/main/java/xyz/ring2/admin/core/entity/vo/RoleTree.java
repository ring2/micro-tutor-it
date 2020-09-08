package xyz.ring2.admin.core.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import xyz.ring2.admin.core.entity.Role;

import java.util.List;

/**
 * @author :     weiquanquan
 * @date :       2020/2/10 08:29
 * description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleTree extends Role {

    private List<PermissionTree> children;

}
