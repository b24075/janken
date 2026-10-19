package oit.is.z3398.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {

  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken/**").authenticated() // /sample3/以下は認証済みであること
            .anyRequest().permitAll());
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // user1/p@ss,user2/p@ss,admin/p@ss

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$05$pk8oIMK5SMUMdMcg7FzCsuQiyl7ToMfG5I.ErXOIpyoKbbY56g..2").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$VXKQ7cfcahR8T9TNx5CGPOMHD0EcFO7zd4Ule7VNy0SiEzIKR2ttu").roles("USER").build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2);
  }

}
