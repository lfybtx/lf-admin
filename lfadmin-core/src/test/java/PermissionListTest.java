import com.lf.LfadminApplication;
import com.lf.entity.Permission;
import com.lf.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = LfadminApplication.class)
public class PermissionListTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testFindPermissionListByUserId() {
        // 调用方法获取权限列表
        List<Permission> permissions = permissionService.findPermissionListByUserId(2L);

        System.out.println(permissions);
    }
}
