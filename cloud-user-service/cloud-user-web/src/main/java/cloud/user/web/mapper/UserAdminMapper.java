package cloud.user.web.mapper;

import cloud.user.web.entity.MallUser;
import cloud.user.web.entity.UserAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-11-03 16:12
 **/
@Mapper
public interface UserAdminMapper extends BaseMapper<UserAdmin> {
}
