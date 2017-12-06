package com.winterchen.web.user;

import com.winterchen.domain.QQUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/**
 *
 * Created by winterchen on 2017/11/21.
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

   /* //创建线程安全的Map
    static Map<Long, UserEntity> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value="用户列表", notes = "")
    @GetMapping("/")
    public List<UserEntity> getUserList(){
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<UserEntity> rs = new ArrayList<>(users.values());
        return rs;
    }

    @ApiOperation(value="创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "userEntity", value = "用户详细实体User",required = true, dataType = "UserEntity")
    @PostMapping("/")
    public String postUser(@ModelAttribute UserEntity userEntity){
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(userEntity.getId(), userEntity);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据用户的id查找User")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable Long id){
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @ApiOperation(value = "更新用户信息", notes = "根据用户的id来指定用户，然后根据传送的User更新实体User")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的用户Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userEntity", value = "需要更新的用户详细", required = true, dataType = "UserEntity", paramType = "body")
    })
    @PostMapping("/update/{id}")
    public String putUser(@PathVariable Long id, @ModelAttribute UserEntity userEntity){

        // 处理"/users/{id}"的PUT请求，用来更新User信息
        UserEntity u = users.get(id);
        u.setUserName(userEntity.getUserName());
        u.setAge(userEntity.getAge());
        users.remove(id);
        users.put(id,u);
        return "success";

    }

    @ApiOperation(value = "删除用户", notes = "根据用户ID删除指定用户")
    @ApiImplicitParam(name = "id", value = "需要删除的用户ID", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        users.remove(id);
        return "success";
    }*/

    @ApiOperation(value = "获取用户资料", notes = "登录成功之后的")
    @GetMapping("/home")
    public String user(@AuthenticationPrincipal UsernamePasswordAuthenticationToken userAuthentication, Model model){
        QQUser user = (QQUser) userAuthentication.getPrincipal();
        model.addAttribute("username", user.getNickname());
        model.addAttribute("avatar", user.getAvatar());
        return "/user/user";
    }


}
