package com.winterchen.config;

import com.winterchen.filter.qq.QQAuthenticationFilter;
import com.winterchen.filter.qq.QQAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 配置类
 * Created by winterchen on 2017/11/29.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private AnyUserDetailsService anyUserDetailsService;

    @Autowired
    public void setAnyUserDetailsService(AnyUserDetailsService anyUserDetailsService){
        this.anyUserDetailsService = anyUserDetailsService;
    }

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**","/home/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home/home")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        // 在 UsernamePasswordAuthenticationFilter 前添加 QQAuthenticationFilter
        http.addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/img/**");
    }

    /**
     * 自定义 QQ登录 过滤器
     */
    private QQAuthenticationFilter qqAuthenticationFilter(){
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/login/qq");
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        successHandler.setDefaultTargetUrl("/home/home");
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        return authenticationFilter;
    }


    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(anyUserDetailsService);
    }

}
